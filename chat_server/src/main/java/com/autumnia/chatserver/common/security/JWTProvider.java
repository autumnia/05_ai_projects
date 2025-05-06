package com.autumnia.chatserver.common.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.autumnia.chatserver.common.contant.Constants;
import com.autumnia.chatserver.common.exception.*;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@Component
public class JWTProvider {
    private static String secret_key;
    private static String refresh_secret_key;
    private static Long token_time_for_minute;
    private static Long Refresh_token_time_for_minute;


    @Value("${token.key}")
    public void set_secret_key(String secret_key) {
        JWTProvider.secret_key = secret_key;
    }

    @Value("${token.refresh-secret-key}")
    public void set_refresh_secret_key(String refresh_secret_key) {
        JWTProvider.refresh_secret_key = refresh_secret_key;
    }

    @Value("${token.secret-key}")
    public void set_token_time_for_minute(Long token_time_for_minute) {
        JWTProvider.token_time_for_minute = token_time_for_minute;
    }

    @Value("${token.refresh-secret-key}")
    public void set_Refresh_time_for_minute(Long Refresh_keytoken_time_for_minute) {
        JWTProvider.Refresh_token_time_for_minute = Refresh_keytoken_time_for_minute;
    }


    public static String create_token(String name) {
        return JWT.create()
                .withSubject(name)
                .withIssuedAt(new Date())
                .withExpiresAt( new Date(System.currentTimeMillis() + token_time_for_minute * Constants.ON_MINUTE_TO_MILLIS ) )
                .sign(Algorithm.HMAC256(secret_key));
    }

    public static String create_refresh_token(String name) {
        return JWT.create()
                .withSubject(name)
                .withIssuedAt(new Date())
                .withExpiresAt( new Date(System.currentTimeMillis() + token_time_for_minute * Constants.ON_MINUTE_TO_MILLIS ) )
                .sign(Algorithm.HMAC256(refresh_secret_key));
    }

    public static DecodedJWT check_token_for_refresh(String token) {
        try {
            DecodedJWT decoded = JWT.require( Algorithm.HMAC256(secret_key) )
                    .build()
                    .verify( token );
            log.error("token must be expired : {}", decoded.getSubject());
            throw new CustomException(ErrorCode.ACCESS_TOKEN_IS_NOT_EXPIRED);
        }
        catch (AlgorithmMismatchException | SignatureVerificationException | InvalidClaimException e) {
            throw new CustomException(ErrorCode.TOKEN_IS_INVALID);
        }
        catch (TokenExpiredException e) {
            return JWT.decode(token);
        }
    }

    private static DecodedJWT decode_token_after_verify(String token, String key) {
        try {
            return JWT.require(Algorithm.HMAC256(key))
                    .build()
                    .verify(token);
        }
        catch (AlgorithmMismatchException | SignatureVerificationException | InvalidClaimException e) {
            throw new CustomException(ErrorCode.TOKEN_IS_INVALID);
        }
        catch (TokenExpiredException e) {
            throw new CustomException(ErrorCode.TOKEN_IS_EXPIRED);
        }
    }

    public static DecodedJWT decoded_JWT(String token) {
        return JWT.decode(token);
    }

    public static DecodedJWT decode_access_token(String token) {
        return decode_token_after_verify(token, secret_key);
    }

    public static String extractToken(String header) {
        log.debug("header: {}", header);

        if ( !StringUtils.hasText(header) || !header.startsWith("Bearer ") ) {
            throw new IllegalArgumentException("Invalid Auth Header");
        }

//        if ( StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
//        }
    }

    public static String getUserFromToken(String token) {
        DecodedJWT jwt = decoded_JWT(token);
        return jwt.getSubject();
    }

}
