# SERVICIO DE PRECIOS (v1.0.0)

## Introducción

Se trata de un servicio REST realizado en Spring Boot que expone un endpoint para recuperar un precio final (PVP) a partir de una serie de parámetros de entrada. Es un servicio básico al que se le han añadido algunas funcionalidades adicionales como es el caso de seguridad a través del protocolo TLS (https) y seguridad básica de un microservicio, de la documentación de la API a través de OpenApi v3, una clase gestora de las respuestas de error, trazas (logs) para las peticiones y respuestas, Spring Actuator para saber conocer la salud e información del servicio, JavaMoney para controlar la moneda y su cantidad, documentación de clases (Javadoc), Sleuth para analizar las trazas sobre peticiones, un fichero de entorno de producción, un fichero de muestra Jenkisfile para un hipotético despliegue y otro fichero de propiedades de Sonar para evaluar vulnerabilidades, duplicidades, code smell, hotspots, ... Por último JaCoCo para la cobertura de clases en caso de tests. En este aspecto cabe destacar que únicamente se incluyen los tests pedidos en la prueba técnica.

## Consideraciones técnicas

Para este microservicio se han usado las siguientes librerías y plugins con sus respectivas versiones. Así como se han usado algunos estándares para tratar con fechas o monedas.

* JDK 11
* Spring Boot 2.5.2
* Maven 3.6.x
* Lombok 1.18.20
* Generador OpenApi v3 (Swagger)
* JaCoCo 0.8.5
* JavaDoc
* Codificación: UTF-8
* Fechas formateadas a: ISO-8601
* JavaMoney: JSR-354
* Eclipse Java Google Style
* Plugin Color Ansi Escap Eclipse

Se ha desarrollado el proyecto usando el fomateador de texto Eclipse Java Google Style.

A parte se incluye un fichero Jenkisfile, para simular un despligue y otro sonar.properties para la evaluación de calidad de código. Estos ficheros se incluyen a modo de prueba, ya que no he tenido la oprtunidad de realizar las comprobaciones de los mismos.

## Consideraciones funcionales adicionales

Se ha optado por realizar un microservicio REST basado en Spring Boot y no en Spring Webflux, ni usando colas, ni haciendo uso de GraphQL, por el hecho de simplificar el ejemplo. De igual modo he seguido un patrón estándar de microservicio, desechando patrones como CQRS o Event Sourcing.

Se han creado 2 entornos con sus respectivos ficheros de propiedades de spring: dev(local o desarrollo) y prd (producción).
El entorno de ejecución o perfil donde he realizado el desarrollo ha sido con el perfil dev. La API se ha documentado mediante OpenApi y se encuentra disponible en la url https://localhost:8080/pricing-service/swagger-ui.html, una vez ejecutado. Se considera que en un entorno de desarrollo la API, debe estar disponible, mientras que para un entorno de producción no, y para ello se ha creado el archivo de propiedades correspondiente para poder desactivar esta característica. Este ha sido el motivo de incorporar otro entorno simulando un entorno de producción.

Se ha incorporado un plugin de generación de ficheros openapi, para disponer de una especificación y poder usarla directamente en un servicio como API Gateway de AWS, o API Management de Azure.

Se ha incluido seguridad a nivel básica usando Spring Security para este ejemplo, al igual que el uso de https en las comunicaciones. En el caso de HTTPS, se ha autogenerado un almacen de claves presente en la la ruta src/main/resources/certs/try_cert.jks. Se trata un almacén con la información básica y válido para el DNS localhost y también para la IP 127.0.0.1. No está hecho para un entorno de producción.

Por otro lado se ha decido realizar el traceo de las peticiones mediante la librería de zalando que proporciona bastante información de solicitudes y respuestas y las trazas para el entorno de dev, se sacan por consola y se almacenan en un fichero con política de rotación. Mientras que en el entorno prd, solo se usa el fichero. También se ha añadido Spring Clous Sleuth, para que ingrese en las trazas, el servicio, el id de la traza padre e hija. He decidido incorporarlo, para demostrar que las trazas podrían ser analizadas desde su origen usando algún servicio para ello, como Zipkin, por ejemplo.

