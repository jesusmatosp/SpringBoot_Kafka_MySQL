package pe.com.intercorp.challenge.BatchProcess.services;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pe.com.intercorp.challenge.BatchProcess.entities.Metadata;
import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaProducerService {

  private final Logger LOG = LoggerFactory.getLogger(KafkaProducerService.class);

  @Autowired
  private KafkaTemplate<String, Metadata> kafkaTemplate;

    public void send(Metadata metadata) {
        LOG.info("Sending User Json Serializer : {}", metadata);
        kafkaTemplate.send("topic1-test", metadata);
    }

}
