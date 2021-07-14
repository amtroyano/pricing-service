# SERVICIO DE PRECIOS (v1.0.0)

## Introducción

Se trata de un servicio REST realizado en Spring Boot que expone un endpoint para recuperar un precio final (PVP) a partir de una serie de parámetros de entrada. Es un servicio básico al que se le han añadido algunas funcionalidades adicionales como es el caso de la documentación de la API a través de OpenApi v3, una clase gestora de las respuestas de error, trazas (logs) para las peticiones y respuestas, Spring Actuator para saber conocer la salud e información del servicio, Java Money para controlar la moneda y su cantidad, documentación de clases (Javadoc) y por último JaCoCo para la cobertura de clases en caso de tests. En este aspecto cabe destacar que únicamente se incluyen los tests pedidos en la prueba técnica.

## Consideraciones técnicas

Para este microservicio se han usado las siguientes librerías con sus respectivas versiones. Así como se han usado algunos estándares para tratar con fechas o monedas.

* JDK 11
* Spring Boot 2.5.2
* Maven 3.6.x
* Lombok 1.18.20
* OpenApi v3 (Swagger)
* Codificación: UTF-8
* Fechas formateadas a: ISO-8601
* JavaMoney: JSR-354

## Consideraciones funcionales adicionales

Se ha optado por realizar un microservicio REST basado en Spring Boot y no en Spring Webflux, ni haciendo uso de GraphQL, por el hecho de simplificar el ejemplo.

El entorno de ejecución o perfil, es local. Para este entorno está disponible la documentación API. La API se ha documentado mediante OpenApi y se encuentra disponible en la url <link>http://localhost:8080/pricing_service/swagger-ui.html</link>. Se considera que en un entorno de desarrollo la API, debe estar disponible, mientras que para un entorno de producción no. Para conseguir esto, hay que establecer la porpiedad del perfil de spring a otro distinto de local y desactivar la parte ui de openapai.

'''
spring.profiles.active=otro
...
springdoc.swagger-ui.enabled: false
'''

Se ha obviado el tema de la seguridad con Spring Security para este ejemplo, al igual que el uso de https en las comunicaciones, para simplificar el ejemplo.

Por otro lado se ha decido realizar el traceo de las peticiones mediante la librería de zalando, que properciona bastante información de solicitudes y respuestas.

También se ha decido documentar las clases y métodos, para continuar con el guión de buenas prácticas y para la compilación del proyecto, se ha decido usar JaCoCo, excluyendose determinados paquetes, para tener una covertura.

## Funcionalidad

Se trata de una aplicación basada en el patrón de capas hecha con microservicioS del tipo REST. Expone un endpoint usando el método GET mediante un controller. Éste recibe una serie de parámetros en formato json que sirven como filtro para recuperar un detereminado precio de una tabla almacenada en una base de datos en memoria H2. Los parámetros por los que se filtrarán son:

- fecha de aplicación de la tarifa: applicationDate
- identificador del producto:       productId
- identificador de la cadena:       brandId

Una vez recibida la petición para por una primera capa de validación de datos. Si son validadados exitosamente, se entregan a la capa del controlador. En caso contrario, se retorna una respuesta informando del error. El controlador hace uso de la capa de servicios, quien lleva la lógica de negocio, para que ésta le entregue el resultado y poder generar la respuesta correcta. La capa de servicios o de lógica de negocio, hace uso de la capa de acceso a repositorio de datos, para acceder a base de datos y recuperar valores mediante una sentencia SQL filtrada. En la capa de negocio se gestiona que si la capa de repositorio retornara más de un elemento, se quedaría con el primer valor para siempre devolver al controlador un único valor. También le indica a la capa de repositorio, como tiene que formarse la query de consulta y como debe devolver el resultado, que se trata de una sentencia SELECT con filtro y con un orden. En la capa de repositorio hemos decidido hacer uso de Method Query Language de Spring para generar la SQL. La capa de acceso a repositorios o de persistencia está formada por 2 paquetes, los dominios o entidades y la de repositorio. En el paquete de entidades están las clases que representan las tablas de base de datos y que tienen validadores adicionales. Esta última capa hace uso de un ORM, Hibernate en este caso, para llevar toda la operación de acceso a base de datos. Una vez recuperados, se hace el ciclo en el sentido contrario; de la capa de repositotio al la capa de servicio y ésta la entrega al controlador, quien la parsea convirtiendo la respuesta en formato Json.

En todo este proceso y según los datos almacenados en base de datos, pudiera suceder que no retornara valores, por lo que se retornaría una respuesta 404.
