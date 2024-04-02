package com.gateway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/tracker/**")
						.filters(f -> f.rewritePath("/tracker/(?<segment>.*)", "/${segment}")
								.circuitBreaker(
										config -> config.setName("trackerCircuitBreaker")
												.setFallbackUri("forward:/contactSupport")))
						.uri("lb://TRACKER"))
				.build();
	}

}
