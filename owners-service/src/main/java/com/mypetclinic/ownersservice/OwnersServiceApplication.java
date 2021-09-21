package com.mypetclinic.ownersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OwnersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnersServiceApplication.class, args);
    }

}
