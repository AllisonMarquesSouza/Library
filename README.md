Execute it in an IDE of your choice.
Open your browser and type the URL:
http://localhost:8080/swagger-ui/index.html
The API is fully documented with Swagger.

Security API
The API is protected by Spring Security (JWT token). Here are the steps to gain access:

Register User: Register a user in the database at the endpoint /register in Swagger.
Grant ADMIN Permission: If you want the user to have ADMIN permission, add it directly in the database.
Login: For an already registered user, you can log in.
Receive Token: After logging in, you will receive a token.
Authorize: Use the token to authorize in Swagger. Once authorized, all methods are available for use.
About the API:

This REST API was created to manage books in a library and provides the following features:

- Register users in the system.
- Create books (only with ADMIN role).
- Manage books, including:
- Listing books available for reservation.
- Deleting books.
- Making reservations.
- Managing reservations.
- Returning books (the book will automatically become available for reservation).
- Controller Security: Requests are permitted only with a valid token for authentication (Exceptions: Register and login are always allowed).
- Password Encryption: Passwords are encrypted using Bcrypt.

Technologies used:

- Java
- Spring Boot
- JWT Token
- MySQL
- Flyway (manages database tables)
- Swagger
- Spring Security
- Docker
Bcrypt

Endpoints: 

![Captura de tela 2024-08-10 153733](https://github.com/user-attachments/assets/b30cfef3-a71d-463b-a201-b61b79bae93e)



