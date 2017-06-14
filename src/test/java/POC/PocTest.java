package POC;

import Utility.Helper;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import cucumber.api.java.After;

public class PocTest {

	public static String baseURI;
	public Response ResP;
	public static ExtentTest test;
	static ExtentHtmlReporter htmlReporter;
	static String RootFolder = System.getProperty("user.dir");
	public static ExtentReports extent = new ExtentReports();
	static Properties Property = new Properties();
	ExtentTest scenario;
	String URI;
	ExtentTest Extst;
	RequestSpecification Rsp;


	@Before
	public static void StatupConfig() throws IOException {
		FileInputStream PropPath = new FileInputStream("src/test/java/GenericRepository.properties");
		Property.load(PropPath);
		
		htmlReporter = new ExtentHtmlReporter(RootFolder + Property.getProperty("TestReport"));
		extent.attachReporter(htmlReporter);
		htmlReporter.getSystemAttributeContext();
		htmlReporter.setAppendExisting(true);
		htmlReporter.getStartTime();
		htmlReporter.getEndTime();
		htmlReporter.config().setReportName("API_Automation_Report");
		htmlReporter.config().setDocumentTitle("RestAPiReport");

	}

	@Given("^URI is \"([^\"]*)\"$")
	public void uri_is(String Endpoint) throws Throwable {

		
		//String tst13= "http://petstore.swagger.io/v2/store/inventory";
		
		test = extent.createTest("Validate URI" + Endpoint);
		Extst = test.log(Status.INFO, "Initiate Testcase with " + Endpoint);
		URI = Endpoint;
		scenario = test.createNode(new GherkinKeyword("Scenario"), "Validate API ");
		scenario.createNode(new GherkinKeyword("Given"), "API URI " + Endpoint).pass("pass");
	}

	@When("^Perform GET request$")
	public void perform_GET_request() throws Throwable {

		ResP = given().get(URI);
		scenario.createNode(new GherkinKeyword("When"), "Made GET Request with :" + URI).pass("pass");
		Extst.log(Status.INFO, "Made GET Request with " + ResP.print());
	}

	@Then("^Validate response \"([^\"]*)\"$")
	public void validate_response(String ResponseCode) throws Throwable {

		ResP.then().assertThat().statusCode(200).log().ifValidationFails();
		Extst.log(Status.PASS, "Validate Request");
		scenario.createNode(new GherkinKeyword("Then"), "Validate Response Code " + ResponseCode).pass(ResponseCode);
	}

	@When("^Perform POST request$")
	public void perform_POST_request() throws Throwable {

		scenario.createNode(new GherkinKeyword("When"), "Made POST Request with :" + URI).pass("pass");
		Extst.log(Status.PASS, "Post Request Made");
		ResP = Rsp.post(URI);
	}

	@Then("^Use sheet \"([^\"]*)\" and (\\d+) and (\\d+)$")
	public void use_sheet_and_and(String Sheet, int Row, int Column) throws Throwable {

		Extst.log(Status.PASS, "Validate Request");

		String cellvalJson = Helper.getCellValue(RootFolder + Property.getProperty("JsonExcellSheet"), Sheet, Row,
				Column);
		System.out.println("Json" + cellvalJson);
		scenario.createNode(new GherkinKeyword("Then"), "Json Body " + cellvalJson).info("Json Body");
		Rsp = given().contentType(ContentType.JSON).and().body(cellvalJson).log().all();
	}

	@Then("^verify the (\\d+)$")
	public void verify_the(int ResponseCode) throws Throwable {

		String RCode = Integer.toString(ResponseCode);
		scenario.createNode(new GherkinKeyword("Then"), "Validate Response Code " + ResponseCode).pass(RCode);
		ResP.then().assertThat().statusCode(ResponseCode).log().all();

	}

	@After
	public static void TearDown() {
		extent.flush();

	}

}
