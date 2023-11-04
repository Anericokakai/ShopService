package com.ShopsService.shopsService.Controller;

import com.ShopsService.shopsService.Services.ShopServiceImpl;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private  final ShopServiceImpl  shopService;
    @PostMapping("/new")

    public ResponseEntity<?> createNewShop(@RequestBody @Valid ShopRequest request) throws HttpMessageNotReadableException {

        var newShop=shopService.createNewShop(request);
        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(newShop);

    }
}
