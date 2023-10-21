package com.ShopsService.shopsService.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "shops")
@DynamicInsert
@DynamicUpdate
public class Shops {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "shop_location")
    private  String shopLocation;
    @Column(name = "shop_contact")
    private String shopContact;
    @Column(name = "store_number")
    private String storeNumber;
}
