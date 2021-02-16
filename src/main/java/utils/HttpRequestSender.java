package utils;

import config.PropertiesConfiguration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class HttpRequestSender {

    protected static PropertiesConfiguration conf =
            ConfigFactory.create(PropertiesConfiguration.class, System.getProperties());

    private static final Logger LOGGER = Logger.getLogger(HttpRequestSender.class);

    public static String getBaseURI() {
        return conf.env();
    }

    public static RequestSpecification getMinimumRequestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(getBaseURI())
                .setAccept(ContentType.JSON)
                .log(LogDetail.BODY)
                .log(LogDetail.HEADERS)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();
    }

    /***
     * Make a POST request
     * @param call - endpoint URL
     * @param parameters - body parameters as Object
     * @return - Response
     */
    public static Response post(String call, Object parameters) {
        if (call.contains("null")) {
            LOGGER.error("this is a error causing call");
        }
        Response r = null;
        try {
            r = given()
                    .spec(getMinimumRequestSpecification())
                    .body(parameters)
                    .when().post(call);
            onRequestSuccess("POST", r);
        } catch (Exception e) {
            onRequestFail("POST", e);
        }
        return r;
    }

    private static void onRequestSuccess(String type, Response response) {
        LOGGER.info("Response headers: \n" + response.headers().toString());
        LOGGER.info("Response:\n");
        response.prettyPrint();
        LOGGER.info("---------------------------- SUCCESS Of " + type + " ----------------");
    }

    public static void onRequestFail(String type, Exception e) {
        LOGGER.error(e.getMessage(), e);
        LOGGER.info("---------------------------- FAIL Of " + type + " ----------------");
    }
}
