package pe.com.intercorp.challenge.Microservice01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.com.intercorp.challenge.Microservice01.model.Metadata;

@Component
public class KafkaProducerService {

    private final Logger LOG = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, Metadata> kafkaTemplate;

    public void send(Metadata metadata) {
        LOG.info("Sending User Json Serializer : {}", metadata);
        kafkaTemplate.send("topic2-test", metadata);
    }

}
