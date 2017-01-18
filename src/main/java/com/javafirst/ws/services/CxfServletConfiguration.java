package com.javafirst.ws.services;

import javax.xml.namespace.QName;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;

import com.javafirst.ws.services.paymentProcessor.PaymentProcessorImpl;

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
        
        return endpoint;
    }
	
}
