
# 📚 Prueba Alquimia  

Proyecto API REST desarrollada en Java 17 y Spring Boot 3.4.4, usando Maven como gestor de dependencias.
Permite registrar Usuarios, Clientes y Direcciones asociadas a los Clientes.

🔗 Repositorio: https://github.com/RonnyChamba/prueba-alquimia

📌 Requisitos  
  - Java 17 ☕
  
  - Maven 3.9+ 📦
  
  - PostgreSQL 14+ 🐘
  
  - (Opcional) Docker 🐳

## 🛠️ Instalación  
1. Configurar Base de Datos 🗄️
Crea la base de datos ejecutando el script:  
➡️ base_test_alquimia.sql que se encuentra en la raíz del proyecto.


2. Configurar el archivo de propiedades ⚙️
- En la ruta src/main/resources/, copia el archivo:
cp application.properties.dist application.properties

- Actualiza las propiedades de conexión en el nuevo application.properties:

spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

3. Levantar el proyecto localmente 🚀
- Compila y ejecuta el proyecto desde tu IDE favorito (IntelliJ, Eclipse, etc.) o usando Maven:

mvn spring-boot:run


4. Contratos Postman 🚀
- Ver contratos de los request:

https://documenter.getpostman.com/view/17373384/2sB2j1gXBc

## 🐳 Levantar con Docker (opcional)

- Primero, genera el .jar:
mvn clean install

- Luego, construye la imagen de Docker desde la raiz del proyecto:
docker build -t prueba-alquimia .

- Finalmente, ejecuta el contenedor:
docker run -p 8080:8080 prueba-alquimia
