package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigCenterBusApp
{
    public static void main( String[] args )
    {
            SpringApplication.run(ConfigCenterBusApp.class, args);
    }
}
