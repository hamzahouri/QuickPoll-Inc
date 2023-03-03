package org.houri.QuickPoll;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration

public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .paths(PathSelectors.regex("/polls/*.*|/votes/*.*|/computeresult/*.*"))
                .build().apiInfo(apiInfo())
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Quick poll rest",
                "QuickPoll Api for creating and managing polls",
                "http://example.com/terms-of-service",
                "Terms of service",
                new Contact("hamza houri", "https://www.linkedin.com/in/hamza-houri/", "hamzahouri@gmail.com"),
                        "MIT License", "http://opensource.org/licenses/MIT",
                        Collections.emptyList());
    }
}
