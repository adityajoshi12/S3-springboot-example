package com.example.springs3demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class S3Config {

    private static final Logger log = LoggerFactory.getLogger(S3Config.class);

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secret}")
    private String secret;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 s3() {
        validateProperties();

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secret);

        // Logging the creation of AmazonS3 bean
        log.info("Creating AmazonS3 client with region: {}", region);

        return AmazonS3ClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    private void validateProperties() {
        if (accessKey == null || accessKey.isEmpty()) {
            throw new IllegalArgumentException("AWS accessKey must not be null or empty");
        }
        if (secret == null || secret.isEmpty()) {
            throw new IllegalArgumentException("AWS secret must not be null or empty");
        }
        if (region == null || region.isEmpty()) {
            throw new IllegalArgumentException("AWS region must not be null or empty");
        }
    }
}
