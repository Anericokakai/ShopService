//package com.ShopsService.shopsService.ControllerTest;
//
//import com.ShopsService.shopsService.Controller.ShopsController;
//import com.ShopsService.shopsService.Exceptions.ExceptionHandlerController;
//import com.ShopsService.shopsService.Models.Shops;
//import com.ShopsService.shopsService.Services.ShopServiceImpl;
//import com.ShopsService.shopsService.Tdo.ShopRequest;
//import com.ShopsService.shopsService.Tdo.ShopResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest(classes = {ShopControllerTest.class})
//@AutoConfigureMockMvc
//@ContextConfiguration
//@ComponentScan(basePackages = "com.ShopService.shopService")
//public class ShopControllerTest {
//   @Autowired
//    MockMvc mockMvc;
//   @Mock
//   ShopServiceImpl shopService;
//
//
//   @InjectMocks
//    ShopsController shopsController;
//   Shops shops;
//   List<Shops> shopsList;
//    ShopRequest shopRequest;
//    ShopRequest updateShop;
//
//
//    @BeforeEach
//    void setUp() {
//        shopsList=new ArrayList<>();
//        shopsList.add(new Shops(1,"kwa mama brayo","nairobi cbd","0792626899","STORE-78225202328113"));
//
//        shopsList.add(new Shops(2,"kwa mama jemo","nyeri town naivas","079387219","STORE-78225202328129"));
//        shopsList.add (new Shops(1, "kwa mama brayo", "nairobi cbd", "0792626899", "STORE-78225202328113"));
//        shops= new Shops(2,"kwa mama jemo","nyeri town naivas","079387219","STORE-78225202328129");
//         shopRequest=new ShopRequest(shops.getShopName(), shops.getShopLocation(), shops.getShopContact(), shops.getStoreNumber());
//        mockMvc= MockMvcBuilders.standaloneSetup(shopsController)
//                .setControllerAdvice( new ExceptionHandlerController()).build();
//    }
//
//    @Test
//
//    @Order(1)
//    public void assert_that_Will_FindAllShops() throws Exception{
//        Mockito.when(shopService.findAll()).thenReturn(shopsList);
//mockMvc.perform(get("/home/all")
//        .contentType("application/json")
//                .characterEncoding("utf-8"))
//
//        .andExpect(status().isFound())
//        .andExpect(jsonPath("$[0]['id']").value(shopsList.get(0).getId()))
//        .andExpect(jsonPath("$[2]['storeNumber']").value(shopsList.get(2).getStoreNumber()))
//        .andDo(print());
//
//
//    }
//
//    @Test
//    @Order(2)
//   public void asserThatwillCreateNewShop() throws  Exception{
//
//        ObjectMapper objectMapper= new ObjectMapper();
//
//        ShopResponse shopResponse=new ShopResponse();
//        shopResponse.setShopName(shops.getShopName());
//        shopResponse.setShopLocation(shops.getShopLocation());
//        shopResponse.setShopContact(shops.getShopContact());
//        shopResponse.setStoreNumber(shops.getStoreNumber());
//        String reqBody=objectMapper.writeValueAsString(shopRequest);
//  Mockito.when(shopService.createNewShop(shopRequest)).thenReturn(shopResponse);
// mockMvc.perform(post("/home/new")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .characterEncoding("utf-8")
//                 .content(reqBody))
//        .andExpect(status()
//        .isCreated())
//         .andExpect(jsonPath("$.storeNumber").value(shops.getStoreNumber()))
//         .andDo(print());
//    }
//@Test
//@Order(3)
//    public  void assertThatWIllThrowExceptionInvalidINputs() throws  Exception{
//
//
//
//        ShopRequest shopRequest= ShopRequest.builder()
//                .shopContact("Anerico")
//                .shopLocation("nairobi")
//                .shopName("")
//                .storeNumber("poiuytr").build();
//
//    ObjectMapper objectMapper= new ObjectMapper();
//        String reqBody=  objectMapper.writeValueAsString(shopRequest);
//
//        mockMvc.perform(post("/home/new")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//                .content(reqBody)).andExpect(status().isBadRequest())
//                .andExpect(result->assertTrue(result.getResolvedException()
//                        instanceof MethodArgumentNotValidException))
//                .andDo(print());
//}
//
//
//
//@Test
//@Order(4)
//    public  void  assertThatWillFIndAShopBYStoreNumber()  throws  Exception{
//
//        String  storeNUmber="STORE-78225202328129";
//
//
//        Mockito.when(shopService.findByStoreNumber(storeNUmber)).thenReturn(Optional.of(shops));
//
//        mockMvc.perform(
//                get("/home/{storeNumber}",storeNUmber)
//                        .characterEncoding("utf-8")
//                        .contentType(MediaType.APPLICATION_JSON)
//
//        ).andExpect(status().isOk())
//                .andExpect(jsonPath("$.storeNumber").value(shops.getStoreNumber()))
//                .andExpect(jsonPath("$.shopName").value(shops.getShopName()));
//
//
//}
//
//@Test
//    @Order(5)
//public  void assertThatWillThrowNOtFoundException() throws Exception{
//        String storeNum="1234";
//
//
//        String  errorMessage="shop with the given id :"+storeNum+ " was not found";
//        Mockito.when(shopService.findByStoreNumber(storeNum)).thenAnswer(result->{
//            if(storeNum.equals(shops.getStoreNumber())){
//                return  Optional.of(shops);
//            }else{
//                throw new EntityNotFoundException(errorMessage);
//            }
//        });
//
//
//        mockMvc.perform(
//                get("/home/{storeNumber}",storeNum)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//
//        ).andExpect(status().isBadRequest())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
//                .andExpect(jsonPath("$.errorMessage" ).value(errorMessage))
//                .andDo(print());
//}
//
//
//
//    @Test
//    @Order(6)
//    public void assertWillUpdateStore() throws  Exception{
//
//updateShop=new ShopRequest("drips ke","nairobi westGate mall","074378047","STORE-26783902");
//ShopResponse shopRes= ShopResponse.builder()
//        .shopName(updateShop.getShopName())
//        .shopLocation(updateShop.getShopLocation())
//        .shopContact(updateShop.getShopContact())
//        .storeNumber(updateShop.getStoreNumber())
//        .build();
//String  storeNumber="12345";
//        Mockito.when(shopService.updateShop(shopRequest,shopRequest.getStoreNumber())).thenAnswer(response->{
//            if(storeNumber.equals(shops.getStoreNumber())){
//                return shopRes;
//            }else{
//                throw new EntityNotFoundException("shop with the given id :"+storeNumber+ " was not found");
//            }
//
//        });
//
//        ObjectMapper objectMapper= new ObjectMapper();
//
//        String reqBody=objectMapper.writeValueAsString(updateShop);
//        mockMvc.perform(put("/home/update/{storeNumber}",storeNumber)
//                .characterEncoding("utf-8")
//                .contentType("application/json")
//                .content(reqBody))
//                .andExpect(status().isCreated());
//
//}
//
//
//
//}
