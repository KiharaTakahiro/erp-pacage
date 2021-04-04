package com.erp.main.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.erp.main.app.filter.JWTAuthenticationFilter;
import com.erp.main.app.filter.JWTAuthorizationFilter;
import com.erp.main.domain.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer  {

	// FIXME: 一時的にフィルタを開ける
	private static final String[] AUTH_WHITELIST = {
			"/user/register", 
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/",
            "/users/login",
            "/**"
    };
	
	/**
	 * ユーザを認証するためのIDのKey名
	 */
	public static final String USER_ID_KEY = "userId";
	
	/**
	 * パスワードに使用するためのKey名
	 */
	public static final String USER_PASSWORD_KEY = "password";
	
	/**
	 * responseに詰めるAuthののkey名
	 */
	public static final String AUTH_KEY_NAME = "X-Access-Token";
	
	public static final String TOKEN_PREFIX = "Bearer ";
	
	@Autowired
	private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        		.authorizeRequests()
        		.antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest()
                .authenticated()
                .and().logout()
                .and().csrf().disable()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), passwordEncoder()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userService)
                .passwordEncoder(this.passwordEncoder());
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:9527");
    }
    
}
