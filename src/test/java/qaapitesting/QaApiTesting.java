package qaapitesting;

import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.AfterClass;

import static io.restassured.RestAssured.given;

public class QaApiTesting {
	ResponseSpecification obj = null;

    @BeforeClass
    public void setResponseSpecification()
    {
        ExtendReportBase.createReport();

        RestAssured.baseURI = Config.serverURL;

        obj = RestAssured.expect();

        obj.contentType(ContentType.JSON);

        obj.statusCode(200);

        obj.time(Matchers.lessThan(5000L));

        obj.statusLine("HTTP/1.1 200 ");

    }


    @Test
    public void checkUser()
    {
        ExtendReportBase.test = ExtendReportBase.reports.startTest("Check User Test","Create Customer with valid data");
        JSONObject params = new JSONObject();
        params.put("name","Sidharth");
        params.put("job","dev");
        ExtendReportBase.test.log(LogStatus.INFO, "Created a request payload",params.toJSONString());
        String responseMessage = given().header("Content-Type","Application/json").body(params.toJSONString()).when().post("/api/users").then().extract().path("name");
        System.out.println(responseMessage);
    }

    @AfterClass
    public void closeReport()
    {
        ExtendReportBase.reports.flush();
    }



}
