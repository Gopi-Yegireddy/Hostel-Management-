package com.CH22_Project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.CH22_Project.service.LogoutHandlerService;
import com.CH22_Project.service.LogoutSuccessHandlerService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	private LogoutHandlerService logoutHandlerService;
    private LogoutSuccessHandlerService logoutSuccessHandlerService;


    @Autowired
    public SecurityConfig(LogoutHandlerService logoutHandlerService, LogoutSuccessHandlerService logoutSuccessHandlerService) {
        this.logoutHandlerService = logoutHandlerService;
        this.logoutSuccessHandlerService = logoutSuccessHandlerService;
    }	
    	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(customizer -> customizer.disable())
				.authorizeHttpRequests(requests -> requests
													.requestMatchers("/owner/register","/owner/login","/user/register","/user/login","/owner/generate-username").permitAll()
													.anyRequest().authenticated())
//				.formLogin(Customizer.withDefaults())
//				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(l -> l.logoutRequestMatcher(new AntPathRequestMatcher("/owner/logout"))
							  .addLogoutHandler(logoutHandlerService)
							  .logoutSuccessHandler(logoutSuccessHandlerService)
							  .logoutSuccessUrl("/owner/login"))
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	

}
