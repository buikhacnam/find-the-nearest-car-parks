package com.wego.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DemoApplication  {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("hi mom!");
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		int bufferSize = 16 * 1024 * 1024;
		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
				.codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
						.maxInMemorySize(bufferSize))
				.build();

		return WebClient.builder().exchangeStrategies(exchangeStrategies);
	}

}
