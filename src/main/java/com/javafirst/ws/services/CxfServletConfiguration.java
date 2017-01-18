package com.javafirst.ws.services;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.Bus;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;

import com.javafirst.ws.services.fileUploadService.FileServiceImpl;
import com.javafirst.ws.services.interceptors.CustomInterceptor;
import com.javafirst.ws.services.paymentProcessor.PaymentProcessorImpl;
import com.javafirst.ws.services.security.AuthHandler;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CxfServletConfiguration {

	@Autowired
    private ApplicationContext applicationContext;
	
	// services will be available at hostname:post/services
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
    	CXFServlet cxfServlet = new CXFServlet();
        return new ServletRegistrationBean(cxfServlet, "/services/*");
    }
    
    // configure individual services
    @DependsOn("servletRegistrationBean")
    @Bean
    public EndpointImpl paymentProcessorService() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, new PaymentProcessorImpl());
        QName serviceName = new QName("http://customerorders.services.lance.com/", "PaymentProcessor");
        endpoint.setServiceName(serviceName);
        endpoint.publish("/PaymentProcessor");
     
        //custom interceptor gets called second. declared out of order intentionally as a reminder that order of declaration is not necessarily relevant when phases are specififed
        endpoint.getInInterceptors().add(getCustomInterceptor());
        endpoint.getInInterceptors().add(getAuthInterceptor());
        
        return endpoint;
    }
    
    @DependsOn("servletRegistrationBean")
    @Bean
    public EndpointImpl fileService() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, new FileServiceImpl());
        QName serviceName = new QName("http://customerorders.services.lance.com/", "FileService");
        endpoint.setServiceName(serviceName);
        endpoint.publish("/FileService");
        endpoint.getProperties().put("mtom-enabled", true);
        return endpoint;
    }
    
    @Bean
    public AbstractSoapInterceptor getCustomInterceptor(){
    	return new CustomInterceptor();
    }
    
    @Bean
    public AuthHandler getAuthHandler(){
    	return new AuthHandler();
    }
    
    @Bean
    public WSS4JInInterceptor getAuthInterceptor(){
    	Map<String, Object> map = new HashMap<>();
    	map.put("action", "UsernameToken");
    	map.put("passwordType", "PasswordText");
    	map.put("passwordCallbackRef", getAuthHandler());
    	return new WSS4JInInterceptor(map);
    }
	
}
