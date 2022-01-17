package org.external;

import org.models.core.dao.CustomRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
@EnableMongoRepositories(basePackages = {"org.models"})
@ComponentScan(basePackages = {"org.external","org.models"})
@ConfigurationPropertiesScan("org.models.core.properies")
@Import(BeanValidatorPluginsConfiguration.class)
public class ExternalApplication {

	@Autowired
	CustomRepositories customRepositories;

	public static void main(String[] args) {

		SpringApplication.run(ExternalApplication.class, args);

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*")
						.allowedMethods(HttpMethod.DELETE.name(),HttpMethod.OPTIONS.name(),HttpMethod.POST.name(),HttpMethod.GET.name(),HttpMethod.PUT.name());
			}
		};
	}

}
