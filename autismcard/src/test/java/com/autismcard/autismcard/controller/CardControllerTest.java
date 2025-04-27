package com.autismcard.autismcard.controller;

import com.autismcard.autismcard.dto.CardRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.time.LocalDate;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
class CardControllerTest {

  @LocalServerPort
  private int port;

  private static String createdUuid;
  private static final String BASE_PATH = "/api/cards";

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "http://localhost";
  }

  // Testa a criação de uma nova carteirinha
  @Test
  @Order(1)
  void testCreateCard() {
    CardRequestDto request = new CardRequestDto(
        "83336947057",
        "Nome Completo",
        LocalDate.of(2000, 1, 1),
        "CID-123",
        "11999999999",
        "http://link.com/relatorio",
        "Responsavel Nome",
        "11988888888"
    );

    Response response = RestAssured.given()
        .port(port)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post(BASE_PATH)
        .then()
        .statusCode(201)
        .body("cpf", Matchers.equalTo("833.***.***-57"))
        .body("fullName", Matchers.equalTo("Nome Completo"))
        .extract().response();

    // Armazena o uuid criado para outros testes
    createdUuid = response.jsonPath().getString("id");
  }

  // Testa a consulta pelo CPF
  @Test
  @Order(2)
  void testGetCardByCpf() {
    RestAssured.given()
        .port(port)
        .when()
        .get(BASE_PATH + "/cpf/83336947057")
        .then()
        .statusCode(200)
        .body("id", Matchers.equalTo(createdUuid))
        .body("cpf", Matchers.equalTo("833.***.***-57"));
  }

  // Testa a atualização da carteirinha
  @Test
  @Order(3)
  void testUpdateCard() {
    CardRequestDto request = new CardRequestDto(
        "83336947057",
        "Nome Atualizado",
        LocalDate.of(2000, 1, 1),
        "CID-123",
        "11977777777",
        "http://link.com/novo-relatorio",
        "Responsavel Atualizado",
        "11966666666"
    );

    RestAssured.given()
        .port(port)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .put(BASE_PATH + "/" + createdUuid)
        .then()
        .statusCode(200)
        .body("fullName", Matchers.equalTo("Nome Atualizado"))
        .body("phone", Matchers.equalTo("11977777777"));
  }

  // Testa a listagem de todas as carteirinhas
  @Test
  @Order(4)
  void testGetAllCards() {
    RestAssured.given()
        .port(port)
        .when()
        .get(BASE_PATH)
        .then()
        .statusCode(200)
        .body("size()", Matchers.greaterThanOrEqualTo(1));
  }

  // Testa a exclusão da carteirinha
  @Test
  @Order(5)
  void testDeleteCard() {
    RestAssured.given()
        .port(port)
        .when()
        .delete(BASE_PATH + "/" + createdUuid)
        .then()
        .statusCode(204);
  }

  // Cenário: Criação com campos inválidos (ex.: data de nascimento no futuro)
  @Test
  @Order(1)
  void testCreateCardWithInvalidBirthDate() {
    CardRequestDto request = new CardRequestDto(
        "12345678901",
        "Nome Incorreto",
        LocalDate.now().plusDays(1), // data no futuro
        "CID-123",
        "11999999999",
        "http://link.com/relatorio",
        "Responsavel Nome",
        "11988888888"
    );

    RestAssured.given()
        .port(port)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post(BASE_PATH)
        .then()
        .statusCode(400);
  }

  // Cenário: Criação com CPF inválido (formato incorreto)
  @Test
  @Order(2)
  void testCreateCardWithInvalidCpf() {
    CardRequestDto request = new CardRequestDto(
        "123", // CPF inválido
        "Nome Completo",
        LocalDate.of(2000, 1, 1),
        "CID-123",
        "11999999999",
        "http://link.com/relatorio",
        "Responsavel Nome",
        "11988888888"
    );

    RestAssured.given()
        .port(port)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post(BASE_PATH)
        .then()
        .statusCode(400);
  }

  // Cenário: Consulta por CPF inexistente
  @Test
  @Order(3)
  void testGetCardByNonexistentCpf() {
    RestAssured.given()
        .port(port)
        .when()
        .get(BASE_PATH + "/cpf/00000000000")
        .then()
        .statusCode(404);
  }

  // Cenário: Atualização com UUID inexistente
  @Test
  @Order(4)
  void testUpdateCardWithNonexistentUuid() {
    CardRequestDto request = new CardRequestDto(
        "83336947057",
        "Nome Atualizado",
        LocalDate.of(2000, 1, 1),
        "CID-123",
        "11977777777",
        "http://link.com/novo-relatorio",
        "Responsavel Atualizado",
        "11966666666"
    );

    RestAssured.given()
        .port(port)
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .put(BASE_PATH + "/2147213f-76e4-4f67-8098-0159f2ac7bf6")
        .then()
        .statusCode(404);
  }

  // Cenário: Exclusão com UUID inexistente
  @Test
  @Order(5)
  void testDeleteCardWithNonexistentUuid() {
    RestAssured.given()
        .port(port)
        .when()
        .delete(BASE_PATH + "/2147213f-76e4-4f67-8098-0159f2ac7bf6")
        .then()
        .statusCode(404);
  }
}