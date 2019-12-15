package m.s.h.cloudserver.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.util.Map;

public class CustomJwtDecoder implements JwtDecoder {

    private String jwtSecret;

    private CustomJwtDecoder(){}

    public CustomJwtDecoder(String jwtSecret){
        this.jwtSecret = jwtSecret;
    }

    @Override
    public Jwt decode(String token) throws JwtException{
        io.jsonwebtoken.Jwt jwt1 =   Jwts.parser().setSigningKey(jwtSecret).parse(token);
        return new Jwt(token, null, null, jwt1.getHeader(), (Map<String, Object>) jwt1.getBody());
    }


}
