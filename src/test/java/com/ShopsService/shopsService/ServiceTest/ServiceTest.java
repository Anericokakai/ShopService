//package com.ShopsService.shopsService.ServiceTest;
//import com.ShopsService.shopsService.EurekaClient.ProductsClient;
//import com.ShopsService.shopsService.Exceptions.NotFoundException;
//import com.ShopsService.shopsService.Models.Shops;
//import com.ShopsService.shopsService.Repository.ShopRepository;
//import com.ShopsService.shopsService.Services.ShopServiceImpl;
//import com.ShopsService.shopsService.Tdo.ShopRequest;
//import com.ShopsService.shopsService.Tdo.ShopResponse;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import java.util.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = ServiceTest.class )
//
//public class ServiceTest {
//    @Mock
//    ShopRepository shopRepository;
//    @InjectMocks
//    ShopServiceImpl shopsService;
//
//    @InjectMocks
//    private  ModelMapper modelMapper;
//
//    @Mock
//            private ProductsClient productsClient;
//
//     List<Shops> shops;
//    Shops shops1;
//    ShopResponse shopResponse;
//    ShopRequest shopRequest;
//
//
//    @BeforeEach
//    void setUp() {
//        shops= new ArrayList<>();
//        shops.add(new Shops(1,"kwa mama brayo","nairobi cbd","0792626899","STORE-78225202328113"));
//
//        shops.add(new Shops(2,"kwa mama jemo","nyeri town naivas","079387219","STORE-78225202328129"));
//        shops1= new Shops(1, "kwa mama brayo", "nairobi cbd", "0792626899", "STORE-78225202328113");
//         shopResponse=ShopResponse.builder()
//                .shopName(shops1.getShopName())
//                .shopLocation(shops1.getShopLocation())
//                .shopContact(shops1.getShopContact())
//                .storeNumber(shops1.getStoreNumber()).build();
//         shopRequest= ShopRequest.builder()
//                .storeNumber(shops1.getStoreNumber())
//                .shopName(shops1.getShopName())
//                .shopLocation(shops1.getShopLocation())
//                .shopContact(shops1.getShopContact())
//                .build();
//
//
//    }
//
//    @Test
//    @Order(1)
//    public  void test_FindAllShops(){
//
//
//        Mockito.when(shopRepository.findAll()).thenReturn(shops);
//
//
//        assertEquals(2,shopsService.findAll().size());
//
//
//    }
//
//    @Test
//    @Order(2)
//public  void  assert_throw_notFoundExForFindAll_test(){
//
//        Mockito.when(shopRepository.findAll()).thenThrow(new NotFoundException("there are no items shops available"));
//
//        assertThrows(NotFoundException.class,()->{
//            shopsService.findAll();
//        });
//    }
//
//
//    @Test
//    public  void assert_findByStoreNumber_isAvailable_test(){
//
//
//      String storeNumber="STORE-78225202328113";
//
//
//      Mockito.when(shopRepository.findByStoreNumber(storeNumber)).thenReturn(Optional.of(shops1));
//
//      Optional<Shops> availableShop =shopsService.findByStoreNumber(storeNumber);
//      assertEquals(shops1,availableShop.orElse(null));
//      Shops foundShop=availableShop.get();
//
//      assertEquals(storeNumber,foundShop.getStoreNumber());
//
//      assertEquals(shops1.getShopContact(),foundShop.getShopContact());
//      assertEquals(shops1.getShopName(),foundShop.getShopName());
//
//    }
//
//    //excepion will be thrown
//
//
//    @Test
//    public  void assertThaExceptionWillBeThrownForNotFoundShop(){
//        String  storeNum="123";
//        Mockito.when(shopRepository.findByStoreNumber(storeNum))
//                .thenAnswer(invoc->{
//                    if(storeNum.equals(shops1.getStoreNumber())){
//                        return  Optional.of(shops1);
//                    }else {
//                        throw new  EntityNotFoundException("shop with the given id :"+storeNum+ " was not found");
//                    }
//                });
//
//
//        assertThrows(EntityNotFoundException.class,()->{
//            shopsService.findByStoreNumber(storeNum);
//        });
//
//    }
//
////    create a new shop test
//
////    @Test
////   public void assert_That_ShopWillBECreated(){
////
////Mockito.when(shopRepository.save(shops1)).thenReturn(shops1);
////
////
////        Shops newShop=new Shops();
////        Shops savedShop=new Shops();
////        Mockito.when(shopRepository.save(Mockito.any(Shops.class))).thenReturn(shops1);
////
////       when(modelMapper.map(shopRequest, Shops.class)).thenReturn(newShop);
////       when(shopRepository.save(newShop)).thenReturn(savedShop);
////       when(modelMapper.map(savedShop,ShopResponse.class)).thenReturn(shopResponse);
////       assertEquals(shopResponse,shopsService.createNewShop(shopRequest));
////    }
//    @Test
//    public void assertThat_Update_WillFail(){
//        String  storeNumber= "12345";
//        when(shopRepository.findByStoreNumber(storeNumber)).thenAnswer(res->{
//            if (storeNumber.equals(shops1.getStoreNumber())) {
//                return Optional.of(shops1);
//            }else{
//               throw new EntityNotFoundException("shop with the given id :"+storeNumber+ " was not found") ;
//            }
//        });
//
//        assertThrows(EntityNotFoundException.class,()->{
//            shopsService.updateShop(shopRequest,storeNumber);
//        });
//
//    }
//
////    @Test
////     public  void assert_That_ShopWillBeUpdated(){
////        Shops savedShop=new Shops();
////        ShopRequest newShop=new ShopRequest("kwa kina siko","mombasa","07926262899","");
////        ShopResponse updatedShop= new ShopResponse(newShop.getShopName(),newShop.getShopLocation(),newShop.getShopContact(),shops1.getStoreNumber());
////
////        ShopResponse shopResponse1= modelMapper.map(newShop,ShopResponse.class);
//////     when(modelMapper.map(savedShop,ShopResponse.class)).thenReturn(updatedShop);
////        when(shopRepository.findByStoreNumber(shops1.getStoreNumber())).thenReturn(Optional.ofNullable(shops1));
////shopsService= new ShopServiceImpl(shopRepository,modelMapper,productsClient);
////        when(shopsService.updateShop(newShop,shops1.getStoreNumber())).thenReturn(shopResponse1);
////
////        assertEquals(updatedShop,shopsService.updateShop(newShop,shops1.getStoreNumber()));
////
////    }
//
//
////    ! delete tesst
//    @Test
//    public  void WillTrhowExceptionFOrWrongStoreNumber(){
//
//        String storeNumber= "1234";
//
//        when(shopRepository.findByStoreNumber(storeNumber))
//                .thenAnswer(res->{
//                    if(storeNumber.equals(shops1.getStoreNumber())){
//                        return Optional.of(shops1);
//                    }else{
//                        throw new NotFoundException("there is no store with the given id : "+storeNumber+" that is available");
//
//                    }
//                });
//
//        assertThrows(NotFoundException.class,()->{
//            shopsService.deleteShop(storeNumber);
//        });
//    }
//
//
//@Test
//    public  void assert_willDelete_Shop(){
//String  storeNumber=shops1.getStoreNumber();
//    Map<String ,String > response=new HashMap<>();
//    response.put("message","products where deleted successful together with the shop");
//
//    Map<String ,String > expectedRes=new HashMap<>();
//    Map<String,String> message=new HashMap<>();
//
//    message.put("productMessage","products deleted succesfully");
//    expectedRes.put("message","products where deleted successful together with the shop");
//    expectedRes.putAll(message);
//    response.putAll(message);
//
//    when(productsClient.deleteAllProductUnderShop(shops1.getStoreNumber())).thenReturn(message);
//    when(shopRepository.findByStoreNumber(shops1.getStoreNumber())).thenReturn(Optional.of(shops1));
//when(shopsService.deleteShop(storeNumber)).thenReturn(response);
//assertEquals(expectedRes,shopsService.deleteShop(storeNumber));
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
//
//
//
//
//
