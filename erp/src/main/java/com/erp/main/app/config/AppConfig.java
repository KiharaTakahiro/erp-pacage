package com.erp.main.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 処理全体に使用するConfigを設定するクラス
 * @author takah
 *
 */
@Configuration
@EntityScan(basePackages ={"com.erp.main.domain.objects.entity", "com.erp.main.domain.objects.entity.fields"})
public class AppConfig implements WebMvcConfigurer{

}
