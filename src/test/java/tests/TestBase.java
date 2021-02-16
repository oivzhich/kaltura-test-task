package tests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.PropertiesConfiguration;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

public class TestBase {

    protected static final Logger LOG = Logger.getLogger(TestBase.class);

    protected static final PropertiesConfiguration conf =
            ConfigFactory.create(PropertiesConfiguration.class, System.getProperties());

    protected Response r;

    protected String getBaseURL() {
        return conf.env();
    }

    @BeforeClass(alwaysRun = true, description = "Prepare test environment")
    public void beforeClass() {
        LOG.info("-------------------------------- Test suite '" + this.getClass().getSimpleName() +
                "' started ------------------------------");
    }

    @BeforeTest(alwaysRun = true)
    public void setParameters() {
        LOG.info("-------------------------------- Test '" + this.getClass().getSimpleName() +
                "' started ------------------------------");

        RestAssured.config =
                RestAssuredConfig.config().objectMapperConfig(objectMapperConfig().jackson2ObjectMapperFactory(
                        (type, s) -> {
                            ObjectMapper om = new ObjectMapper().findAndRegisterModules();
                            om.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
                            return om;
                        }
                ));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        String testName = method.getName();
        LOG.info("-------------------------------- Test method'" + testName +
                "' started ------------------------------");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        String status;
        switch (result.getStatus()) {
            case 1:
                status = "SUCCESS";
                break;
            case 2:
                status = "FAIL";
                break;
            case 3:
                status = "SKIPPED";
                break;
            default:
                status = null;

        }
        LOG.info("-------------------------------- Test method'"
                + result.getName() + "' finished with status " + status + " ------------------------------");
    }
}
