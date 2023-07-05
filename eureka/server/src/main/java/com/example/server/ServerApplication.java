package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		ServerApplication.class.getClassLoader().loadClass("okio.ByteString");
		ServerApplication.class.getClassLoader().loadClass("okio.SegmentedByteString");
		SpringApplication.run(ServerApplication.class, args);
	}

}
