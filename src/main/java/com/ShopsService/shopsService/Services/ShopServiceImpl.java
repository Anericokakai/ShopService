package com.ShopsService.shopsService.Services;
import com.ShopsService.shopsService.EurekaClient.ProductsClient;
import com.ShopsService.shopsService.Exceptions.NotFoundException;
import com.ShopsService.shopsService.Models.Shops;
import com.ShopsService.shopsService.Repository.ShopRepository;
import com.ShopsService.shopsService.Tdo.ShopRequest;
import com.ShopsService.shopsService.Tdo.ShopResponse;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



@Service
public class ShopServiceImpl implements ShopsService{

    private  final ShopRepository shopRepository;


    private  ModelMapper modelMapper;
private  final ProductsClient productsClient;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, ProductsClient productsClient) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.productsClient = productsClient;
    }


//    ! generate a unique store number of each shop

    public String generateUniqueStoreNumber(ShopRequest shopRequest){

        String uniqueId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyssSSS"));
        return "STORE-"+shopRequest.getShopContact().substring(6)+uniqueId;
    }

    @Override
    public Optional<Shops> findByStoreNumber(String storeNumber) {

        if(shopRepository.findByStoreNumber(storeNumber).isEmpty()){

            throw new EntityNotFoundException("shop with the given id :"+storeNumber+ " was not found");
        }
            return  shopRepository.findByStoreNumber(storeNumber);
    }

    @Override
    public ShopResponse createNewShop(ShopRequest shopRequest) {
        String storeNo=generateUniqueStoreNumber(shopRequest);
        shopRequest.setStoreNumber(storeNo);
        Shops newShop=  modelMapper.map(shopRequest,Shops.class);
        Shops savedShop=shopRepository.save(newShop);
        return modelMapper.map(savedShop, ShopResponse.class);
    }

    @Override
    public ShopResponse updateShop(ShopRequest shopRequest,String storeNumber) {
        if(shopRepository.findByStoreNumber(storeNumber).isEmpty()){
            throw new EntityNotFoundException("shop with the given id :"+storeNumber+ " was not found");
        }

        Shops existingShop= shopRepository.findByStoreNumber(storeNumber).get();
if(!shopRequest.getShopLocation().isEmpty()){
    existingShop.setShopLocation(shopRequest.getShopLocation());
}
if(!shopRequest.getShopContact().isEmpty()){
    existingShop.setShopContact(shopRequest.getShopContact());
}

if(!shopRequest.getShopName().isEmpty()){
    existingShop.setShopName(shopRequest.getShopName());
}
        Shops savedShop=shopRepository.save(existingShop);

        return modelMapper.map(savedShop,ShopResponse.class);

    }

    @Override
    public Map<String,String > deleteShop(String storeNumber) {



if(shopRepository.findByStoreNumber(storeNumber).isEmpty()){

    throw  new NotFoundException("there is no store with the given id : "+storeNumber+" that is available");

}

shopRepository.deleteByStoreNumber(storeNumber);
//        delete all the products related to the shop
       Map<String,String > response= productsClient.deleteAllProductUnderShop(storeNumber);


       response.put("message","products where deleted successful together with the shop");
return  response;
    }

    @Override
    public List<Shops> findAll() {
        List<Shops> shops = shopRepository.findAll();

        if(shops.isEmpty()){
            throw new NotFoundException("there are no items shops available");
        }
        return shops;
    }
}
