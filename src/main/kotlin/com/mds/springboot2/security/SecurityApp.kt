package com.mds.springboot2.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityApp : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder) {
        // this code sets up a user store in memory.
        // this is useful fo debugging and development
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("ronan")
                .password("ronan")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("USER", "ADMIN")

    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/VAADIN/**", "/PUSH/**", "/UIDL/**", "/login", "/login/**",
                        "/error/**", "/accessDenied/**", "/vaadinServlet/**", "/").permitAll()
                .antMatchers("/authorized", "/**").fullyAuthenticated()
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                .and()
                    .httpBasic()
        http.csrf().disable();
    }
}