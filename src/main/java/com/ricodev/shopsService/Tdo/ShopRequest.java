package com.ricodev.shopsService.Tdo;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ShopRequest {


@NotBlank(message = "shop name cannot be blank")
    private String shopName;

@NotBlank(message = "ShopLocation cannot be blank")
    private  String shopLocation;
@NotBlank(message = "shop contact number cannot be blank")
    private String shopContact;

    private  String storeNumber;
}

