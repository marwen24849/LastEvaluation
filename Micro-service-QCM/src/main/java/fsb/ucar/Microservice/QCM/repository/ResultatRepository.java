package fsb.ucar.Microservice.QCM.repository;


import fsb.ucar.Microservice.QCM.entity.Quiz;
import fsb.ucar.Microservice.QCM.entity.Response;
import fsb.ucar.Microservice.QCM.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {


    @Query(value = "SELECT * FROM resultat r Where r.quiz=:quiz", nativeQuery = true)
    public List<Resultat> findResultatQuiz(Quiz quiz);
}
