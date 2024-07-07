Baixe o projeto.
Execute-o em uma IDE de sua escolha.
Abra seu navegador e navegue para a seguinte URL:
http://localhost:8080/swagger-ui/index.html
A API está totalmente documentada via Swagger.

Segurança da API
A API é protegida pelo Spring Security (token JWT). Aqui estão os passos para obter acesso:

Registro de Usuário: Registre um usuário no banco de dados (você pode fazer isso via Postman ou Swagger).
OBS: Se quiser que o usuario tenha permissao de ADMIN, adicione diretamente no banco de dados .

Login: Com o usuário registrado, faça o login.
Obtenção do Token: Recupere o token retornado após o login.
Autorizar Swagger: Use o token para autorizar o Swagger. Uma vez autorizado, todos os métodos estarão acessíveis.
Sobre a API:

Esta API REST foi projetada para gerenciar livros em uma biblioteca. Ela suporta as seguintes funcionalidades:

- Registrar Usuários do Sistema: Adicionar novos usuários ao sistema.
  
- Cadastrar Livros: Adicionar novos livros na biblioteca , apenas com a permissão ADMIN  .

- Gerenciar livros : Listar livros(pode-se listar apenas os disponíveis pra reserva) .

- Deleção: Realizar deleção direta ou deleção lógica baseada em regras de negócios.

- Fazer reserva : Fazer uma reserva se o livro estiver disponível, usa-se o livro e usuario .
  
- Gerenciar reserva : Listar reserva por id , ou por usuário que foi registrado.
  
- Devolver o livro : Devoloção do livro, e automaticamente já fica pronto pra ser reservado novamente.

- Controle de Segurança: As requisições são permitidas apenas após o login e inclusão do token para autenticação. Com exeções do Login e Cadastro (essas estão sempre permitidas )

- Criptografia de Senha: As senhas são criptografadas usando Bcrypt para garantir a segurança da instituição e suas funções.


Tecnologias Utilizadas:

- Java
- Spring Boot
- Token JWT
- MySQL
- Swagger
- Spring Security
- Docker Conteinização
- Bcrypt
