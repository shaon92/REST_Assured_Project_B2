import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Customer {
    public Integer ID;
    public String Name;
    public String email;
    public String address;
    public String phone_number;
    Properties prop = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");

    public Customer() throws FileNotFoundException {
    }
    public void callingLoginAPI() throws ConfigurationException, IOException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res =
                given()
                        .contentType("application/json")
                        .body("{\n" +
                                "    \"username\":\"salman\",\n" +
                                "    \"password\":\"salman1234\"\n" +
                                "}")
                        .when()
                        .post("/customer/api/v1/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath resObj = res.jsonPath();
        String token = resObj.get("token");
        Utils.setEnvVariable("token", token);
        System.out.println(token);
    }

    public void callingCustomerListAPI() throws IOException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                .when()
                        .get("/customer/api/v1/list")
                .then()
                        .assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
        JsonPath resObj = response.jsonPath();
        int id = resObj.get("Customers[0].id");
        Assert.assertEquals(101,id);
    }

    public void callingCustomerSearchAPI(int givenId) throws IOException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .when()
                        .get("/customer/api/v1/get/"+givenId+"")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        System.out.println(response.asString());
        JsonPath resObj = response.jsonPath();
        int id = resObj.get("id");
        Assert.assertEquals(givenId,id);
    }

    public void generateCustomer() throws IOException, ConfigurationException {
        prop.load(file);
        RestAssured.baseURI = "http://randomuser.me";
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get("/api")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath resObj = response.jsonPath();
        ID = (int) Math.floor(Math.random()*(999999-100000)+1);
        Name  = "Shaon "+resObj.get("results[0].name.first");
        email = resObj.get("results[0].email");
        address = resObj.get("results[0].location.state");
        phone_number = resObj.get("results[0].cell");
        Utils.setEnvVariable("ID", ID.toString());
        Utils.setEnvVariable("Name",Name);
        Utils.setEnvVariable("Email",email);
        Utils.setEnvVariable("Address",address);
        Utils.setEnvVariable("phone_number",phone_number);
        System.out.println(response.asString());
    }

    public void callingCreateCustomerAPI() throws IOException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .body("{\n" +
                                " \"id\": "+prop.getProperty("ID")+",\n" +
                                " \"name\": \""+prop.getProperty("Name")+"\",\n" +
                                " \"email\": \""+prop.getProperty("Email")+"\",\n" +
                                " \"address\": \""+prop.getProperty("Address")+"\",\n" +
                                " \"phone_number\": \""+prop.getProperty("phone_number")+"\"\n" +
                                "}")
                        .when()
                        .post("/customer/api/v1/create")
                        .then()
                        .assertThat().statusCode(201).extract().response();
        System.out.println(response.asString());
    }
    public void callingUpdateCustomerAPI(int givenID, String givenName, String givenEmail, String givenAddress, String givenPhoneNumber) throws IOException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .body("{\n" +
                                " \"id\": "+givenID+",\n" +
                                " \"name\": \""+givenName+"\",\n" +
                                " \"email\": \""+givenEmail+"\",\n" +
                                " \"address\": \""+givenAddress+"\",\n" +
                                " \"phone_number\": \""+givenPhoneNumber+"\"\n" +
                                "}")
                        .when()
                        .put("/customer/api/v1/update/"+givenID+"")
                        .then()
                        .assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
    }
    public void callingCustomerDeleteAPI(int givenId) throws IOException {
        prop.load(file);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response response =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("token"))
                        .when()
                        .delete("/customer/api/v1/delete/"+givenId+"")
                        .then()
                        .assertThat().statusCode(200).extract().response();
        System.out.println(response.asString());
    }
}
