package StepDefinations;

import EnumResources.EnumAPI;
import UtilityResources.ReusableMethods;
import UtilityResources.TestDataBuild;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class stepDefinition {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    ReusableMethods reusableMethods = new ReusableMethods();
    static String placeId;


    @Given("Add a place Payload {string} {string} {string}")
    public void add_a_place_payload(String name, String language, String address) throws FileNotFoundException {

        requestSpecification = given().spec(testDataBuild.requestSpecification())
                .body(testDataBuild.addPlaceReturn(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String httpResources, String method) {

//When we call .valueOf it will call the constructor
        EnumAPI enumAPI = EnumAPI.valueOf(httpResources);
        String httResources = enumAPI.getResources();
        System.out.println(httResources);

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST")) {
            response = requestSpecification
                    .when().post(httResources);
        } else if (method.equalsIgnoreCase("GET")) {
            response = requestSpecification
                    .when().get(httResources);
        }

    }

    @Then("the response with API call got success code {int}")
    public void the_response_with_api_call_got_success_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @And("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        Assert.assertEquals(reusableMethods.getJsonPath(response,keyValue), expectedValue);

    }

    @And("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws FileNotFoundException {
//        prepare request spec
        placeId = reusableMethods.getJsonPath(response, "place_id");
        requestSpecification = given().spec(testDataBuild.requestSpecification()).queryParams("place_id", placeId);
        user_calls_with_post_http_request(resource, "GET");
        String name = reusableMethods.getJsonPath(response, "name");
        Assert.assertEquals(name, expectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws FileNotFoundException {
        requestSpecification = given().spec(testDataBuild.requestSpecification())
                .body(testDataBuild.deletePlacePayload(placeId));
    }
}