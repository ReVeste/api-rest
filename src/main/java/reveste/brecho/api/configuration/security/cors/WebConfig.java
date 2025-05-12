package reveste.brecho.api.configuration.security.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("http://localhost:3000"); // Especifique o frontend
        config.addAllowedOrigin("http://localhost:8080"); // Especifique o frontend
        config.addAllowedOrigin("https://black-meadow-0cd280a0f.4.azurestaticapps.net/"); // Especifique o frontend
        config.addAllowedOrigin("http://10.0.2.2:8080"); // Mobile

        config.addAllowedOrigin("http://13.217.123.64"); // IP específico permitido
        config.addAllowedOrigin("http://13.217.123.64:3000"); // IP específico permitido
        config.addAllowedOrigin("http://13.217.123.64:8080"); // IP específico permitido
        config.addAllowedOrigin("http://35.171.135.32"); // IP específico permitido

        config.addAllowedOriginPattern("http://localhosto:*");
        config.addAllowedOriginPattern("http://13.217.123.64:*"); // IP específico permitido
        config.addAllowedOriginPattern("http://10.0.0.*"); // IP específico permitido
        config.addAllowedOriginPattern("http://10.0.1.*"); // IP específico permitido

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}