package com.tourMaster.letsTour;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tourMaster.letsTour.modals.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DummyController {
     public DummyController()
     {


     }
     @PostMapping("/api/v1/rawJson")
    public void processJson(@RequestBody String rawJson) throws JsonProcessingException {
         ObjectMapper mapper = new ObjectMapper();
         JsonNode rootNode= mapper.readTree(rawJson);
         //firstUserPrimaryEmail(rootNode);
        // countLoginHistory(rootNode);
        //  getSecondLoginCity(rootNode);
         // totalOrderAmountAfterDiscount(rootNode);
         //addNewRole(rootNode);
         //convertJsonNodeToObject(rootNode);
         getRiskScore(rootNode);



     }

    private void getRiskScore(JsonNode rootNode) {
         if(rootNode.path("data").path("users").isArray()) {
             ArrayNode users = (ArrayNode) rootNode.path("data").path("users");
             for (JsonNode user : users) {
                 Double riskScore=user.path("flags").path("riskScore").asDouble();
                 if(riskScore>0.2)
                 {
                     System.out.println(user.path("profile").path("name").path("first").asText()+
                                     user.path("profile").path("name").path("last").asText()

                             );
                 }


             }
         }

    }

    private void convertJsonNodeToObject(JsonNode rootNode) throws JsonProcessingException {
        ArrayList<Item> items= new ArrayList<>();
         if(rootNode.path("data").path("users").isArray())
         {
             ArrayNode userArrayNode =(ArrayNode) rootNode.path("data").path("users");
             for (JsonNode user:userArrayNode)
             {
                 if(user.path("orders").path(0).path("items").isArray())
                 {
                     ArrayNode itemsArrayNode =(ArrayNode) user.path("orders").path(0).path("items");
                     for(JsonNode item:itemsArrayNode)
                     {
                         ObjectMapper mapper= new ObjectMapper();
                         items.add(mapper.treeToValue(item,Item.class));
                     }
                 }
             }
         }
        System.out.println("==================================");
        System.out.println(items);
    }

    private void addNewRole(JsonNode rootNode) {
        ArrayNode userArrayNode = (ArrayNode) rootNode.path("data").path("users");
        for (JsonNode user: userArrayNode)
        {
            if(user.path("roles").isArray())
            {
                ArrayNode rolesArray=(ArrayNode)user.path("roles");
                rolesArray.add("Employee");
            }
        }
        System.out.println(rootNode);
    }

    private void totalOrderAmountAfterDiscount(JsonNode rootNode) {
         Integer reducedAmount=0;
         ArrayNode userArrayNode = (ArrayNode) rootNode.path("data").path("users");
         for(JsonNode user:userArrayNode)
         {
             ArrayNode ordersArrayNode=(ArrayNode) user.path("orders");
             for (JsonNode order: ordersArrayNode)
             {
                 ArrayNode itemArrayNode =(ArrayNode) order.path("items");
                 for(JsonNode item:itemArrayNode)
                 {
                     Integer itemAmount =item.path("price").path("amount").asInt();
                     ArrayNode discountArrayNode =(ArrayNode) item.path("price").path("discounts");
                     Integer totalDiscounts =0;
                     if(discountArrayNode.size()>0)
                     {
                         for(JsonNode discount:discountArrayNode) {
                             totalDiscounts += discount.path("value").asInt();
                         }
                     }

                     itemAmount-=totalDiscounts;
                     reducedAmount+=itemAmount;


                 }
             }
         }
        System.out.println("==================================");
        System.out.println(reducedAmount);
    }

    private void getSecondLoginCity(JsonNode rootNode) {
         String cityName="";
        ArrayNode userArrayNode = (ArrayNode) rootNode.path("data").path("users");
        for (JsonNode user: userArrayNode)
        {
            ArrayNode loginHistoryArrayNode =(ArrayNode) user.path("security").path("loginHistory");
           cityName += loginHistoryArrayNode.path(1).path("location").path("city").asText();
        }
        System.out.println("==================================");
        System.out.println(cityName);

    }

    public void firstUserPrimaryEmail(JsonNode rootNode)
     {
         String email="";
         ArrayNode userArrayNode= (ArrayNode) rootNode.path("data").path("users");
         for (JsonNode user :userArrayNode)
         {
             ArrayNode emailArrayNode=(ArrayNode) user.path("profile").path("emails");
             for(JsonNode emailNode:emailArrayNode)
             {
                 email=emailNode.path("value").asText();
                 break;

             }

         }
         System.out.println("==================================");
         System.out.println(email);

     }
     public void countLoginHistory(JsonNode rootNode)
     {
         Integer totalLoginHistory=-1;
         ArrayNode userArrayNode = (ArrayNode) rootNode.path("data").path("users");
         for (JsonNode user: userArrayNode)
         {
              ArrayNode loginHistoryArrayNode =(ArrayNode) user.path("security").path("loginHistory");
              totalLoginHistory=loginHistoryArrayNode.size();
         }
         System.out.println("==================================");
         System.out.println(totalLoginHistory);



     }
}
