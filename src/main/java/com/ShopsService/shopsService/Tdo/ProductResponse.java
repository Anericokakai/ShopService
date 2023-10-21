package com.ShopsService.shopsService.Tdo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponse {


    private Long id;
    private String productName;

    private String productDesc;

    private Integer price;
    private Integer shopId;
    private ShopResponse shopResponse;
}
