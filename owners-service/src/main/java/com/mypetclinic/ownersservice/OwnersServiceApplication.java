package com.mypetclinic.ownersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
//TODO нужно все-таки разделить pets и owners по отдельным сервисам я думаю, хотя дело вкуса
public class OwnersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnersServiceApplication.class, args);
    }

}
