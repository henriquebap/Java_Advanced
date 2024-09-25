# Restaurante Reviews - README - Projeto Diamante

## Autor

* **Nome:** Henrique Oliveira Baptista
* **RM:** 97796
* **LinkedIn:** [Henrique Baptista](https://www.linkedin.com/in/henrique-baptista777/)
* **GitHub:** [henriquebap](https://github.com/henriquebap)
* **Email:** [henriquebaptista2003@gmail.com](mailto:henriquebaptista2003@gmail.com)

## Objetivos do Projeto

Este projeto tem como objetivo criar uma aplicação web para avaliação de restaurantes. Usuários podem se registrar, fazer pedidos e avaliações de restaurantes. A aplicação utiliza autenticação e autorização JWT para garantir a segurança.

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:

```
restaurante-reviews
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── exemplo
│   │   │           └── restaurantereviews
│   │   │               ├── controller
│   │   │               ├── entity
│   │   │               ├── repository
│   │   │               ├── security
│   │   │               └── RestauranteReviewsApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── db
│   │           └── migration
│   │               └── V1__Initial_setup.sql
│   └── test
│       └── java
│           └── com
│               └── exemplo
│                   └── restaurantereviews
│                       └── RestauranteReviewsApplicationTests.java
└── pom.xml
```

## Entidades e Relacionamentos

1. **Usuário** (`usuarios_review`):

   - `id`: único, auto incrementável
   - `nome:` nome do usuário
   - `email:` email do usuário
   - `senha:` senha do usuário
   - `Relacionamento:` um usuário pode ter vários pedidos.
2. **Restaurante** (restaurantes):

   - `id:` único, auto incrementável
   - `nome:` nome do restaurante
   - `endereco: `endereço do restaurante
   - `Relacionamento:` um restaurante pode ter vários pedidos.
3. **Pedido** (`pedidos`):

   - `id:` único, auto incrementável
   - `data:` data do pedido
   - `valor:` valor do pedido
   - `restaurante_id:` referência ao restaurante associado
   - `usuario_id:` referência ao usuário associado
   - `Relacionamento:` um pedido leva a uma avaliação.
4. **Avaliação** (`avaliacoes`):

   - `id:` único, auto incrementável
   - `nota:` nota da avaliação (1 a 5)
   - `comentario:` comentário da avaliação
   - `pedido_id:` referência ao pedido associado
   - `Relacionamento:` uma avaliação está associada a um pedido.

### Ilustração dos Relacionamentos

```
Usuário (1) -> (N) Pedido (1) -> (1) Avaliação
Restaurante (1) -> (N) Pedido
```

## Tecnologias Utilizadas

- Spring Boot: Framework para desenvolvimento da aplicação.
- Spring Data JPA: Para persistência de dados.
- Spring Security: Para segurança.
- JWT (JSON Web Token): Autenticação e autorização.
- Lombok: Redução de código boilerplate.
- Flyway: Migração de banco de dados.
- H2 Database: Banco de dados em memória (desenvolvimento).
- PostgreSQL: Produção.

## Configuração Inicial e Banco de Dados

### Arquivo application.properties

```properties
spring.datasource.url=jdbc:h2:mem:restaurantesdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=validate

spring.flyway.baseline-on-migrate=true
```

### Script de Migração Flyway

`src/main/resources/db/migration/V1__Initial_setup.sql`

```sql
-- Cria a tabela de Usuarios
CREATE TABLE usuarios_review (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL
);

-- Cria a tabela de Restaurantes
CREATE TABLE restaurantes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  endereco VARCHAR(255) NOT NULL
);

-- Cria a tabela de Pedidos
CREATE TABLE pedidos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  data DATE NOT NULL,
  valor DECIMAL(10, 2) NOT NULL,
  restaurante_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id),
  CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios_review(id)
);

-- Cria a tabela de Avaliações
CREATE TABLE avaliacoes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nota INT NOT NULL CHECK (nota BETWEEN 1 AND 5),
  comentario TEXT,
  pedido_id BIGINT NOT NULL,
  CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);
```

## Endpoints da API

### Autenticação

#### Registrar Usuário

- URL: `/usuarios/registrar`
- Método: POST
- Requisição:
  ```json
  {
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "senha": "senha123"
  }
  ```
- Resposta:
  ```json
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "senha": "senha123"
  }
  ```

#### Login

- URL: `/auth/login`
- Método: POST
- Requisição:
  ```json
  {
    "username": "joao.silva@example.com",
    "password": "senha123"
  }
  ```
- Resposta:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
  ```

### Endpoints Protegidos (necessário Authorization: Bearer `<token>`)

#### Usuário

##### Obter Usuário por ID

- URL: `/usuarios/{id}`
- Método: GET
- Resposta:
  ```json
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@example.com"
  }
  ```

##### Atualizar Usuário

- URL: `/usuarios/{id}`
- Método: PUT
- Requisição:
  ```json
  {
    "nome": "João Pedro Silva",
    "email": "joao.pedro@example.com"
  }
  ```
- Resposta:
  ```json
  {
    "id": 1,
    "nome": "João Pedro Silva",
    "email": "joao.pedro@example.com"
  }
  ```

##### Deletar Usuário

- URL: `/usuarios/{id}`
- Método: DELETE

#### Restaurante

##### Listar Restaurantes

- URL: `/restaurantes`
- Método: GET
- Resposta:
  ```json
  [
    {
      "id": 1,
      "nome": "Restaurante A",
      "endereco": "Rua A, 123"
    },
    ...
  ]
  ```

##### Buscar Restaurantes por Nome

- URL: `/restaurantes/buscar`
- Método: GET
- Parâmetros: `?nome=Restaurante`
- Resposta:
  ```json
  [
    {
      "id": 1,
      "nome": "Restaurante A",
      "endereco": "Rua A, 123"
    },
    ...
  ]
  ```

##### Criar Restaurante

- URL: `/restaurantes`
- Método: POST
- Requisição:
  ```json
  {
    "nome": "Restaurante B",
    "endereco": "Rua B, 456"
  }
  ```
- Resposta:
  ```json
  {
    "id": 2,
    "nome": "Restaurante B",
    "endereco": "Rua B, 456"
  }
  ```

##### Obter Restaurante por ID

- URL: `/restaurantes/{id}`
- Método: GET
- Resposta:
  ```json
  {
    "id": 1,
    "nome": "Restaurante A",
    "endereco": "Rua A, 123"
  }
  ```

##### Atualizar Restaurante

- URL: `/restaurantes/{id}`
- Método: PUT
- Requisição:
  ```json
  {
    "nome": "Restaurante C",
    "endereco": "Rua C, 789"
  }
  ```
- Resposta:
  ```json
  {
    "id": 1,
    "nome": "Restaurante C",
    "endereco": "Rua C, 789"
  }
  ```

##### Deletar Restaurante

- URL: `/restaurantes/{id}`
- Método: DELETE

#### Pedido

##### Listar Pedidos

- URL: `/pedidos`
- Método: GET
- Resposta:
  ```json
  [
    {
      "id": 1,
      "data": "2023-09-25",
      "valor": 50.00,
      "restaurante": {
        "id": 1,
        "nome": "Restaurante A"
      },
      "usuario": {
        "id": 1,
        "nome": "João Silva"
      }
    },
    ...
  ]
  ```

##### Listar Pedidos de um Usuário

- URL: `/pedidos/usuario/{usuarioId}`
- Método: GET
- Resposta:
  ```json
  [
    {
      "id": 1,
      "data": "2023-09-25",
      "valor": 50.00
    },
    ...
  ]
  ```

##### Criar Pedido

- URL: `/pedidos`
- Método: POST
- Requisição:
  ```json
  {
    "data": "2023-09-25",
    "valor": 50.00,
    "restaurante": {
      "id": 1
    },
    "usuario": {
      "id": 1
    }
  }
  ```
- Resposta:
  ```json
  {
    "id": 1,
    "data": "2023-09-25",
    "valor": 50.00
  }
  ```

##### Obter Pedido por ID

- URL: `/pedidos/{id}`
- Método: GET
- Resposta:
  ```json
  {
    "id": 1,
    "data": "2023-09-25",
    "valor": 50.00,
    "restaurante": {
      "id": 1,
      "nome": "Restaurante A"
    },
    "usuario": {
      "id": 1,
      "nome": "João Silva"
    }
  }
  ```

##### Atualizar Pedido

- URL: `/pedidos/{id}`
- Método: PUT
- Requisição:
  ```json
  {
    "data": "2023-09-26",
    "valor": 60.00
  }
  ```
- Resposta:
  ```json
  {
    "id": 1,
    "data": "2023-09-26",
    "valor": 60.00
  }
  ```

##### Deletar Pedido

- URL: `/pedidos/{id}`
- Método: DELETE

#### Avaliação

##### Listar Avaliações

- URL: `/avaliacoes`
- Método: GET
- Resposta:
  ```json
  [
    {
      "id": 1,
      "nota": 5,
      "comentario": "Excelente!"
    },
    ...
  ]
  ```

##### Criar Avaliação

- URL: `/avaliacoes`
- Método: POST
- Requisição:
  ```json
  {
    "nota": 4,
    "comentario": "Bom!",
    "pedido": {
      "id": 1
    }
  }
  ```
- Resposta:
  ```json
  {
    "id": 2,
    "nota": 4,
    "comentario": "Bom!"
  }
  ```

##### Obter Avaliação por ID

- URL: `/avaliacoes/{id}`
- Método: GET
- Resposta:
  ```json
  {
    "id": 1,
    "nota": 5,
    "comentario": "Excelente!"
  }
  ```

##### Atualizar Avaliação

- URL: `/avaliacoes/{id}`
- Método: PUT
- Requisição:
  ```json
  {
    "nota": 3,
    "comentario": "Médio"
  }
  ```
- Resposta:
  ```json
  {
    "id": 1,
    "nota": 3,
    "comentario": "Médio"
  }
  ```

##### Deletar Avaliação

- URL: `/avaliacoes/{id}`
- Método: DELETE

## Instruções de Instalação e Execução

1. Clone o repositório:

```
	git clone https://github.com/henriquebap/Java_Advanced.git
```

1. Navegue até o diretório do projeto:
   <pre><div class="relative flex flex-col rounded-lg"><div class="text-text-300 absolute pl-3 pt-2.5 text-xs"></div><div class="pointer-events-none sticky my-0.5 ml-0.5 flex items-center justify-end px-1.5 py-1 mix-blend-luminosity top-0"><div class="from-bg-300/90 to-bg-300/70 pointer-events-auto rounded-md bg-gradient-to-b p-0.5 backdrop-blur-md"><button class="flex flex-row items-center gap-1 rounded-md p-1 py-0.5 text-xs transition-opacity delay-100 hover:bg-bg-200 opacity-60 hover:opacity-100"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" viewBox="0 0 256 256" class="text-text-500 mr-px -translate-y-[0.5px]"><path d="M200,32H163.74a47.92,47.92,0,0,0-71.48,0H56A16,16,0,0,0,40,48V216a16,16,0,0,0,16,16H200a16,16,0,0,0,16-16V48A16,16,0,0,0,200,32Zm-72,0a32,32,0,0,1,32,32H96A32,32,0,0,1,128,32Zm72,184H56V48H82.75A47.93,47.93,0,0,0,80,64v8a8,8,0,0,0,8,8h80a8,8,0,0,0,8-8V64a47.93,47.93,0,0,0-2.75-16H200Z"></path></svg><span class="text-text-200 pr-0.5">Copy</span></button></div></div><div><div class="code-block__code !my-0 !rounded-lg !text-sm !leading-relaxed"><code><span><span>cd restaurante-reviews</span></span></code></div></div></div></pre>
2. Instale as dependências:
   <pre><div class="relative flex flex-col rounded-lg"><div class="text-text-300 absolute pl-3 pt-2.5 text-xs"></div><div class="pointer-events-none sticky my-0.5 ml-0.5 flex items-center justify-end px-1.5 py-1 mix-blend-luminosity top-0"><div class="from-bg-300/90 to-bg-300/70 pointer-events-auto rounded-md bg-gradient-to-b p-0.5 backdrop-blur-md"><button class="flex flex-row items-center gap-1 rounded-md p-1 py-0.5 text-xs transition-opacity delay-100 hover:bg-bg-200 opacity-60 hover:opacity-100"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" viewBox="0 0 256 256" class="text-text-500 mr-px -translate-y-[0.5px]"><path d="M200,32H163.74a47.92,47.92,0,0,0-71.48,0H56A16,16,0,0,0,40,48V216a16,16,0,0,0,16,16H200a16,16,0,0,0,16-16V48A16,16,0,0,0,200,32Zm-72,0a32,32,0,0,1,32,32H96A32,32,0,0,1,128,32Zm72,184H56V48H82.75A47.93,47.93,0,0,0,80,64v8a8,8,0,0,0,8,8h80a8,8,0,0,0,8-8V64a47.93,47.93,0,0,0-2.75-16H200Z"></path></svg><span class="text-text-200 pr-0.5">Copy</span></button></div></div><div><div class="code-block__code !my-0 !rounded-lg !text-sm !leading-relaxed"><code><span><span>mvn install</span></span></code></div></div></div></pre>
3. Execute a aplicação:
   <pre><div class="relative flex flex-col rounded-lg"><div class="text-text-300 absolute pl-3 pt-2.5 text-xs"></div><div class="pointer-events-none sticky my-0.5 ml-0.5 flex items-center justify-end px-1.5 py-1 mix-blend-luminosity top-0"><div class="from-bg-300/90 to-bg-300/70 pointer-events-auto rounded-md bg-gradient-to-b p-0.5 backdrop-blur-md"><button class="flex flex-row items-center gap-1 rounded-md p-1 py-0.5 text-xs transition-opacity delay-100 hover:bg-bg-200 opacity-60 hover:opacity-100"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" viewBox="0 0 256 256" class="text-text-500 mr-px -translate-y-[0.5px]"><path d="M200,32H163.74a47.92,47.92,0,0,0-71.48,0H56A16,16,0,0,0,40,48V216a16,16,0,0,0,16,16H200a16,16,0,0,0,16-16V48A16,16,0,0,0,200,32Zm-72,0a32,32,0,0,1,32,32H96A32,32,0,0,1,128,32Zm72,184H56V48H82.75A47.93,47.93,0,0,0,80,64v8a8,8,0,0,0,8,8h80a8,8,0,0,0,8-8V64a47.93,47.93,0,0,0-2.75-16H200Z"></path></svg><span class="text-text-200 pr-0.5">Copy</span></button></div></div><div><div class="code-block__code !my-0 !rounded-lg !text-sm !leading-relaxed"><code><span><span>mvn spring-boot:run</span></span></code></div></div></div></pre>
4. A aplicação estará disponível em `http://localhost:8080`
