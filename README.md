# E-Commerce Application com Java e Spring Boot
Projeto de um sistema de ecommerce completo, desenvolvido como parte do curso **Formação em Java** do Full Stack Club.   
A Aplicação abrange desde a arquitetura MVC e APIs REST até a containerização com docker.

---

## Funcionalidades Principais
- **Catálogo de Produtos:** Visualização de produtos com filtro por categoria.
- **Carrinho de Compras:** Gerenciamento de itens em sessão.
- **Fluxo de Pedidos:** Checkout completo com criação de pedidos no banco de dados.
- **Painel Administrativo:** CRUD Completo para Produtos, Usuários, Pedidos.
- **API REST**: Endpoints para gerenciamento de recuross via HTTP
- **Containerização**: Ambiente de produção completo com Docker e Docker Compose.

---

## Stack Tecnológica
- **Backend**: Java 21, Spring Boot 3, Spring MVC, Spring Data JPA, Hibernate, Docker, Postgres
- **Frontend**: Thymeleaf, HTML, CSS
- **Banco de Dados:** Postgres (producao) / H2 (desenvolvimento/teste)
- **Build/Dependency:** Maven
- **Containerização:** Docker, Docker Compose
- **Testes:** Junit5, Mockito

## Hard Skills Desenvolvidas

Para construir este projeto, foram aplicadas e aprofundads as seguintes habilidades técnicas, que são altamente 
demandadas no mercado de desenvolvimento backend Java:

### Linguagem Java (21+):
- Uso de features modernas como **Records** para DTOs, **Stream API** para manipulação de coleções,
e  **Optionals** para tratamento de valores nulos.
### Ecossistema Spring:
- **Spring Boot:** Configuração de projetos, gerenciamento de dependencias e uso de profiles.
- **Spring MVC:** Arquitetura para aplicações web, com Controllers para gerencia requisicoes HTTP.
- **Spring Data JPA**: Persistencia de dados com padrao **Repository** e mapeamento objeto-relacional (ORM) com **Hibernate**.
### Template Engine:
- **Thymeleaf:** Renderização de paginas web no lado do servidor, integrando o backend com frontend HTML.
### APIs e Arquitetura:
- **Desing de APIs RESTful:** Construção de endpoints segundo os padroes REST.
- **Padrao DTO (Data Transfer Object):** Separação entre modelo de dominio e cama de API.
- **Arquitetura em 3 camadas:** Organização do codigo em Controller, Service, Repository.
### Banco de Dados:
- **PostgreSL:** Utilização como banco de dados de produção.
- **H2 Database:** Uso para testes automatizados e ambiente de desenvolvimento inicial.
### Testes Automatizados:
- **JUnit5 e Mockito:** Criação de testes unitarios e de integracao para agarantir a qualidade e a confiabilidade do codigo.
### Ferramentas de Build e Versionamento:
- **Maven:** Gerenciamento de dependencias e do cilco de vida do build do projeto.
- **Git e GitHub:** Controle de versao e hospedagem do codigo-fonte.
### Containerizacao (DevOps)
- **Docker e Docker Compose:** Criação de um ambiente de desenvolvimento e produção contairizado, garantindo consistencia e facilitando o deploy. 

## Como executar o projeto

### Pré-Requisitos
- Java 21
- Maven 3.6+
- Docker e Docker Compose

### Passos

1. **Clone o repositorio:**  
```bash
git clone git@github.com:nandofontelles/ecommerce-formacao-java.git
cd ecommerce-formacao-java
```

2. **Execute o ambiente Docker:**
*Isso irá iniciar o naco de dados PostgreSQL e a interface Adminer.*
```bash
docker-compose up -d
```

3. **Execute a aplicação Spring Boot:**
```bash
./mvnw spring-boot:run
```

4. **Acesse a aplicacao:**
- **Loja:** [http://localhost:8080](http://localhost:8080)
- **Adminer (DB):** [http://localhost:8081](http://localhost:8081)

---

## Endpoints da API
- `GET /api/products`: Lista de produtos.
- `PUT /api/proiducts/{id}/price`: Atualiza o preço de um produto.
- `Post /api/orders`: Cria um pedido.
- ... e outros.

## Comandos Principais

### Executar a aplicação

```bash
./mvnw sprint-boot:run
```

A aplicacao estará disponivel em: http://localhost:8080

### Executar com auto-login de administrador
```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=admin
```

Acesse diretamente: http://localhost:8080/admin (já logado como admin)

### Build do Projeto
```bash
./mvnw clean clean package
```

### Executar testes
```bash 
./mvnw test 
```

## H2 Console
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:ecommerce-db`
- **username**: `sa`
- **Password**: (vazio)

## Usuario ade Teste
O Sistema inicia com os seguintes usuarios cadastrados:

- **Admin**: admin@admin.com / admin@123
- **Joao**: joao@email.com / joao@123
- **Antonio**: antonio@email.com / antonio@123
- **Maria**: maria@email.com / maria@123 (CONTA SUSPENSA)
- **Lais**: lais@email.com / lais@123 (CONTA INATIVA)

## Solucao de Problemas

### Porta 8080 ja em uso (Windows)
**Opcao 1:** Encerrar o processo que esta usando a porta
```bash
# Encontrar o PID do processo
netstat -ano | findstr :8080

#Encerrar o processo (substituir <PID> pelo ID retornado)
taskkill /PID <PID> /F
```

**Opcao 2:** Alterar a posta da aplicacao
Adicione em `src/main/resources/application.properties`:
```properties
server.port=8081
```

**Opcao 3:** Executar temporariamente em outra porta
```bash
./mvnw spring-boot.run.arguments=--server.port=8081
```

