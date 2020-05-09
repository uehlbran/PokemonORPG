package orpg.com.pokemonorpg.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import orpg.com.pokemonorpg.handlers.AuthenticationSuccessRedirectHandler;
import orpg.com.pokemonorpg.services.UserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and()
                    .authorizeRequests().antMatchers("/", "/js/**", "/css/**", "/images/**").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/h2", "/h2/**", "/h2-console/**").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/user/**").authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .successHandler(authenticationSuccessHandler())
                .and()
                    .csrf().disable();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessRedirectHandler();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
