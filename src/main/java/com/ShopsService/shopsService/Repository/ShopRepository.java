package com.ShopsService.shopsService.Repository;

import com.ShopsService.shopsService.Models.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shops,Integer> {
}
