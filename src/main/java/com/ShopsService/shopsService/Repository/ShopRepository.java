package com.ShopsService.shopsService.Repository;

import com.ShopsService.shopsService.Models.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shops,Integer> {

    Optional<Shops>findByStoreNumber(String storeNumber);
    Optional<Shops> deleteByStoreNumber(String storeNumber);
}
