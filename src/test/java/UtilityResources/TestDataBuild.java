package UtilityResources;

import POJO_Serialization.AddPlace;
import POJO_Serialization.Location;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild extends ReusableMethods{


    public AddPlace addPlaceReturn(String name, String lanaguge, String address){
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace addPlaceObject = new AddPlace();

        addPlaceObject.setAccuracy(50);
        addPlaceObject.setName(name);//Frontline house
        addPlaceObject.setPhone_number("(+91) 983 893 3937");
        addPlaceObject.setAddress(address);//29, side layout, cohen 09
        addPlaceObject.setWebsite("http://google.com");
        addPlaceObject.setLanguage(lanaguge);//French-IN

        List<String> typesList = new ArrayList<>();
        typesList.add("shoe park");
        typesList.add("shop");
        addPlaceObject.setTypes(typesList);

        Location locationObject = new Location();
        locationObject.setLat(-38.383494);
        locationObject.setLng(33.427362);
        addPlaceObject.setLocation(locationObject);
        return addPlaceObject;
    }
    public String deletePlacePayload(String place_ID){
       return "{\n" +
               "    \"place_id\":\""+place_ID+"\"\n" +
               "}";
    }
}
