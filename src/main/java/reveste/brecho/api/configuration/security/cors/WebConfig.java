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
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Especifique o frontend
        config.addAllowedOrigin("https://back-reveste.azurewebsites.net"); // Especifique o frontend
        config.addAllowedHeader("");
        config.addAllowedMethod("");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
