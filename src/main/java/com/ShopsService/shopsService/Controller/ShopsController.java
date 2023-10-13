package com.ShopsService.shopsService.Controller;

import com.ShopsService.shopsService.Exceptions.NotFoundException;
import com.ShopsService.shopsService.Services.ShopServiceImpl;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ShopsController {
    private  final ShopServiceImpl shopService;

    @PostMapping("/new")
    public ResponseEntity<?> createNewShop(@RequestBody ShopRequest request){

        HashMap<String ,String > errorMessage= new HashMap<>();
        errorMessage.put("errorMessage","all fields are required");
        if(request.getShopContact()==null||request.getShopLocation()==null||request.getShopName()==null){
            return ResponseEntity.status(401).body(
                    errorMessage
            );
        }
        try {

            var newShop=shopService.createNewShop(request);
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(newShop);
        }catch (NotFoundException ex){
            HashMap<String ,String > errorMap= new HashMap<>();
            errorMap.put("errorMessage","failed to create a new shop");


            return  ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errorMap);
        }

    }


    @GetMapping("/{shopId}")

    public ResponseEntity<?> findShopById(@PathVariable("shopId") int id){
        try{

           var result= shopService.getShopById(id);
           if(result.isEmpty()){
               HashMap<String ,String > error= new HashMap<>();
               error.put("errorMessage","cannot find a shop of the given id !");


               return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                       error);
           }
           return  ResponseEntity.status(200).body(
                   result
           );
    }catch (EntityNotFoundException ex){


            HashMap<String ,String > error= new HashMap<>();
            error.put("errorMessage","cannot find a shop of the given id !");


            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    error);
        }

        }
}
