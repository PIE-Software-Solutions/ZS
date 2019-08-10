package com.pss.oneservice.zuulservice;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@Configuration
public class ZuulServiceApplication {

	DiscoveryClient discovery;
	ZuulProperties properties;
	
    private static final Logger logger = LoggerFactory.getLogger(ZuulServiceApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZuulServiceApplication.class,args);
        logger.info("Zuul Service is up and running...");
	}
	
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
	    return new PatternServiceRouteMapper(
	        "(?<name>^.+)-(?<version>v.+$)",
	        "${version}/${name}");
	}
	/*
	@Bean
	public DiscoveryClientRouteLocator getRouteLocator() {
	    return new DiscoveryClientRouteLocator("/", discovery, properties) {
	        @Override
	        protected LinkedHashMap<String, ZuulRoute> locateRoutes() {
	            LinkedHashMap<String, ZuulRoute> routes = super.locateRoutes();
	            
	            routes.values().forEach(r -> r.setStripPrefix(false));
	            
	            return routes;
	        }
	    };
	}*/
	
}
