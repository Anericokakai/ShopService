package com.ricodev.shopsService.Controller;

import com.ricodev.shopsService.Services.ShopServiceImpl;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class ShopsController {
    private  final ShopServiceImpl shopService;


    @GetMapping("/all")
    public  ResponseEntity<?> findAllShops(){
        var res=shopService.findAll();
        return  ResponseEntity.status(HttpStatus.FOUND).body(res);
    }
    @GetMapping("/{storeNumber}")

    public ResponseEntity<?> findShopById(@PathVariable("storeNumber") String storeNumber) throws  EntityNotFoundException{

           var result= shopService.findByStoreNumber(storeNumber);

           System.out.println("the server was hit by the product service");
           return  ResponseEntity.status(HttpStatus.OK).body(
                   result
           );


        }


//        update the shop details





}


