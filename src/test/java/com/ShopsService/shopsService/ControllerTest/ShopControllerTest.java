package com.ShopsService.shopsService.ControllerTest;

import com.ShopsService.shopsService.Controller.ShopsController;
import com.ShopsService.shopsService.Models.Shops;
import com.ShopsService.shopsService.Services.ShopServiceImpl;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import com.ShopsService.shopsService.Tdo.ShopResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ShopControllerTest.class})
@AutoConfigureMockMvc
@ContextConfiguration
@ComponentScan(basePackages = "com.ShopService.shopService")
public class ShopControllerTest {
   @Autowired
    MockMvc mockMvc;
   @Mock
   ShopServiceImpl shopService;


   @InjectMocks
    ShopsController shopsController;
   Shops shops;
   List<Shops> shopsList;


    @BeforeEach
    void setUp() {
        shopsList=new ArrayList<>();
        shopsList.add(new Shops(1,"kwa mama brayo","nairobi cbd","0792626899","STORE-78225202328113"));

        shopsList.add(new Shops(2,"kwa mama jemo","nyeri town naivas","079387219","STORE-78225202328129"));
        shopsList.add (new Shops(1, "kwa mama brayo", "nairobi cbd", "0792626899", "STORE-78225202328113"));
        mockMvc= MockMvcBuilders.standaloneSetup(shopsController).build();
    }

    @Test

    public void assert_that_Will_FindAllShops() throws Exception{
        Mockito.when(shopService.findAll()).thenReturn(shopsList);
mockMvc.perform(get("/all")
        .contentType("application/json")
                .characterEncoding("utf-8"))

        .andExpect(status().isFound())
        .andExpect(jsonPath("$[0]['id']").value(shopsList.get(0).getId()))
        .andExpect(jsonPath("$[2]['storeNumber']").value(shopsList.get(2).getStoreNumber()))
        .andDo(print());


    }

    @Test
   public void asserThatwillCreateNewShop() throws  Exception{

        ObjectMapper objectMapper= new ObjectMapper();

        shops= new Shops(2,"kwa mama jemo","nyeri town naivas","079387219","STORE-78225202328129");
        ShopRequest shopRequest=new ShopRequest(shops.getShopName(), shops.getShopLocation(), shops.getShopContact(), shops.getStoreNumber());
        ShopResponse shopResponse=new ShopResponse();
        shopResponse.setShopName(shops.getShopName());
        shopResponse.setShopLocation(shops.getShopLocation());
        shopResponse.setShopContact(shops.getShopContact());
        shopResponse.setStoreNumber(shops.getStoreNumber());
        String reqBody=objectMapper.writeValueAsString(shopRequest);
  Mockito.when(shopService.createNewShop(shopRequest)).thenReturn(shopResponse);
 mockMvc.perform(post("/new")
                 .contentType(MediaType.APPLICATION_JSON)
                 .characterEncoding("utf-8")
                 .content(reqBody))
        .andExpect(status()
        .isCreated())
         .andExpect(jsonPath("$.storeNumber").value(shops.getStoreNumber()))
         .andDo(print());
    }
@Test
    public  void assertWIllThrowExcptio() throws  Exception{



        ShopRequest shopRequest= ShopRequest.builder()
                .shopContact("Anerico")
                .shopLocation("nairobi")
                .shopName("")
                .storeNumber("poiuytr").build();

    ObjectMapper objectMapper= new ObjectMapper();
        String reqBody=  objectMapper.writeValueAsString(shopRequest);

        mockMvc.perform(post("/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                .content(reqBody)).andExpect(status().isBadRequest())
                .andDo(print());
}




}
