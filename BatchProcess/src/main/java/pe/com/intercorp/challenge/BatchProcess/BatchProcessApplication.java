package pe.com.intercorp.challenge.BatchProcess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pe.com.intercorp.challenge.BatchProcess.entities.Metadata;
import pe.com.intercorp.challenge.BatchProcess.repository.MetadataRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class BatchProcessApplication{
	@Autowired
	private MetadataRepository metadataRepository;

	@Autowired
	private JobLauncher jobLauncher;

	private final Logger LOG = LoggerFactory.getLogger(BatchProcessApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BatchProcessApplication.class, args);
	}

	@KafkaListener(topics ="topic3-test", groupId ="group_id")
	public void listen(String message) throws JsonProcessingException {
		System.out.println("Received Messasge in group id: " + message);
		Metadata metadata = new ObjectMapper().readValue(message, Metadata.class);
		LOG.info("DATA A ENVIAR A BD: " + metadata.toString());
		metadataRepository.save(metadata);
	}

}
