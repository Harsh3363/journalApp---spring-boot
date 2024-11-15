package net.engineeringdigest.journalApp.config;

import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/journals/**","/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


















/*
http.authorize Requests(): This tells Spring Security to start authorizing the requests. .antMatchers("/hello").permitAll():
This part specifies that HTTP requests matching the path /hello should be permitted (allowed) for all users, whether they are authenticated or not.
.anyRequest().authenticated():
This is a more general matcher that specifies any request (not already matched by previous matchers) should be authenticated,
meaning users have to provide valid credentials to access these endpoints. .and(): This is a method to join several configurations.
It helps to continue the configuration from the root (HttpSecurity). .formLogin(): This enables form-based authentication. By default,
it will provide a form for the user to enter their username and password.
 If the user is not authenticated, and they try to access a secured endpoint, they'll be redirected to the default login form.
*/