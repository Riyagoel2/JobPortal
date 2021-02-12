package com.project.jobsearch.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
@Service
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
	BCryptPasswordEncoder bcryptencoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(bcryptencoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().
                antMatchers("/jobs/**").permitAll()
                .antMatchers("/").permitAll()
                    .and()
                        .formLogin()
                            .loginPage("/login").defaultSuccessUrl("/jobs")
                    .and()
    				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
    				.exceptionHandling();
    }
    
    @Override
	 public void configure(WebSecurity web) throws Exception
	 {
		 web.ignoring().antMatchers("/resources/**", "/static/**" , "/css/**" ,  "/js/**", "/media/**" ,"/templates/**");
		 
	 }


    
}