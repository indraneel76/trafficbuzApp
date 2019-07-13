package com.inda.trafficbuz.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

	 @Autowired
	 DataSource dataSource;
	 
	 @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth.jdbcAuthentication().dataSource(dataSource)
		  .usersByUsernameQuery(
		   "select username,password,enabled from users where username=?")
		   .authoritiesByUsernameQuery(
		   "select username, role from user_roles where username=?")
		   .passwordEncoder(new BCryptPasswordEncoder());
		   
		 }
	 
	
	 @Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.anyRequest().authenticated()
					.and()
				.requestCache()
					.requestCache(new NullRequestCache())
					.and()
				.httpBasic();
		}
	 
	 
	 
	 /*
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests()
	  .antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
	  .and()
	    .formLogin().loginPage("/login")
	    .usernameParameter("username").passwordParameter("password")
	  .and()
	    .logout().logoutSuccessUrl("/login?logout") 
	   .and()
	   .exceptionHandling().accessDeniedPage("/403")
	  .and()
	    .csrf();
	 }
	 
	 */
	/*
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {

	 http.authorizeRequests().anyRequest().hasAnyRole("ROLE_ADMIN", "ROLE_USER")
	    .and()
	    .httpBasic(); // Authenticate users with HTTP basic authentication

	 }
	 
	 */
    }
	
