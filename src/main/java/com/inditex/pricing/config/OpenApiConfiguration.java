package com.inditex.pricing.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.Optional;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

/**
 * Configuración de OpeanApi para el perfil 'dev'. En producción no está disponible esta
 * configuración.
 * 
 * @author Antonio Troyano
 *
 */
@Configuration
@Profile("dev")
public class OpenApiConfiguration {

  private static final String DESCRIPTION = "description";

  /**
   * Bean que es usado para alimentar la UI de swagger basada en OpenApi.
   * 
   * @param buildProperties Propiedades generadas en la compilación de la aplicación.
   * 
   * @return Un bean {link OpenAPI}
   */
  @Bean
  public OpenAPI springOpenApi(BuildProperties buildProperties) {

    return new OpenAPI().info(buildInfo(buildProperties));
  }

  /**
   * Bean que permite añadir respuesta u otros elementos de forma general al API Docs.
   * 
   * @return Un bean del tipo {@link OpenApiCustomiser}
   */
  @Bean
  public OpenApiCustomiser customOpenApiCustomiser() {

    return openApi -> openApi.getPaths().values()
        .forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

          operation.getResponses().addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
              new ApiResponse().description(HttpStatus.BAD_REQUEST.getReasonPhrase()));

          operation.getResponses().addApiResponse(
              String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
              new ApiResponse().description(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()));

          operation.getResponses().addApiResponse(
              String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
              new ApiResponse().description(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()));

          operation.getResponses().addApiResponse(
              String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
              new ApiResponse().description(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        }));
  }

  /**
   * Método que construye el objeto Info de la UI de swagger.
   * 
   * @param buildProperties Propiedades generadas en la compilación de la aplicación
   * @return
   */
  private Info buildInfo(BuildProperties buildProperties) {

    var info = new Info();

    Optional.ofNullable(buildProperties.getName()).ifPresentOrElse(
        name -> info.setTitle(buildProperties.getName()),
        () -> info.setTitle(buildProperties.getArtifact()));

    Optional.ofNullable(buildProperties.get(DESCRIPTION)).ifPresentOrElse(
        description -> info.setDescription(buildProperties.get(DESCRIPTION)),
        () -> info.setDescription(""));

    info.setVersion(buildProperties.getVersion());

    return info;
  }

}
