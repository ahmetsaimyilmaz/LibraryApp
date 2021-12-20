package com.example.libraryapp.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userDetailsService);
        provider.setPasswordEncoder((PasswordEncoder)new BCryptPasswordEncoder());
        return (AuthenticationProvider)provider;
    }

    protected void configure(HttpSecurity http) throws Exception {
        ((HttpSecurity)((FormLoginConfigurer)((FormLoginConfigurer)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)http
                .csrf().disable())
                .authorizeRequests()

                .antMatchers(new String[] { "/login*" })).permitAll()
                .anyRequest()).authenticated()
                .and())
                .formLogin()
                .defaultSuccessUrl("/", true))
                .failureUrl("/login.html?error=true"))
                .and())
                .logout().logoutRequestMatcher((RequestMatcher)new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }
}
