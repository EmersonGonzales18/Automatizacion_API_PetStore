Feature: API de PetStore - Order

  Background: Configuración inicial
    Given que el servicio de la tienda de mascotas está disponible

  @Consulta1
  Scenario Outline: Creación de Order
    When envío una solicitud POST a "/store/order" con los siguientes datos:
      | id         | <id>       |
      | petId      | <petId>    |
      | quantity   | <quantity> |
      | shipDate   | <shipDate> |
      | status     | <status>   |
      | complete   | <complete> |
    Then la respuesta debe tener un status code 200
    And validar que el body de la respuesta contenga los datos enviados
    Examples:
      | id | petId | quantity | shipDate         | status | complete |
      | 10 | 50    | 2        | 2024-09-01T10:00 | placed | true     |
      | 20 | 51    | 5        | 2024-09-01T10:00 | placed | true     |

  @Consulta2
  Scenario Outline: Consulta de Order
      When envío una peticion GET a "/store/order/<id>"
      Then la respuesta debe tener un status code 200
      And valida que el id es <id>
      And valida que el petId es <petId>
      And valida que la cantidad es <quantity>
      Examples:
        | id |  | petId | quantity |
        | 10 |  | 50    | 2        |
        | 20 |  | 51    | 5        |