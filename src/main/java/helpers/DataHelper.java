package helpers;

import com.github.javafaker.Faker;

import static utilities.Log.info;

public class DataHelper {
    static Faker faker = new Faker();

    public static String email() {
        String email =  faker.internet().emailAddress().replace("'","");;
        info("\tCreate new random email: "+email);
        return email;
    }
    public static String title(){
        String title =  faker.book().title().replace("'","");
        info("\tCreate new random title: "+title);
        return title;
    }

    public static String articleText() {
        String articleText = faker.harryPotter().quote();
        info("\tCreate new random article text: "+articleText);
        return articleText;
    }

    public static String name(){
        String name = faker.funnyName().name().replace("'","");;
        info("\tCreate new random name: "+name);
        return name;
    }
    public static String word(){
        String name = faker.lorem().word().replace("'","");;
        info("\tCreate new random word: "+name);
        return name;
    }
    public static String url(){
        String name = faker.internet().url().replace("'","");;
        info("\tCreate new random url: "+name);
        return name;
    }

    public static String pid() {
        return faker.bothify("??######");
    }
}
