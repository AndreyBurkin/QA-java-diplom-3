package serialization;

import Constants.Constant;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pens.PensUserAPI;
import url.UrlApiBase;

import static io.restassured.RestAssured.given;

//API запросы для авторизации и удаления пользователя
public class UserSerialization extends UrlApiBase {

    @Step("Удаление пользователя (DELETE /api/auth/user)")
    public static Response deleteUser(String accessToken) {
        setUp();
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(Constant.BURGER_API_USER_DELETE);
    }

    @Step("Авторизация пользователя (POST /api/auth/login)")
    public static Response loginUser(PensUserAPI pensUserAPI) {
        setUp();
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(pensUserAPI)
                .when()
                .post(Constant.BURGER_API_USER_AUTH);
    }

    @Step("Создание пользователя POST /api/auth/register")
    public static Response createUser(PensUserAPI pensUserAPI) {
        setUp();
        return given()
                .header("Content-type", "application/json")
                .body(pensUserAPI)
                .when()
                .post(Constant.BURGER_API_USER_CREATE);
    }
}
