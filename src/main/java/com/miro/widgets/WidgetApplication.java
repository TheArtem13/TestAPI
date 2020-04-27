package com.miro.widgets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import config.*;

@SpringBootApplication
public class WidgetApplication implements WebMvcConfigurer {
	
	@Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    Refill refill = Refill.greedy(1000, Duration.ofMinutes(1));
	    Bandwidth limit = Bandwidth.classic(10, refill).withInitialTokens(1);
	    Bucket bucket = Bucket4j.builder().addLimit(limit).build();
	    registry.addInterceptor(new RateLimitInterceptor(bucket, 1)).addPathPatterns("/*");

	    refill = Refill.intervally(200, Duration.ofMinutes(1));
	    limit = Bandwidth.classic(10, refill);
	    bucket = Bucket4j.builder().addLimit(limit).build();
	    registry.addInterceptor(new RateLimitInterceptor(bucket, 1))
	        .addPathPatterns("/list/"); 
	}
	
    public static void main(String[] args) {
        SpringApplication.run(WidgetApplication.class, args);
        
    }
    
}
