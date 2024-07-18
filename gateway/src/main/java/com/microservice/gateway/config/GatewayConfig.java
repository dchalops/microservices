package com.microservice.gateway.config;

import com.microservice.gateway.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GatewayConfig {
    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service-mongo", r -> r.path("/v1/user-service-mongo/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service-mongo"))

                .route("sample-service", r -> r.path("/v1/sample-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://sample-service"))

                .route("user-service", r -> r.path("/v1/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://user-service"))

                .route("job-service", r -> r.path("/v1/job-service/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://job-service"))

                .route("notification-service", r -> r.path("/v1/notification/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://notification-service"))

                .route("auth-service", r -> r.path("/v1/auth/**")
                        .uri("lb://auth-service"))

                .route("file-storage", r -> r.path("/v1/file-storage/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://file-storage"))
                .build();
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:3033");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
