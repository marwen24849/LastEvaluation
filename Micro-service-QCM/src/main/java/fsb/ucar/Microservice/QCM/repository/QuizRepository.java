package fsb.ucar.Microservice.QCM.repository;

import fsb.ucar.Microservice.QCM.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCategory(String category);


    @Query(value = "select * from quiz q where q.category = :category AND q.difficultylevel = :difficultylevel" , nativeQuery = true)
    List<Quiz> findByCategoryDifficultylevel(String category, String difficultylevel);

}
