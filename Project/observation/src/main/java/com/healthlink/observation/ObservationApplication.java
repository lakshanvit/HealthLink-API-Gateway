package com.healthlink.observation;

import com.healthlink.observation.dto.ObservationContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {ObservationContactInfoDto.class})
public class ObservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObservationApplication.class, args);
	}

}
