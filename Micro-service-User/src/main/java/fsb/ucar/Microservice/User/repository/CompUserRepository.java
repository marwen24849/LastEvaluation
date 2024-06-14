package fsb.ucar.Microservice.User.repository;


import fsb.ucar.Microservice.User.entity.CompetenceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompUserRepository extends JpaRepository<CompetenceUser, Long> {
}
