# Autism Card API

API REST em Java com Spring Boot para cadastro e gerenciamento de carteirinhas de identificação para pessoas com Transtorno do Espectro Autista (TEA). O sistema fornece endpoints para criar, atualizar, consultar e excluir informações, e gera dados necessários para que o frontend exiba a carteirinha com QR Code funcional. 

---

## Funcionalidades

- Cadastro de pessoas autistas com CPF como identificador único  
- CRUD completo (Create, Read, Update, Delete)  
- Validação de campos como CPF e datas  
- Integração com banco de dados MySQL  
- Documentação com Swagger via Springdoc OpenAPI

---

## Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Springdoc OpenAPI

---

## Como rodar o projeto

1. Clone o repositório
   
 ```git
    git clone https://github.com/seu-usuario/autism-card-api.git
    cd autism-card-api
```

  3. Crie o banco de dados no MySQL

 ```sql
 CREATE DATABASE autism_card_db;
```

 4. Configure o arquivo application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/autism_card_db
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```
5. Rode a aplicação

```bash
./mvnw spring-boot:run
```
---

## Endpoints Principais

| Método | Endpoint           | Descrição                        |
|--------|--------------------|----------------------------------|
| GET    | /cards             | Lista todas as carteirinhas      | 
| GET    | /cards/{cpf}       | Busca uma carteirinha por CPF    | 
| POST   | /cards             | Cria uma nova carteirinha        |
| PUT    | /cards/{cpf}       | Atualiza uma carteirinha existente | 
| DELETE | /cards/{cpf}       | Remove uma carteirinha do sistema|

---

## Exemplo de Json para Post

```json
{
  "cpf": "12345678900",
  "fullName": "Ana Souza",
  "birthDate": "2010-03-22",
  "phone": "11991234567",
  "cid": "F84.0",
  "reportLink": "https://example.com/laudo/ana.pdf",
  "guardianName": "Carlos Souza",
  "guardianPhone": "11999887766"
}
```
---
## Swagger

Documentação automática disponível em: 

 📄 - http://localhost:8080/swagger-ui.html  
 
 📘 - http://localhost:8080/api-docs

---

## Autor

Desenvolvido por [Nayane Silva]  

📧 - nayanecosilva@gmail.com  

🔗 - https://www.linkedin.com/in/nayanecosta/

---
