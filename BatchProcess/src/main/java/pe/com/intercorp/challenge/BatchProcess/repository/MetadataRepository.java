package pe.com.intercorp.challenge.BatchProcess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.intercorp.challenge.BatchProcess.entities.Metadata;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {
}
