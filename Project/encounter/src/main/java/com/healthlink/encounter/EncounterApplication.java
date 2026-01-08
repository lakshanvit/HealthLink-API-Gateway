package com.healthlink.encounter;

import com.healthlink.encounter.dto.EncounterContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {EncounterContactInfoDto.class})
public class EncounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncounterApplication.class, args);
	}

}
