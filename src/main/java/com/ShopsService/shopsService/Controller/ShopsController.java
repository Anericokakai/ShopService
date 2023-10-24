package com.ShopsService.shopsService.Controller;

import com.ShopsService.shopsService.Exceptions.NotFoundException;
import com.ShopsService.shopsService.Services.ShopServiceImpl;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor

public class ShopsController {
    private  final ShopServiceImpl shopService;

    @PostMapping("/new")
    public ResponseEntity<?> createNewShop(@RequestBody @Valid ShopRequest request) throws HttpMessageNotReadableException {

            var newShop=shopService.createNewShop(request);
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(newShop);

    }
    @GetMapping("/all")
    public  ResponseEntity<?> findAllShops(){
        var res=shopService.findAll();
        return  ResponseEntity.status(HttpStatus.FOUND).body(res);
    }


    @GetMapping("/{storeNumber}")

    public ResponseEntity<?> findShopById(@PathVariable("storeNumber") String storeNumber) throws  EntityNotFoundException{

           var result= shopService.findByStoreNumber(storeNumber);

           System.out.println("the server was hit by the product service");
           return  ResponseEntity.status(HttpStatus.FOUND).body(
                   result
           );


        }

//        update the shop details

    @PutMapping("/update/{storeNumber}")
    public ResponseEntity<?> updateShopInfo(@RequestBody ShopRequest shopRequest,@PathVariable("storeNumber") String storeNumber) throws EntityNotFoundException{

     var Res=   shopService.updateShop(shopRequest,storeNumber);
        return  ResponseEntity.status(HttpStatus.CREATED.value()).body(Res);
    }


    @DeleteMapping("/delete/{storeNumber}")
    public  ResponseEntity<?> deleteShop(@PathVariable("storeNumber") String storeNumber) throws NotFoundException{

   var res=  shopService.deleteShop(storeNumber);
System.out.println("the end point was hit");
   return  ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}


