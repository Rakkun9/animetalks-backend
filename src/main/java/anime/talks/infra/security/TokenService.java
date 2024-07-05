package anime.talks.infra.security;

import anime.talks.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    public String generateToken(User user) {
       try {
           Algorithm algorithm = Algorithm.HMAC256("123456");
           return JWT.create()
                   .withIssuer("Animetalks")
                   .withSubject("miyarakvn")
                   .withClaim("id", user.getId())
                   .withExpiresAt(generateExpirationDate())
                   .sign(algorithm);
       }catch (JWTCreationException exception){
           throw new RuntimeException("Error creating token");
       }
    }
    //Code from the documentation
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Invalid token");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Animetalks")

                    // reusable verifier instance
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier == null || verifier.getSubject() == null){
            throw new RuntimeException("Invalid token");

        }
        return verifier.getSubject();
    }


    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));

    }
}
