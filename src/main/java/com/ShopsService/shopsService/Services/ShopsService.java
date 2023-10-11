package com.ShopsService.shopsService.Services;

import com.ShopsService.shopsService.Models.Shops;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import com.ShopsService.shopsService.Tdo.ShopResponse;

import java.util.Optional;

public interface ShopsService {


    Optional<Shops> getShopById( int id);


    ShopResponse createNewShop(ShopRequest shopRequest);

}
