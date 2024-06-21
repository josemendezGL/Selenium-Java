package com.example.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;

public class APIHelper {

    private String apiKey;
    private String apiToken;

    public APIHelper(String apiKey, String apiToken) {
        this.apiKey = apiKey;
        this.apiToken = apiToken;
        RestAssured.baseURI = "https://api.trello.com/1";
    }

    public void deleteAllBoards() {
        Response response = given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .when()
                .get("/members/me/boards");
        List<String> boardIds = response.jsonPath().getList("id");
        for (String boardId : boardIds) {
            given()
                    .queryParam("key", apiKey)
                    .queryParam("token", apiToken)
                    .when()
                    .delete("/boards/" + boardId);
        }
    }

    public Response createNewBoard(String boardName) {
        return given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .queryParam("name", boardName)
                .contentType("application/json")
                .when()
                .post("/boards/");
    }

    public Response deleteBoard(String boardId) {
        return given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .when()
                .delete("/boards/" + boardId);
    }

    public Response sendGetRequestToOrganizationBoards() {
        return given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .when()
                .get("/organizations/userworkspace15786559/boards");
    }

    public Response getBoard(String boardId) {
        return given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .when()
                .get("/boards/" + boardId);
    }

    public Response updateBoardName(String boardId, String newName) {
        return given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .queryParam("name", newName)
                .contentType("application/json")
                .when()
                .put("/boards/" + boardId)
                .then()
                .log().all()
                .extract().response();

    }
}