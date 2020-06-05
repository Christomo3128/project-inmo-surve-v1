package com.inmo.channel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableSwagger2WebFlux
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerWebFluxConfiguration extends WebFluxConfigurationSupport {

  @Bean
  public Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
    return new Jackson2JsonEncoder(mapper);
  }

  @Bean
  public Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
    return new Jackson2JsonDecoder(mapper);
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:META-INF/resources/");
  }

  @Bean
  public Docket docketApi(ApplicationProperties applicationProperties, Tag[] customTags) {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(this.metadata(applicationProperties))
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .pathMapping("/").protocols(ImmutableSet.of("http", "https"))
        .useDefaultResponseMessages(false)
        .enableUrlTemplating(false)
        .forCodeGeneration(true)
        .ignoredParameterTypes(Throwable.class)
        .directModelSubstitute(Completable.class, Void.class)
        .genericModelSubstitutes(Optional.class, ResponseEntity.class, Single.class, Maybe.class,
            Observable.class, Flowable.class, Mono.class, Flux.class);
    Arrays.stream(customTags).forEach(tag -> docket.tags(tag, tag));
    return docket;
  }

  private ApiInfo metadata(ApplicationProperties applicationProperties) {
    String title = applicationProperties.getSwaggerTitle();
    String description = applicationProperties.getSwaggerDescription();
    String version = applicationProperties.getSwaggerVersion();
    return (new ApiInfoBuilder()).title(title).description(description).version(version).build();
  }

}