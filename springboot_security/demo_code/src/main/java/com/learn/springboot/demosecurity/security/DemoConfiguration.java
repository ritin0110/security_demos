package com.learn.springboot.demosecurity.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.learn.springboot.demosecurity.service.UserService;

@Configuration
public class DemoConfiguration {

	@Bean
	public UserDetailsManager userDetailManagerJDBC(DataSource dataSource) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("select user_id, security_key, active from custom_users where user_id=?");
		manager.setAuthoritiesByUsernameQuery("select user_id, role_id from custom_authorities where user_id=?");
		return manager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserService userService) {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailManagerMemory() {
		UserDetails demoEmp = User.builder()
								.username("demoemp")
								.password("{noop}abc123")
								.roles("EMPLOYEE")
								.build();
		
		UserDetails demoManager = User.builder()
							.username("demomanager")
							.password("{noop}abc123")
							.roles("EMPLOYEE", "MANAGER")
							.build();
		
		UserDetails demoHR = User.builder()
							.username("demohr")
							.password("{bcrypt}$2a$12$GUTW7HWLSOj.lO1CuLVKBeLBLEq.YCohtsDSVuxINTjoi6dzV6es6")
							.roles("EMPLOYEE", "MANAGER", "HR")
							.build();
		
		return new InMemoryUserDetailsManager(demoEmp, demoManager, demoHR);
	}
	*/
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> 
					configurer
					.requestMatchers("/").hasRole("EMPLOYEE")
					.requestMatchers("/managers/**").hasAnyRole("MANAGER","HR")
					.requestMatchers("/hrpage/**").hasRole("HR")
					.anyRequest().authenticated()
			).formLogin(form -> 
						form.loginPage("/customloginpage").loginProcessingUrl("/customloginurl").permitAll()
			).logout( logout -> logout.permitAll()
			).exceptionHandling(object -> object.accessDeniedPage("/accessdenied")
					);
		return http.build();
	}
}
