package pe.com.intercorp.challenge.Microservice02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import pe.com.intercorp.challenge.Microservice02.model.Metadata;
import pe.com.intercorp.challenge.Microservice02.service.KafkaProducerService;
import pe.com.intercorp.challenge.Microservice02.utils.DateUtils;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class Microservice02Application {
	private final Logger LOG = LoggerFactory.getLogger(Microservice02Application.class);

	@Autowired
	private KafkaProducerService producerService;

	@KafkaListener(topics ="topic2-test", groupId ="group_id")
	public void listen(String message) throws JsonProcessingException {
		System.out.println("Received Messasge in group id: " + message);
		Metadata metadata = new ObjectMapper().readValue(message, Metadata.class);
		LOG.info("DATA TOPIC 2: " + metadata.toString());
		metadata.setLastModified(DateUtils.getDateString(new Date()));
		metadata.setRegEx(UUID.randomUUID().toString());
		producerService.send(metadata);
	}


	public static void main(String[] args) {
		SpringApplication.run(Microservice02Application.class, args);
	}

}
