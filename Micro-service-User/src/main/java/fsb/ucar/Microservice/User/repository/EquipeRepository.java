package fsb.ucar.Microservice.User.repository;


import fsb.ucar.Microservice.User.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    public Optional<Equipe> findByName(String name);
}
