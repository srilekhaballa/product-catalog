package com.shoppingcart.productcatalog.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.shoppingcart.productcatalog.service.CustomUserDetailsService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

	@Configuration
	@EnableSwagger2
	@EnableWebSecurity
	public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	    @Autowired
	    private CustomUserDetailsService customUserDetailsService;
	    
	    @Autowired
		private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;
		
		@Autowired
	  	private JwtAuthenticationEntryPoint unauthorizedHandler;
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests()
	                .antMatchers("/**").permitAll()
	                .anyRequest().authenticated().and().cors().configurationSource(corsConfiguration());
	    }

	    @Bean
	    public CorsConfigurationSource corsConfiguration(){
	        CorsConfiguration corsConfiguration=new CorsConfiguration();
	        corsConfiguration.setAllowedHeaders(List.of("*"));
	        corsConfiguration.setAllowedMethods(List.of("*"));
	        corsConfiguration.setAllowedOrigins(List.of("*"));
	        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
	        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
	        return  urlBasedCorsConfigurationSource;
	    }
	    
	    @Bean
	    public BCryptPasswordEncoder encoder(){
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    @Bean
	    protected AuthenticationManager authenticationManager() throws Exception {
	        return super.authenticationManager();
	    }

	    @Bean
	    public RestTemplate restTemplate(){
	        RestTemplate restTemplate=new RestTemplate();
	        restTemplate.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));
	        return restTemplate;
	    }
	    
	    @Override
	    public UserDetailsService userDetailsServiceBean() throws Exception {
	        return this.customUserDetailsService;
	    }

	    @Override
	    protected UserDetailsService userDetailsService() {
	        return super.userDetailsService();
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
	    }
	} 


