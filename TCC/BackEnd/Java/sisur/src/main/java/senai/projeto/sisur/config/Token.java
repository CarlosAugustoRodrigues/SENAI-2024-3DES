package senai.projeto.sisur.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import senai.projeto.sisur.entities.loginUsuarios.comum.LoginUsuarioComum;
import senai.projeto.sisur.entities.loginUsuarios.funcionario.LoginUsuarioFuncionario;

import java.time.Instant;

@Service
public class Token {

    @Value("${api.security.token}")
    private String secretKey;

    public String generateTokenComum(LoginUsuarioComum usuarioComum) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Instant now = Instant.now();

            return JWT.create()
                    .withIssuer("sisur")
                    .withSubject(usuarioComum.getUsername())
                    .withClaim("type", "usuariocomum")
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(300))
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao autenticar usuario!");
        }
    }

    public String generateTokenFuncionario(LoginUsuarioFuncionario usuarioFuncionario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            Instant now = Instant.now();

            return JWT.create()
                    .withIssuer("sisur")
                    .withSubject(usuarioFuncionario.getEmail())
                    .withClaim("type", "usuariofuncionario")
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(300))
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao autenticar funcionario!");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("sisur")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String getTokenType(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("sisur")
                    .build()
                    .verify(token)
                    .getClaim("type")
                    .asString();
        } catch(JWTVerificationException exception) {
            return null;
        }
    }
}
