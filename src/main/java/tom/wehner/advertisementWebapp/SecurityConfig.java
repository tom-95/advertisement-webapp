package tom.wehner.advertisementWebapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
                        "/changeCity/**"
                ).permitAll()
                // static resources
                .antMatchers(
                        "/css/style.css"
                ).permitAll()
                .anyRequest().authenticated()

                // send the user back to the root page when they logout
                .and()
                .logout().logoutSuccessUrl("/")

                .and()
                .oauth2Client()
                .and()
                .oauth2Login();
    }

}


