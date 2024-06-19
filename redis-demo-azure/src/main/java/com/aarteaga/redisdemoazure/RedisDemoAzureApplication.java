package com.aarteaga.redisdemoazure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class RedisDemoAzureApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisDemoAzureApplication.class);

	@Autowired
	private StringRedisTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(RedisDemoAzureApplication.class, args);
	}


	@Override
	public void run(String... args) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "Hola";
		if(!this.template.hasKey(key)){
			ops.set(key, "Diane");
			LOGGER.info("Add a key is done");
		}

		String key2 = "Name";

		if(this.template.hasKey(key2)){
			LOGGER.info("Key Name exist");
			LOGGER.info("Return the value from the cache: {}", ops.get(key2));
		}

		LOGGER.info("Return the value from the cache: {}", ops.get(key));
	}


}
