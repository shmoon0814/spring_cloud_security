package m.s.h.cloudserver.security;

import com.nimbusds.jose.proc.SingleKeyJWSKeySelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String jwt_secret;

    @Autowired
    AuthErrorEntryPoint authErrorEntryPoint;

    @Bean
    public JwtDecoder jwtDecoder() {
        CustomJwtDecoder customJwtDecoder = new CustomJwtDecoder(jwt_secret);
        return customJwtDecoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .exceptionHandling().authenticationEntryPoint(authErrorEntryPoint).and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt().decoder(jwtDecoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
                .ignoring()
                .antMatchers(
                        HttpMethod.POST,
                        "/auth/v1/login",
                        "/auth/v1/join"
                )
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/auth/test"
                );


    }


}
