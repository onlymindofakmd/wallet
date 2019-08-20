package com.app.wallet;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}

	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> {
			builder.indentOutput(true);
			builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		};
	}

//	@Bean
//	public RedisTemplate<String, JsonObject> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		RedisTemplate<String, JsonObject> template = new RedisTemplate<>();
//		template.setConnectionFactory(redisConnectionFactory);
//		return template;
//	}

}
