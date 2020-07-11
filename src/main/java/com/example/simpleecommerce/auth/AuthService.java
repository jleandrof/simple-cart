package com.example.simpleecommerce.auth;

import com.example.simpleecommerce.users.models.UserRoles;
import com.example.simpleecommerce.utils.Consts;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@NoArgsConstructor
public class AuthService {

    public String generateAuthToken(Long userId, UserRoles role) throws JOSEException {
        Date expireDate = Date.from(Instant.now().plus(Consts.AUTH_TOKEN_EXPIRATION, ChronoUnit.DAYS));

        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .expirationTime(expireDate)
                .claim("user_id", userId).build();

        SignedJWT newToken = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), jwtClaims);

        if(role == UserRoles.ADMIN)
            newToken.sign(new MACSigner(getAdminSecret().getBytes()));
        else
            newToken.sign(new MACSigner(getUserSecret().getBytes()));

        return newToken.serialize();
    }

    public boolean verifyAdminToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        return signedJWT.verify(new MACVerifier(getAdminSecret().getBytes()));
    }

    public boolean verifyUserToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        return signedJWT.verify(new MACVerifier(getUserSecret().getBytes())) || verifyAdminToken(token);
    }

    public JWTClaimsSet getTokenClaims(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        return signedJWT.getJWTClaimsSet();
    }

    private String getAdminSecret() {
        return Consts.getEnv("ADMIN_SECRET").orElse("jcfJB9LM87bllml5EeWyjAvThDz2O6CZ");
    }

    private String getUserSecret() {
        return Consts.getEnv("USER_SECRET").orElse("8gaWDgkCa85TvYMeuIwYfropxlpxRSnG");
    }
}
