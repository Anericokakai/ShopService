package com.ShopsService.shopsService.Services;

import com.ShopsService.shopsService.Exceptions.NotFoundException;
import com.ShopsService.shopsService.Models.Shops;
import com.ShopsService.shopsService.Repository.ShopRepository;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import com.ShopsService.shopsService.Tdo.ShopResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopsService{

    private  final ShopRepository shopRepository;


    private final ModelMapper modelMapper;
    @Override
    public Optional<Shops> getShopById( int id) {

        return shopRepository.findByShopId(id);

    }

    @Override
    public ShopResponse createNewShop(ShopRequest shopRequest) {
        Shops newShop=  modelMapper.map(shopRequest,Shops.class);
        Shops savedShop=shopRepository.save(newShop);
        return modelMapper.map(savedShop, ShopResponse.class);
    }
}
