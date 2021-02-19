import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;
import org.testng.annotations.Test;

public class Get {
     @Test
    public void getUserList(){


         RestAssured.baseURI = "https://reqres.in/api/users";

         RequestSpecification httpRequest = RestAssured.given();

         Response response = httpRequest.get("/3");

         System.out.println("Response Body =>" +response.asString());
         System.out.println(response.getHeader("server"));
         System.out.println(response.getHeaders());


         String contentType = response.header("Content-Type");
         //Assert.assertEquals(contentType /* actual value */, "application/json; charset=utf-8" /* expected value */);
          Assert.assertEquals(contentType, "application/json; charset=utf-8");

          String serverType = response.header("Server");
          Assert.assertEquals(serverType,"cloudflare");

          int responsecode = response.getStatusCode();
          Assert.assertEquals(responsecode, 200);

          String contentEncoding = response.header("Content-Encoding");
          Assert.assertEquals(contentEncoding, "gzip");


          ResponseBody body = response.getBody();

          String bodyAsString = body.asString();
          Assert.assertEquals(bodyAsString.contains("Emma"),true);


         JsonPath jsonPathEvaluator = response.jsonPath();


         String name = jsonPathEvaluator.get("data.first_name");

         // Validate the response
         Assert.assertEquals("Emma", name);







     }







}




