package com.ricodev.shopsService.Controller;
import com.ricodev.shopsService.Exceptions.NotFoundException;
import com.ricodev.shopsService.Services.ShopServiceImpl;
import com.ricodev.shopsService.Tdo.ShopRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private  final ShopServiceImpl  shopService;

    @PostMapping("/new")

    public ResponseEntity<?> createNewShop(@RequestBody @Valid ShopRequest request, @RequestParam("token") String token) throws HttpMessageNotReadableException {


 var req=shopService.createNewShop(request);
          return ResponseEntity.status(HttpStatus.CREATED.value())
                  .body(req);


    }
    @PutMapping("/update/{storeNumber}")
    public ResponseEntity<?> updateShopInfo(@RequestBody ShopRequest shopRequest,@PathVariable("storeNumber") String storeNumber) throws EntityNotFoundException {

        var Res=   shopService.updateShop(shopRequest,storeNumber);
        return  ResponseEntity.status(HttpStatus.CREATED.value()).body(Res);
    }
    @DeleteMapping("/delete/{storeNumber}")
    public  ResponseEntity<?> deleteShop(@PathVariable("storeNumber") String storeNumber) throws NotFoundException {

        var res=  shopService.deleteShop(storeNumber);
        System.out.println("the end point was hit");
        return  ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
