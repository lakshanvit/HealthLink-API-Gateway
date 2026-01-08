package com.healthlink.message.functions;

import com.healthlink.message.dto.PatientMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<PatientMsgDto, PatientMsgDto> email() {
        return patientMsgDto -> {
            log.info("Sending email with the details : " +  patientMsgDto.toString());
            return patientMsgDto;
        };
    }

    @Bean
    public Function<PatientMsgDto, Long> sms() {
        return patientMsgDto -> {
            log.info("Sending sms with the details : " +  patientMsgDto.toString());
            return patientMsgDto.patientId();
        };
    }
}