También se ha decido documentar las clases y métodos, para continuar con el guión de buenas prácticas y para la compilación del proyecto, se ha decido usar JaCoCo, excluyendose determinados paquetes, para tener una covertura.

Y por último he decidido tomar los datos de price y curr como una moneda, para que puedan ser tratados correctamente para operaciones con monedas. No es el caso de uso de este ejemplo, pero he querido hacer notar tal hecho, de la posibilidad de trabajar con monedas.

## Funcionalidad

Se trata de una aplicación basada en el patrón de capas hecha con microservicioS del tipo REST. Expone un endpoint usando el método GET mediante un controller. Éste recibe una serie de parámetros en formato json que sirven como filtro para recuperar un detereminado precio de una tabla almacenada en una base de datos en memoria H2. Los parámetros por los que se filtrarán son:

- fecha de aplicación de la tarifa: applicationDate
- identificador del producto:       productId
- identificador de la cadena:       brandId

Una vez recibida la petición por una primera capa de validación de datos, se realiza la validación. Si son validadados exitosamente, se entregan a la capa del controlador. En caso contrario, se retorna una respuesta informando del error. El controlador hace uso de la capa de servicios, quien lleva la lógica de negocio, para que ésta le entregue el resultado y poder generar la respuesta correcta. La capa de servicios o de lógica de negocio, hace uso de la capa de acceso a repositorio de datos, para acceder a base de datos y recuperar valores mediante una sentencia SQL filtrada. En la capa de negocio se gestiona que si la capa de repositorio retornara más de un elemento, se quedaría con el primer valor para devolver al controlador un único valor. También le indica a la capa de repositorio, como tiene que formarse la query de consulta y como debe devolver el resultado, que se trata de una sentencia SELECT con filtro y con un orden. En la capa de repositorio hemos decidido hacer uso de Method Query Language de Spring para generar la SQL. La capa de acceso a repositorios o de persistencia está formada por 2 paquetes, los dominios o entidades y la de repositorio. En el paquete de entidades están las clases que representan las tablas de base de datos y que tienen validadores adicionales. Esta última capa hace uso de un ORM, Hibernate en este caso, para llevar toda la operación de acceso a base de datos. Una vez recuperados, se hace el ciclo en el sentido contrario; de la capa de repositotio al la capa de servicio y ésta la entrega al controlador, quien la parsea convirtiendo la respuesta en formato Json. En el caso de no encontrar resultados, se lanza una excepción que es tratada por el manejador de errores genérico, retornando una respuesta 404.

## Compilación y ejecución

Para compilar el proyecto es necesario indicarle dónde se encuentran los ficheros de certificación, pues una de las fases levanta la aplicación para generar el fichero de documenrtación de la API.

'''
mvn clean install -Djavax.net.ssl.trustStore=src/main/resources/certs/try_cert.jks -Djavax.net.ssl.trustStorePassword=1234
'''

Y para correr el proyecto:

'''
mvn spring-boot:run
'''

## Observaciones
Hay mucho funcinoalidad adicional, pero que creo conveniente introducirla o añadirla al proyecto para exponer su conocimiento. Ya que se trata de un ejercicio básico no sería necesaria, pero tampoco habría otra posibilidad de demostrar la forma que tengo de trabajar.

Por simplicidad, he metido clases de configuración que pudieran estar en un proyecto aparte simulando un core o una serie de componentes adicionales que añadir al proyecto usando dependencias.

Por motivos laborales, no he podido realizar las suficientes pruebas, ya que posiblemente haya cometido errores y todo sea aún mejorable. Pero he pretendido por un lado realizar la prueba del microservicio, llevando a cabo temas de Clean Code y principios SOLID y por otro lado dar valor añadido que no veo por que no pudiera usado, aunque aumente complejidad y posibilidad de fallo. Y como se trata de una prueba y no una aplicación real dónde se evaluarían otros factores, como latencias, escalado, necesidades, etc. he considerado oprtuno añadirlos.

