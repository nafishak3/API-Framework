package StepDefinations;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Hooks {

    @Before("@DeletePlace")

    public void beforeScenario() throws IOException {
//        here write a code that will return the placeID
//        Execute this code only when placeId is null
        stepDefinition stepDefinition = new stepDefinition();
        if(stepDefinition.placeId == null) {
            stepDefinition.add_a_place_payload("Nafisha", "idk", "hell");
            stepDefinition.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            stepDefinition.verify_place_id_created_maps_to_using("Nafisha", "GetPlaceAPI");
        }
    }

}
