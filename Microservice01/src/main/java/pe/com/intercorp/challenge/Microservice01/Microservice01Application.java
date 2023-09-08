package pe.com.intercorp.challenge.Microservice01;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import pe.com.intercorp.challenge.Microservice01.model.Metadata;
import pe.com.intercorp.challenge.Microservice01.service.KafkaProducerService;
import java.util.Date;

@SpringBootApplication
public class Microservice01Application {
	private final Logger LOG = LoggerFactory.getLogger(Microservice01Application.class);

	@Autowired
	private KafkaProducerService producerService;

	@KafkaListener(topics ="topic1-test", groupId ="group_id")
	public void listen(String message) throws JsonProcessingException {
		System.out.println("Received Messasge in group id: " + message);
		Metadata metadata = new ObjectMapper().readValue(message, Metadata.class);
		LOG.info("DATA: " + metadata.toString());
		metadata.setLastUpdate(new Date());
		producerService.send(metadata);
	}

	public static void main(String[] args) {
		SpringApplication.run(Microservice01Application.class, args);
	}

}
