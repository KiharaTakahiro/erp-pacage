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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.erp.main.app.filter.JWTAuthenticationFilter;
import com.erp.main.app.filter.JWTAuthorizationFilter;
import com.erp.main.domain.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// FIXME: 初期データの投入ができないため[/user/register]一時的に開けるが後に外す
	private static final String[] AUTH_WHITELIST = {
			"/user/register", 
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/",
            "/user/login"
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
	public static final String AUTH_KEY_NAME = "X-Token";
	
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
        		.cors()
        		.configurationSource(this.corsConfigurationSource())
        		.and()
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
    
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addExposedHeader("Authorization");
        corsConfiguration.addAllowedOrigin("http://localhost:9528/"); 
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }
    
}
