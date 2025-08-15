Examen1Back2 – README.txt
================================

1) Descripción del proyecto
---------------------------
Examen1Back2 es una aplicación construida con Java 17 y Spring Boot que gestiona
Usuarios, Docentes y Cursos almacenados en MySQL. Se usan JPA/Hibernate para el
mapeo objeto–relacional y relaciones:
- Docente (1) —— (N) Curso
- Docente (1) —— (1) Usuario

El objetivo es exponer un backend limpio para registrar/consultar docentes,
sus cursos y el usuario asociado.


2) Errores encontrados y correcciones realizadas
------------------------------------------------

A) modelos/Curso
   - Se agrega la anotación @Table(name = "curso") para rastrear la tabla.
   - Se corrige la anotación @GeneratedValue (strategy = GenerationType.IDENTITY).
   - Se agregan puntos y comas faltantes en atributos/métodos.
   - Se agregan getters y setters.
   - Se agrega el modificador de acceso 'private' al atributo 'Docente docente'.
   - Relación con Docente:
       • Se eliminó el uso incorrecto del atributo 'mappedBy' en @ManyToOne
         (esa anotación no acepta 'mappedBy').
       • La relación correcta queda como: @ManyToOne + @JoinColumn("fk_docente").

B) modelos/Docente
   - Se agrega la anotación @Table(name = "docente").
   - Se corrige la escritura de la anotación @Entity.
   - Se agregan constructores (por defecto y con argumentos).
   - Se valida el lado inverso de la relación con Curso:
       • @OneToMany(mappedBy = "docente").
   - Se define relación 1–1 con Usuario mediante @OneToOne + @JoinColumn("fk_usuario").

C) modelos/Usuario
   - Se corrige la escritura de la anotación @Entity.
   - Se completa la sintaxis de @GeneratedValue con GenerationType.IDENTITY.
   - Se corrige la anotación de columna: @Column(name = "id_usuario") y otras
     columnas con restricciones (nullable, length, unique).
   - Nota de estilo: evitar caracteres especiales en nombres persistidos, por
     ejemplo mapear 'contraseña' con @Column(name = "contrasena").


3) Guía paso a paso para conectar Spring Boot a MySQL
-----------------------------------------------------
1. Dependencias (Gradle – build.gradle):
   dependencies {
       implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
       implementation 'org.springframework.boot:spring-boot-starter-web'
       implementation 'mysql:mysql-connector-java:8.0.33'
       developmentOnly 'org.springframework.boot:spring-boot-devtools'
       testImplementation 'org.springframework.boot:spring-boot-starter-test'
   }

2. Crear base de datos (en MySQL):
   CREATE DATABASE develop_db;

3. Configurar src/main/resources/application.properties:
   spring.application.name=Examen1Back2
   spring.datasource.url=jdbc:mysql://localhost:3306/develop_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   # JPA/Hibernate
   spring.jpa.hibernate.ddl-auto=update       # en desarrollo
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

4. Verificar entidades y relaciones:
   - Cada clase debe tener @Entity y un campo @Id.
   - Usar @Table si el nombre de la tabla difiere.
   - En Curso: @ManyToOne + @JoinColumn(name = "fk_docente").
   - En Docente: @OneToMany(mappedBy = "docente").
   - En Docente/Usuario: @OneToOne con @JoinColumn en el lado propietario y mappedBy en el inverso.

5. Ejecutar la aplicación:
   - Asegura que MySQL esté iniciado.
   - Ejecuta la app desde tu IDE o con:  ./gradlew bootRun
   - Hibernate creará o actualizará las tablas automáticamente con ddl-auto=update.


4) Recomendaciones para evitar errores futuros
----------------------------------------------
- JDK: el proyecto usa toolchain/Java 17. Verifica con 'java -version' y 'javac -version'.
- Variables de entorno: configura JAVA_HOME si Gradle no encuentra el JDK.
- Consistencia en nombres: alinear nombres de tabla/columna con @Table/@Column.
- Relaciones JPA:
    • @ManyToOne NO admite 'mappedBy'; úsalo en @OneToMany/@OneToOne inversos.
    • Define siempre la columna FK con @JoinColumn en el lado propietario.
- Configuración de BD:
    • Evita comillas en application.properties (p.ej., spring.datasource.username=root).
    • En desarrollo usa spring.jpa.hibernate.ddl-auto=update; en producción, validate.
- Serialización JSON:
    • Para relaciones bidireccionales usa @JsonManagedReference (lado dueño)
      y @JsonBackReference (lado inverso) para evitar recursión.
- Nombres de campos: evita caracteres especiales persistidos (ñ, tildes); usa @Column para mapear.
- Control de cambios: usa Git y realiza commits pequeños cuando cambies mapeos.
- Logs: habilita spring.jpa.show-sql=true para entender qué ejecuta Hibernate.

Fin del documento.
