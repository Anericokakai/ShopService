package com.ShopsService.shopsService.EurekaClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@EnableFeignClients
@FeignClient(name = "PRODUCTS-APP",path = "/v2/api/products")
public interface ProductsClient {

    @DeleteMapping("/delete/all/{storeNumber}")
    Map<String ,String > deleteAllProductUnderShop(@PathVariable("storeNumber") String  storeNumber);

}
