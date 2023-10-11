package com.ShopsService.shopsService.Tdo;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class ShopResponse {

private  int shopId;
    private String shopName;

    private  String shopLocation;

    private String shopContact;
}
