<div align="center">

# 🛒 Lojinha API RESTful

[![Java Version](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=openapi-initiative&logoColor=black)](http://localhost:8080/swagger-ui.html)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)

*API RESTful robusta desenvolvida em Java com Spring Boot para gerenciamento completo de catálogo de produtos e categorias de estoque.*

</div>

<br />

## 📌 Sobre o Projeto

A **Lojinha API** foi projetada focando em **boas práticas de desenvolvimento de software**, padrões da indústria e arquitetura limpa em camadas (*Clean Architecture / Layered Architecture*). 

A aplicação isola completamente o domínio da infraestrutura, utiliza a camada de **DTOs** para tráfego seguro de dados e conta com **tratamento global de exceções** centralizado.

---

## ✨ Destaques da Arquitetura & Tecnologias

- ⚙️ **Java 21 & Spring Boot 3.2.5** - Base sólida e moderna com o ecossistema Spring.
- 📦 **Padrão DTO (Data Transfer Objects)** - Isolamento total entre entidades JPA (`@Entity`) e a API pública.
- ✅ **Bean Validation** - Validação declarativa de entrada de dados (`@Valid`, `@NotBlank`, `@Positive`).
- 🛡️ **Global Exception Handler** - Tratamento centralizado de erros com respostas HTTP padronizadas via `@RestControllerAdvice`.
- 📖 **Documentação Automática com Swagger / OpenAPI** - Interface visual interativa para teste e navegação das rotas.
- 💾 **Persistência de Dados** - Spring Data JPA com banco de dados H2 em memória.

---

## 🏗️ Estrutura de Pacotes

```text
com.lojinha.api
├── 📁 controller     # Endpoints REST e controle de requisições HTTP
├── 📁 dto            # Objetos de transferência de dados (Request / Response)
├── 📁 model          # Entidades de domínio mapeadas com JPA
├── 📁 repository     # Interfaces de persistência (Spring Data JPA)
└── 📁 service        # Camada de regras de negócio e mapeamento DTO <-> Entidade
