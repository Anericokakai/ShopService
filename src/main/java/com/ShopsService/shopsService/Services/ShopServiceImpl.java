package com.ShopsService.shopsService.Services;

import com.ShopsService.shopsService.Exceptions.NotFoundException;
import com.ShopsService.shopsService.Models.Shops;
import com.ShopsService.shopsService.Repository.ShopRepository;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import com.ShopsService.shopsService.Tdo.ShopResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopsService{

    private  final ShopRepository shopRepository;


    private final ModelMapper modelMapper;



//    ! generate a unique store number of each shop

    public String generateUniqueStoreNumber(ShopRequest shopRequest){

        String uniqueId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyssSSS"));
        return "STORE-"+shopRequest.getShopContact().substring(6)+uniqueId;
    }

    @Override
    public Optional<Shops> findByStoreNumber(String storeNumber) {

        if(shopRepository.findByStoreNumber(storeNumber).isEmpty()){

            throw new EntityNotFoundException("shop with the given id :"+storeNumber+ " was not found");
        }
            return  shopRepository.findByStoreNumber(storeNumber);
    }

    @Override
    public ShopResponse createNewShop(ShopRequest shopRequest) {
        String storeNo=generateUniqueStoreNumber(shopRequest);
        shopRequest.setStoreNumber(storeNo);
        Shops newShop=  modelMapper.map(shopRequest,Shops.class);
        Shops savedShop=shopRepository.save(newShop);
        return modelMapper.map(savedShop, ShopResponse.class);
    }

    @Override
    public ShopResponse updateShop(ShopRequest shopRequest,String storeNumber) {
        if(shopRepository.findByStoreNumber(storeNumber).isEmpty()){
            throw new EntityNotFoundException("shop with the given id :"+storeNumber+ " was not found");
        }

        Shops existingShop= shopRepository.findByStoreNumber(storeNumber).get();
if(!shopRequest.getShopLocation().isEmpty()){
    existingShop.setShopLocation(shopRequest.getShopLocation());
}
if(!shopRequest.getShopContact().isEmpty()){
    existingShop.setShopContact(shopRequest.getShopContact());
}

if(!shopRequest.getShopName().isEmpty()){
    existingShop.setShopName(shopRequest.getShopName());
}
        Shops savedShop=shopRepository.save(existingShop);

        return modelMapper.map(savedShop,ShopResponse.class);

    }
}
