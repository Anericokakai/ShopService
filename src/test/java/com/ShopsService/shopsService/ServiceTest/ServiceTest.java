package com.ShopsService.shopsService.ServiceTest;
import com.ShopsService.shopsService.Exceptions.NotFoundException;
import com.ShopsService.shopsService.Models.Shops;
import com.ShopsService.shopsService.Repository.ShopRepository;
import com.ShopsService.shopsService.Services.ShopServiceImpl;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import com.ShopsService.shopsService.Tdo.ShopResponse;
import com.ShopsService.shopsService.configs.AppConfigs;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfigs.class)
@SpringBootTest(classes = ServiceTest.class )

public class ServiceTest {
    @Mock
    ShopRepository shopRepository;
    @InjectMocks
    ShopServiceImpl shopsService;
@Autowired
private   ModelMapper modelMapper;

     List<Shops> shops;
    Shops shops1;


    @BeforeEach
    void setUp() {
        shops= new ArrayList<>();
        shops.add(new Shops(1,"kwa mama brayo","nairobi cbd","0792626899","STORE-78225202328113"));

        shops.add(new Shops(2,"kwa mama jemo","nyeri town naivas","079387219","STORE-78225202328129"));
        shops1= new Shops(1, "kwa mama brayo", "nairobi cbd", "0792626899", "STORE-78225202328113");


    }

    @Test
    @Order(1)
    public  void test_FindAllShops(){


        Mockito.when(shopRepository.findAll()).thenReturn(shops);


        assertEquals(2,shopsService.findAll().size());


    }

    @Test
    @Order(2)
public  void  assert_throw_notFoundExForFindAll_test(){

        Mockito.when(shopRepository.findAll()).thenThrow(new NotFoundException("there are no items shops available"));

        assertThrows(NotFoundException.class,()->{
            shopsService.findAll();
        });
    }


    @Test
    public  void assert_findByStoreNumber_isAvailable_test(){


      String storeNumber="STORE-78225202328113";


      Mockito.when(shopRepository.findByStoreNumber(storeNumber)).thenReturn(Optional.of(shops1));

      Optional<Shops> availableShop =shopsService.findByStoreNumber(storeNumber);
      assertEquals(shops1,availableShop.orElse(null));
      Shops foundShop=availableShop.get();

      assertEquals(storeNumber,foundShop.getStoreNumber());

      assertEquals(shops1.getShopContact(),foundShop.getShopContact());
      assertEquals(shops1.getShopName(),foundShop.getShopName());

    }

    //excepion will be thrown


    @Test
    public  void assertThaExceptionWillBeThrownForNotFoundShop(){
        String  storeNum="123";
        Mockito.when(shopRepository.findByStoreNumber(storeNum)) .thenReturn(Optional.of(shops1)) .thenThrow(new EntityNotFoundException("shop with the given id :"+storeNum+ " was not found"));

        assertThrows(EntityNotFoundException.class,()->{
            shopsService.findByStoreNumber(storeNum);
        });

    }

//    create a new shop test

    @Test
   public void assert_That_ShopWillBECreated(){

Mockito.when(shopRepository.save(shops1)).thenReturn(shops1);
        ShopRequest shopRequest= ShopRequest.builder()
                .shopContact(shops1.getShopContact())
                .shopLocation(shops1.getShopLocation())
                .shopName(shops1.getShopName())
                .storeNumber(shops1.getStoreNumber())
                .build();
        ShopResponse res=shopsService.createNewShop(shopRequest);
        Shops  foundShop=Shops.builder()
                .shopName(res.getShopName())
                .shopContact(res.getShopContact())
                .shopLocation(res.getShopLocation())
                .id(1)
                .build();
        assertEquals(shops1,foundShop);


    }




































}





