package pe.com.intercorp.challenge.BatchProcess.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import pe.com.intercorp.challenge.BatchProcess.dto.DocumentDTO;
import pe.com.intercorp.challenge.BatchProcess.entities.Metadata;
import pe.com.intercorp.challenge.BatchProcess.entities.Person;

public class StepOneProcessor implements ItemProcessor<DocumentDTO, Metadata> {

    @Override
    public Metadata process(DocumentDTO item) throws Exception {
        return mapperMetadata(item);
    }

    public Metadata mapperMetadata(DocumentDTO documentDTO) {
        Metadata metadata = new Metadata();
        Person person = new Person();
        BeanUtils.copyProperties(documentDTO, metadata);
        BeanUtils.copyProperties(documentDTO.getPerson(), person);
        metadata.setPerson(person);
        return metadata;
    }
}
