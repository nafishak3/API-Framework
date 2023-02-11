package UtilityResources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class ReusableMethods {
    static RequestSpecification requestSpecification;
    JsonPath js;

    public RequestSpecification requestSpecification() throws FileNotFoundException {
        if(requestSpecification == null) {
        PrintStream log =  new PrintStream(new FileOutputStream("Logging.txt"));

            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(getConfigProperty("baseurl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecification;
        }
        return requestSpecification;
    }
    public static String getConfigProperty(String baseurl) {
        Properties prop = new Properties();
        try {
        FileInputStream fis = new FileInputStream("src/test/java/UtilityResources/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baseurl = prop.getProperty("baseurl");
        return baseurl;

    }
    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }



}
