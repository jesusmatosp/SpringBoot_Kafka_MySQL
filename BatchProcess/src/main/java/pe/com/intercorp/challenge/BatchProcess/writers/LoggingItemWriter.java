package pe.com.intercorp.challenge.BatchProcess.writers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import pe.com.intercorp.challenge.BatchProcess.dto.DocumentDTO;

import java.util.List;

public class LoggingItemWriter implements ItemWriter<DocumentDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemWriter.class);
    @Override
    public void write(List<? extends DocumentDTO> list) throws Exception {
        LOGGER.info("Writing students: {}", list);
        list.forEach( item -> {
            System.out.println("Valor " + item.getRandom() + " " + item.getRandomFloat()
                + " :: " + item.getElt().size());
            LOGGER.info("Person:" + item.getPerson().getFirstName() + " " + item.getPerson().getLastName());
        });
    }
}
