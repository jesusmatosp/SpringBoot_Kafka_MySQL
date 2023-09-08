package pe.com.intercorp.challenge.Microservice03.services;

import org.springframework.stereotype.Service;
import pe.com.intercorp.challenge.Microservice03.entities.Metadata;

import java.util.List;

@Service
public interface MetadataService {
    List<Metadata> retrieveMetadata();
}
