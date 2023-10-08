package tn.esprit.CROTUN.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.RequiredTypes;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tn.esprit.CROTUN.Entities.Agent;
import tn.esprit.CROTUN.Event.MySimpleUrlAuthenticationSuccessHandler;
import tn.esprit.CROTUN.Services.AgentService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private   UserDetailsService userDetailService;
	
	@Autowired
	AgentService agentService;

	@Autowired
	DataSource dataSource;
	
	@Autowired
	private CustomAuthenticationFilter authenticationFilter;
	

	private static final Logger logger = LogManager.getLogger(SecurityConfiguration.class);
	
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
	auth.userDetailsService(userDetailService).passwordEncoder(getPasswordEncoder());
	
		
		
	
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Bean
	public JWTAuthenticationEntryPoint getAuthenticationEntryPoint() {
		return new JWTAuthenticationEntryPoint();
	}
	

	
	@Override
	@Bean
	
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().
		authorizeRequests()
		
		
		
		.antMatchers("/complaint/**").hasAnyAuthority("agent","customer","manager")
		.antMatchers("/availablity/**").hasAnyAuthority("agent","customer")
		.antMatchers("/investmentoffer/**").hasAnyAuthority("investor","manager")
		.antMatchers("/investor/**").hasAnyAuthority("investor","manager")
		.antMatchers("/investment/**").hasAnyAuthority("investor","manager")
		.antMatchers("/loan/**").hasAnyAuthority("customer","agent","customer")
		//.antMatchers("/slice/**").hasAnyAuthority("agent","manager")
		.antMatchers("/DetailLoan/**").hasAnyAuthority("agent","manager")
		.antMatchers("/manager/**").hasAuthority("manager")
		.antMatchers("/agent/**").hasAnyAuthority("agent","manager")
		.antMatchers("/availablity/**").hasAnyAuthority("agent","customer")
		.antMatchers("/investment/**").hasAuthority("investor")
		.antMatchers("/customer/**").hasAnyAuthority("customer","manager")
		.antMatchers("/investor/**").hasAuthority("investor")
		.antMatchers("/auth/**").permitAll()
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPoint());
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
		
		
		}
	
	
	
}
