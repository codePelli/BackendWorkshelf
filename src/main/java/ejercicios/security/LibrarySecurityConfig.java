package ejercicios.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import ejercicios.jwt.JWTAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
    SecurityFilterChain fliterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    .authorizeHttpRequests(auth -> auth.requestMatchers(UN_SECURED_URLs).permitAll())
                    .authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui/**","/doc.html").permitAll())
                    .authorizeHttpRequests(authz -> authz.requestMatchers(SECURED_URLs).hasAuthority("ADMIN").anyRequest().authenticated())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
	
	// CORS Configuration Bean
   @Bean
   CorsConfigurationSource corsConfigurationSource() {
    	
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");  // Allow all origins or Arrays.asList("http://localhost:4200","http://localhost:3000")
        configuration.addAllowedMethod("*");      // Allow all methods or List.of("GET", "POST", "PUT", "DELETE")
        configuration.addAllowedHeader("*");      // Allow all headers
        configuration.setAllowCredentials(true);  // Allow sending of authentication cookies
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
