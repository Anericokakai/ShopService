package com.ShopsService.shopsService.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "shops")
public class Shops {

    @Column(name = "shop_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "shop_location")
    private  String shopLocation;
    @Column(name = "shop_contact")
    private String shopContact;
}
