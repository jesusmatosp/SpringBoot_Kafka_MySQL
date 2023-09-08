package pe.com.intercorp.challenge.Microservice03.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.intercorp.challenge.Microservice03.entities.Metadata;
import pe.com.intercorp.challenge.Microservice03.repository.MetadataRepository;

import java.util.List;

@Service
public class MetadataServiceImpl implements MetadataService{

    @Autowired
    private MetadataRepository repository;

    @Override
    public List<Metadata> retrieveMetadata() {
        return repository.findAll();
    }
}
