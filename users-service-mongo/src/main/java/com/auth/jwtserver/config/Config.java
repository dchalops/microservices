package com.auth.jwtserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class Config extends AbstractMongoClientConfiguration {
    
	@Value("${spring.data.mongodb.database}")
    private String databaseName;
    
	@Value("${spring.data.mongodb.uri}")
    private String mongoDbUri;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoDbUri);
    }

    @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:3000") 
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}