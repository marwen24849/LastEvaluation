package fsb.ucar.Microservice.User.repository;



import fsb.ucar.Microservice.User.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    List<Competence> findByNameContaining(String name);
}
