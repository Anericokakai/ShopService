package com.ShopsService.shopsService.Repository;

import com.ShopsService.shopsService.Models.Shops;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shops,Integer> {

    Optional<Shops>findByStoreNumber(String storeNumber);

    @Modifying
    @Transactional
    @Query("DELETE  FROM Shops s WHERE s.storeNumber = :storeNumber")
    void deleteByStoreNumber(String storeNumber);
}
