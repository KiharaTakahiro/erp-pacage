package com.erp.main.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swaggerを生成するためのConfig
 * @author takah
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket getPublicDocument() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("project")
				.select()
				.build()
				.apiInfo(this.apiInfo());
	}
	
	/**
	 * APIの情報
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		// TODO: Deprecatedされていないものに変更する
		return new ApiInfo(
                "ERP-API",
                "ERPのAPIを提供します。",
                "v1",
                "",
                "",
                "商用利用不可",
                "");
	}
}
