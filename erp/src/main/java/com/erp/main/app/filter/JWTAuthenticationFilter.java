package com.erp.main.app.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.erp.main.app.config.WebSecurityConfig;
import com.erp.main.app.controller.system.request.AuthUserRequest;
import com.erp.main.domain.objects.valueObjects.AuthUserVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWTの認証処理を定義するクラス
 * @author takah
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
		
	/**
	 * 有効期限
	 */
	@Value("${app.security.exptration_time}")
	private long exptrationTime;
	
	/**
	 * アプリケーションの鍵
	 */
	@Value("$app.security.app_key")
	private String appKey;
		
	private AuthenticationManager authenticationManager;
	
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, PasswordEncoder passwordEncorder) {
        this.authenticationManager = authenticationManager;

        // ログイン用のpathを変更する
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/users/login", "POST"));

        // ログイン用のID/PWのパラメータ名を変更する
        setUsernameParameter(WebSecurityConfig.USER_ID_KEY);
        setPasswordParameter(WebSecurityConfig.USER_PASSWORD_KEY);

    }
    
    /**
     * 認証処理
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            // requestパラメータからユーザ情報を読み取る
        	AuthUserRequest authUser = new ObjectMapper().readValue(req.getInputStream(), AuthUserRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    		authUser.getUserId(),
                    		authUser.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
        	// TODO: 認証系のエラーを作って返却する
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 認証成功時にヘッダに認証用のトークンを詰める
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // loginIdからtokenを設定してヘッダにセットする
        String token = Jwts.builder()
                .setSubject(((AuthUserVo)auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + this.exptrationTime))
                .signWith(SignatureAlgorithm.HS512, "APP_KEY")
                .compact();
        res.addHeader(WebSecurityConfig.AUTH_KEY_NAME,  WebSecurityConfig.TOKEN_PREFIX + token);

    }
}
