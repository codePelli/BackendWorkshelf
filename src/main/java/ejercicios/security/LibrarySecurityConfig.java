package ejercicios.security;


import org.apache.catalina.filters.CorsFilter;
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
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ejercicios.jwt.JWTAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class LibrarySecurityConfig {
	@Autowired
	private JWTAuthenticationFilter authenticationFilter;
	
	@Autowired
	private LibraryUserDetailsService userDetailsService;

    private static final String[] SECURED_URLs = {"/user/**"};

    private static final String[] UN_SECURED_URLs = {
            //"/books/all",
            "/user",
            "/user/**",
            "/login/**",
            "/swagger-ui",
            "/swagger-ui/**",
            "/role",
            "/role/**"
    };


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }
    
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors(cors -> cors.disable())
        		.csrf(csrf -> csrf.disable())
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/**"))
                .authorizeExchange()
                .anyExchange().authenticated().and()
                .httpBasic();
        return http.build();
    }

   /* @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        		.csrf(csrf -> csrf.disable())
        		.cors(cors -> corsConfigurationSource())
        		.authorizeHttpRequests(auth -> auth
        		.requestMatchers("/login/{id}").access(new WebExpressionAuthorization("#name == authentication.name"))
        	
        		
        		.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(UN_SECURED_URLs).permitAll().and()
                .authorizeHttpRequests().requestMatchers(SECURED_URLs)
                .hasAuthority("ADMIN").anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	
    	CorsConfiguration config = new CorsConfiguration();
    	
    	config.addAllowedOrigin("*");
    	config.addAllowedMethod("*");
    	config.addAllowedHeader("*");
    	config.setAllowCredentials(true);
    	
    	UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
    	src.registerCorsConfiguration("/**", config);
    	return src;
    }

}
