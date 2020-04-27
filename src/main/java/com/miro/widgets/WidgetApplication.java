package com.miro.widgets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.miro.widgets.config.*;

import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;

@SpringBootApplication
public class WidgetApplication implements WebMvcConfigurer {
	
	@Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    Bandwidth limit = Bandwidth.simple(1000, Duration.ofMinutes(1));
	    Bucket bucket = Bucket4j.builder().addLimit(limit).build();
	    registry.addInterceptor(new RateLimitInterceptor(bucket, 3)).addPathPatterns("/widget*");

	    limit = Bandwidth.simple(200, Duration.ofMinutes(1));
	    bucket = Bucket4j.builder().addLimit(limit).build();
	    registry.addInterceptor(new RateLimitInterceptor(bucket, 3))
	        .addPathPatterns("/list"); 
	}
	
    public static void main(String[] args) {
        SpringApplication.run(WidgetApplication.class, args);
        
    }
    
}
