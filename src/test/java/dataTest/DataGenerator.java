package dataTest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static final Faker fake = new Faker(new Locale("en"));

    private DataGenerator() {

    }

    private static void sendRequestSpec(RegistUser user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);

    }

    public static String randLogin() {
        String login = fake.name().username();
        return login;
    }

    public static String randPassword() {
        String password = fake.internet().password();
        return password;
    }

    public static class Regist {
        private Regist() {
        }

        public static RegistUser generetUser(String status) {
            var user = new RegistUser(randLogin(), randPassword(), status);
            return user;
        }

        public static RegistUser generetRegUser(String status) {
            var registerUser = generetUser(status);
            sendRequestSpec(registerUser);
            return registerUser;
        }
    }

    @Value
    public static class RegistUser {
        String login;
        String password;
        String status;
    }


}
