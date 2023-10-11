package com.ShopsService.shopsService.Services;

import com.ShopsService.shopsService.Exceptions.NotFoundException;
import com.ShopsService.shopsService.Models.Shops;
import com.ShopsService.shopsService.Repository.ShopRepository;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import com.ShopsService.shopsService.Tdo.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopsService{

    private  final ShopRepository shopRepository;
    @Override
    public Optional<Shops> getShopById( int id) {

        return shopRepository.findById(id);

    }

    @Override
    public ShopResponse createNewShop(ShopRequest shopRequest) {

        Shops newShop= Shops.builder()
                .shopName(shopRequest.getShopName())
                .shopContact(shopRequest.getShopContact())
                .shopLocation(shopRequest.getShopLocation())
                .build();

        Shops savedShop=shopRepository.save(newShop);
        return ShopResponse.builder()
                .shopContact(savedShop.getShopContact())
                .shopId(savedShop.getId())
                .shopLocation(savedShop.getShopLocation())
                .shopName(savedShop.getShopName())
                .build();
    }
}
