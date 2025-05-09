package com.example.demo.configuration;
import java.util.Arrays;

/* 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todos los endpoints de la API
                .allowedOrigins("http://localhost:4200")  // Frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")  // Todos los headers permitidos
                .allowCredentials(true);  // Permite credenciales (por ejemplo, cookies o autenticación HTTP)
    }
}

*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import io.jsonwebtoken.lang.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf().disable()  // Desactiva CSRF si estás utilizando JWT
            .authorizeRequests()
            .requestMatchers("/api/**").permitAll()  // Permite todas las solicitudes API sin autenticación
       
           // .anyRequest().authenticated();  // Otras solicitudes requieren autenticación
           .and()
           .httpBasic().disable();
        return http.build();
    }

    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));  // tu frontend
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // Aplica a toda la API
        return source;
    }
}
