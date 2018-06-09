package org.learning.by.example.reactive.microservices.application;

import org.learning.by.example.reactive.microservices.handlers.ApiHandler;
import org.learning.by.example.reactive.microservices.handlers.ErrorHandler;
import org.learning.by.example.reactive.microservices.routers.MainRouter;
import org.learning.by.example.reactive.microservices.services.GeoLocationService;
import org.learning.by.example.reactive.microservices.services.GeoLocationServiceImpl;
import org.learning.by.example.reactive.microservices.services.SunriseSunsetService;
import org.learning.by.example.reactive.microservices.services.SunriseSunsetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
//@EnableWebFlux
class ApplicationConfig {

    @Bean
    GeoLocationService locationService(@Value("${GeoLocationServiceImpl.endPoint}") final String endPoint) {
        return new GeoLocationServiceImpl(endPoint);
    }

    @Bean
    SunriseSunsetService sunriseSunsetService(@Value("${SunriseSunsetServiceImpl.endPoint}") final String endPoint) {
        return new SunriseSunsetServiceImpl(endPoint);
    }

    @Bean
    ErrorHandler errorHandler() {
        return new ErrorHandler();
    }

    @Bean
    ApiHandler apiHandler(@Autowired GeoLocationService geoLocationService,
                          @Autowired SunriseSunsetService sunriseSunsetService,
                          @Autowired ErrorHandler errorHandler) {
        return new ApiHandler(geoLocationService, sunriseSunsetService, errorHandler);
    }

    @Bean
    RouterFunction<?> mainRouterFunction(@Autowired ApiHandler apiHandler,
                                         @Autowired ErrorHandler errorHandler) {
        return MainRouter.doRoute(apiHandler, errorHandler);
    }
}
