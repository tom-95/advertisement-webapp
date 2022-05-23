package tom.wehner.advertisementWebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                // public pages
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/getArticles",
                        "/showArticle/**",
                        "/changeCity/**",
                        "/register"
                ).permitAll()
                .antMatchers(
                        HttpMethod.POST,
                        "/createAccount"
                ).permitAll()
                // static resources
                .antMatchers(
                        "/css/style.css",
                        "/js/dynamic.js"
                ).permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/myAccount", true)

                // send the user back to the root page when they logout
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))

                .and()
//                .oauth2Client()
//                .and()
//                .oauth2Login()
//                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}


