package edu.practicum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserMethods {

    private static final String CREATE_USER = "/api/auth/register";
    private static final String LOGIN_USER = "/api/auth/login";
    private static final String DELETE_CHANGING_USER = "/api/auth/user";

    @Step("POST /api/auth/register - создание пользователя")
    public Response create(UserApi user){
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(CREATE_USER);
    }

    @Step("POST /api/auth/login - авторизация пользователя + почта, имя, пароль")
    public Response login(UserApi user){
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_USER);
    }

    @Step("POST /api/auth/login - получение токена & почта, имя, пароль")
    public String getToken(UserApi user){
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_USER);
        return response.jsonPath().getString("accessToken");
    }

    @Step("DELETE /api/auth/user - удаление пользователя")
    public Response delete(UserApi user){
        String token = getToken(user);
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .delete(DELETE_CHANGING_USER);
    }
}