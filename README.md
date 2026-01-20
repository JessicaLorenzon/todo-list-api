# Todo List API

Projeto feito seguindo o roadmap de projetos do [roadmap.sh](https://roadmap.sh/projects/todo-list-api), com o objetivo de praticar desenvolvimento de APIs RESTful completas com autenticação, gerenciamento de dados e boas práticas.

## Descrição

O **Todo List API** é uma aplicação backend que permite aos usuários gerenciar suas tarefas de forma segura.  
A API oferece:

- Registro e login de usuários
- Autenticação com JWT (token)
- CRUD completo de tarefas (To-Do)
- Paginação

A cada requisição, o usuário autenticado acessa apenas suas próprias tarefas.

## Tecnologias utilizadas

- **Java** – Linguagem principal
- **Spring Boot** – Framework para construção da API
- **Maven** – Gerenciador de dependências
- **Spring Security** – Segurança e controle de acesso
- **JPA / Hibernate** – Mapeamento objeto-relacional
- **Flyway** – Versionamento e migração do banco de dados
- **JWT (JSON Web Tokens)** – Geração de token
- **PostgreSQL** – Banco de dados

## Como rodar o projeto

### 1. Baixe ou clone o repositório

```bash
git clone https://github.com/JessicaLorenzon/todo-list-api.git
```

### 2. Configure o banco de dados

Antes de executar a aplicação, configure o banco de dados com as informaçoes que estão no arquivo ```application.properties```

O Flyway será responsável por executar automaticamente as migrações do banco de dados ao iniciar a aplicação.

### 3. Execute a aplicação

```bash
java -jar target/todo-list-api-0.0.1.jar
```

### 4. Acesse a aplicação

A API estará disponível em: http://localhost:8080

## Endpoints disponíveis

### Autenticação

### 1. Registro de usuário

```http
POST /register
```

#### Payload (JSON):

```json
{
  "name": "John Doe",
  "email": "john@doe.com",
  "password": "password"
}
```

### 2. Login

```http
POST /login
```

#### Payload (JSON):

```json
{
  "email": "john@doe.com",
  "password": "password"
}
```

### Tarefas (necessita token no header Authorization)

### 1. Buscar todas as tarefas (com paginação)

```http
GET /todos?page=0&limit=10
```

### 2. Buscar tarefa única

```http
GET /todos/{id}
```

### 3. Criar nova tarefa

```http
POST /todos
```

#### Payload (JSON):

```json
{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, and bread"
}
```

### 4. Atualizar tarefa

```http
PUT /todos/{id}
```

#### Payload (JSON):

```json
{
  "title": "Buy groceries",
  "description": "Buy milk, eggs, bread, and cheese"
}
```

### 5. Deletar tarefa

```http
DELETE /todos/{id}
```