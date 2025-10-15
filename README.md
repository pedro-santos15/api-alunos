# 🎓 Alunos API

API REST desenvolvida em **Java com Spring Boot**, com o objetivo de gerenciar informações de alunos.  
O projeto foi criado como prática dos meus estudos com o ecossistema Spring, aplicando conceitos de **arquitetura em camadas**, **validação**, **tratamento de exceções** e **persistência de dados** com **Spring Data JPA**.

---

## 🚀 Tecnologias utilizadas
- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database (banco em memória)**
- **SLF4J** (logging)
- **Maven**
- **Postman** (para testes de endpoints)

---

## 🧱 Estrutura do projeto

src/
 └── main/
      ├── java/com/pedrosantos15/alunosapi/
      │     ├── controller/     → Controladores REST (AlunoController)
      │     ├── service/        → Regras de negócio (AlunoService)
      │     ├── repository/     → Acesso ao banco (AlunoRepository)
      │     ├── model/          → Entidades JPA (Aluno)
      │     ├── validator/      → Validações personalizadas
      │     └── exceptions/     → Exceções customizadas
      └── resources/
            └── application.yaml

---

## 📚 Funcionalidades
✅ Criar um novo aluno  
✅ Listar todos os alunos  
✅ Buscar aluno por ID  
✅ Atualizar informações de um aluno  
✅ Excluir um aluno  
✅ Validações de nome e idade  
✅ Logs informativos a cada operação  

---

## 🧠 Conceitos aplicados
- Organização em camadas (**Controller → Service → Repository → Model → Validator → Exceptions**)
- **Injeção de dependência**
- **Validação e tratamento de exceções customizadas**
- **Logs com SLF4J**
- **Banco em memória H2** para testes rápidos e sem dependências externas
- **Testes via Postman**
