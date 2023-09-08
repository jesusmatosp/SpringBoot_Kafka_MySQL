package pe.com.intercorp.challenge.Microservice03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.intercorp.challenge.Microservice03.entities.Metadata;
import pe.com.intercorp.challenge.Microservice03.services.MetadataService;

import java.util.List;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

    @Autowired
    private MetadataService service;

    @CrossOrigin( origins =  {"http://localhost:4200", "http://localhost:80", "http://app-web:80"})
    @GetMapping("/all")
    public ResponseEntity<List<Metadata>> retrieveAllMetadata(){
        try {
            List<Metadata> data = service.retrieveMetadata();
            return new ResponseEntity<List<Metadata>>(data, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
