package POC;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.asserts.SoftAssert;;
public class TEST007 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="http://localhost:9001/mytoll/api";
		RestAssured.basePath="/v1/location/reference/";
		
	//Response rsp=given().get("/inventory");
	given().log().all().get("freight_point/tolcarrier/rrw").then().assertThat().statusCode(200).and().contentType(ContentType.JSON).log().all();
	
/*	SoftAssert validate=new SoftAssert();
	validate.assertEquals(rsp.getStatusCode(), 200 ,"Response code not get");
	validate.assertEquals(rsp.getStatusCode(), 200 , "Response code matched");
	validate.assertAll();*/
	}

	
	
	
	
}
