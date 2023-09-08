package pe.com.intercorp.challenge.BatchProcess.writers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.com.intercorp.challenge.BatchProcess.entities.Metadata;
import pe.com.intercorp.challenge.BatchProcess.services.KafkaProducerService;

import java.util.List;

@Component
public class StepOneWriter implements ItemWriter<Metadata> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepOneWriter.class);



    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public void write(List<? extends Metadata> items) throws Exception {
        items.forEach( item -> {
            LOGGER.info("ITEMS METADATA = [" + item.getRandom() + ", " +
                    item.getRegEx()+ "]");
            // metadataRepository.save(item);
            kafkaProducerService.send(item); // Send to Topic
            LOGGER.info("FORMAT JSON: " + new Gson().toJson(item));
        });
    }

}
