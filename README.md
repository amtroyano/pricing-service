# Getting Started

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.0-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.2/reference/htmlsingle/#boot-features-developing-web-applications)

## Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Springdoc openapi](https://springdoc.org/#spring-data-rest-support)
* [Lombok](https://projectlombok.org/)


## Plugins

List of usage plugins:

* [Java Code coverage](https://www.jacoco.org/jacoco/index.html)
* [Javadoc plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/)


## Price Application


Price application is based in spring boot and java 11. The application provides a Rest endpoint to consult a price in a specific date/hour ordering by priority
if there were more than one.


### How to run

Compile:

    $ mvn clean install

Run :

    $ mvn spring-boot:run
    
        
### API Price Contract
	
	$curl --location --request GET 'http://host:port/price?applicationDate=2020-06-14%2010:30:00&productId=35455&brandId=1'

## Utils

The application has enabled the H2 console in runtime.
	
    