package fsb.ucar.Microservice.QCM.repository;

import fsb.ucar.Microservice.QCM.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByCategory(String category);

    List<Question> findByDifficultylevel(String difficultylevel);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category AND q.difficultylevel = :difficultylevel ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Long> findRandomQuestionsByCategoryAndDifficultyLevel(String category, String difficultylevel, int numQ);

    @Query(value = "SELECT COUNT(q.id) FROM question q WHERE q.category = :category AND q.difficultylevel = :difficultylevel", nativeQuery = true)
    int countQuestionsByCategoryAndDifficultyLevel(String category, String difficultylevel);

    @Query(value = "SELECT * FROM question q WHERE q.category = :category AND q.difficultylevel = :difficultylevel", nativeQuery = true)
    List<Question> getQuestionsByCategoryAndDifficultyLevel(String category, String difficultylevel);




}
