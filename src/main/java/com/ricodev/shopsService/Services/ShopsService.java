package com.ricodev.shopsService.Services;

import com.ricodev.shopsService.Models.Shops;
import com.ricodev.shopsService.Tdo.ShopRequest;
import com.ricodev.shopsService.Tdo.ShopResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ShopsService {


    Optional<Shops> findByStoreNumber( String storeNumber);


    ShopResponse createNewShop(ShopRequest shopRequest);


    ShopResponse updateShop(ShopRequest shopRequest,String storeNumber);
    Map<String ,String > deleteShop(String storeNumber);

    List<Shops> findAll();
}
