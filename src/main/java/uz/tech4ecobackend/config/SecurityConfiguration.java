package uz.tech4ecobackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import uz.tech4ecobackend.security.JWTConfigure;
import uz.tech4ecobackend.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //  default holatdagi ba'zi xavfsizlik blocklarini ochib chiqish

                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
//*************************************************
                .authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("api/parameter/register").permitAll()
                .antMatchers("/api/node/**").permitAll()

                .antMatchers("/api/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/account").hasAnyRole("USER", "ADMIN")  // Bazaga ROLE_ADMIN deb saqlash kerak
                .antMatchers("/api/getLogin", "/api/role/**").hasAnyRole("USER", "ADMIN")

                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .apply(securityConfigureAdapter());

    }

    private JWTConfigure securityConfigureAdapter() {
        return new JWTConfigure(jwtTokenProvider);
    }
}

