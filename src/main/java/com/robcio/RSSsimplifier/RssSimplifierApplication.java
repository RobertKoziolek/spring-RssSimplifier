package com.robcio.RSSsimplifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RssSimplifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(RssSimplifierApplication.class, args);
	}
}
