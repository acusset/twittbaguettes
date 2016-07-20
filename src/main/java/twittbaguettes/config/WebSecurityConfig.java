package twittbaguettes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Gestion des ACL
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .formLogin()
                    .loginPage("/login.html")
                    .loginProcessingUrl("/loginProcess")
                    .failureUrl("/login.html?action=error")
                    .permitAll()
                // Création de compte autorisrée par des anonymes
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/user").permitAll()
                .and().authorizeRequests().antMatchers("/app.js").permitAll()
                .and().authorizeRequests().antMatchers("/app/**").permitAll()
                .and().authorizeRequests().antMatchers("/**/*.*").permitAll()
//                .and().authorizeRequests().antMatchers("/css/**").permitAll()
//                .and().authorizeRequests().antMatchers("/font/**").permitAll()
//                .and().authorizeRequests().antMatchers("/fonts/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable();
    }
}