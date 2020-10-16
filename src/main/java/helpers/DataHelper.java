package helpers;

import com.github.javafaker.Faker;

import static utilities.Log.info;

public class DataHelper {
    static Faker faker = new Faker();

    public static String email() {
        String email = faker.internet().emailAddress().replace("'", "");
        info("\tCreate new random email: " + email);
        return email;
    }

    public static String articleText() {
        String articleText = faker.harryPotter().quote();
        info("\tCreate new random article text: " + articleText);
        return articleText;
    }

    public static String url() {
        String name = faker.internet().url().replace("'", "");
        info("\tCreate new random url: " + name);
        return name;
    }

    public static String randomString() {
        String s = faker.letterify("??????????????").toLowerCase().replace("'", "");
        info("\tCreate new random url: " + s);
        return s;
    }
}
