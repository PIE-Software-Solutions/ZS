package com.pss.oneservice.zuulservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.stereotype.Component;
 
import java.util.LinkedHashMap;
import java.util.List;
 
@Component
public class MyRouteLocator extends DiscoveryClientRouteLocator {
 
    private static final Log log = LogFactory.getLog(MyRouteLocator.class);
 
    private DiscoveryClient discoveryClient;
 
    MyRouteLocator(ServerProperties server, DiscoveryClient discoveryClient,
                   ZuulProperties properties, ServiceRouteMapper mapper, 
                   ServiceInstance localServiceInstance) {
        super(server.getServlet().getContextPath(), discoveryClient, 
              properties, mapper, localServiceInstance);
        this.discoveryClient = discoveryClient;
    }
 
    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = super.locateRoutes();        
        routesMap.values().forEach(r -> r.setStripPrefix(false));
        
        return routesMap;
    }
}