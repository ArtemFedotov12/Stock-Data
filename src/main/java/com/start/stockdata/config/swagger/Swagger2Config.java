package com.start.stockdata.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.exception.wrapper.StockExceptionWrapper;
import com.start.stockdata.exception.wrapper.ValidationExceptionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.start.stockdata.util.constants.GlobalConstants.HEADER_STRING;

@Configuration
@EnableSwagger2
public class Swagger2Config implements WebMvcConfigurer {

	//.apis(RequestHandlerSelectors.basePackage("com.baeldung.web.controller"))
	//.paths(PathSelectors.ant("/foos/*"))

	@Autowired
	TypeResolver typeResolver;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// returns an instance of ApiSelectorBuilder,
				// which provides a way to control the endpoints exposed by Swagger.
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.start"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				.genericModelSubstitutes(ResponseEntity.class)
				.securityContexts(Lists.newArrayList(securityContext()))
				.securitySchemes(Lists.newArrayList(apiKey()))

				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
				.globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
				.globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
				.globalResponseMessage(RequestMethod.PATCH, getCustomizedResponseMessages())
				.globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages())
				.additionalModels(typeResolver.resolve(StockExceptionWrapper.class))
				.additionalModels(typeResolver.resolve(ValidationExceptionWrapper.class));

		//Map<String, List<String>>
		//ValidationExceptionWrapper
	}

	private List<ResponseMessage> getCustomizedResponseMessages(){
		List<ResponseMessage> responseMessages = new ArrayList<>();

		responseMessages.add(new ResponseMessageBuilder()
				.code(400)
				.message("Client error, server cannot process the request.")
				.responseModel(new ModelRef("StockExceptionWrapper"))
				.build());

		responseMessages.add(new ResponseMessageBuilder()
				.code(403)
				.message("There aren't enough permissions.")
				.build());

		responseMessages.add(new ResponseMessageBuilder()
				.code(422)
				.message("Request dto hasn't passed the validation process.")
				.responseModel(new ModelRef("ValidationExceptionWrapper"))
				.build());

		responseMessages.add(new ResponseMessageBuilder()
				.code(500).message("Internal Server Error.")
				.responseModel(new ModelRef("StockExceptionWrapper"))
				.build());
		return responseMessages;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/.*"))
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", HEADER_STRING, "header");
	}

/*	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(
				null,
				null,
				null, // realm Needed for authenticate button to work
				null, // appName Needed for authenticate button to work
				"Bearer ",// apiKeyValue
				ApiKeyVehicle.HEADER,
				"Authorization", //apiKeyName
				null);
	}*/



	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(
				new SecurityReference("JWT", authorizationScopes));
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Swagger2 Api Documentation",
				"How to generate Swagger documentation for your Rest API",
				"1.0", "urn:tos",
				new Contact("Bannes", "www.start.com", "artemfedotov045@gmail.com"),
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//enabling swagger-ui part for visual documentation
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
