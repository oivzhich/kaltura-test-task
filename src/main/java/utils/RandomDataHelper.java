package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

//Based on Faker lib https://github.com/DiUS/java-faker
public class RandomDataHelper {

    public static final Faker faker = new Faker(new Locale("en-GB"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    private static String getQuestionMarks(int countQuestions) {
        return "" + "?".repeat(Math.max(0, countQuestions));
    }

    public static String getUserName() {
        return faker.name().username();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getAddress() {
        return faker.address().fullAddress();
    }

    public static String getCity() {
        return faker.address().city();
    }

    public static String getExternalId() {
        return faker.number().digits(10);
    }
}
