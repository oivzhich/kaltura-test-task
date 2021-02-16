package tests;

import model.User;
import model.UserActionable;
import org.apache.http.HttpStatus;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterTest extends TestBase{

    UserActionable expectedUser;

    @BeforeTest
    public void beforeTest() {
        expectedUser = new UserActionable();
    }

    @Test(priority = 1)
    public void verifyUserRegistration() {
        r = expectedUser.register();
        r.then()
                .assertThat()
                .header("Content-Type", Matchers.equalTo("application/json"))
                .statusCode(HttpStatus.SC_OK)
                .body("executionTime", Matchers.notNullValue())
                .body("result", Matchers.hasKey("id"))
                .body("result", Matchers.hasKey("countryId"));

        User actualUser = r.getBody().jsonPath().getObject("result", User.class);

        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser.getUser());
    }

    @Test(priority = 2)
    public void verifyUserLoggedIn() {
        r = expectedUser.login();
        r.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("result.user", Matchers.hasKey("lastLoginDate"));

        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime lastLoginDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(r.jsonPath().get("result.user.lastLoginDate.toLong()")),
                TimeZone.getDefault().toZoneId()).truncatedTo(ChronoUnit.DAYS);
        MatcherAssert.assertThat(today, LocalDateTimeMatchers.sameDay(lastLoginDate));
    }

    @Test(priority = 3)
    public void verifyUserRegisteredAgain() {
        r = expectedUser.register();
        r.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("result.error.code", Matchers.equalTo("2014"))
                .body("result.error.message", Matchers.is("User exists"));
    }
}
