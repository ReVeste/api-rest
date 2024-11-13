package reveste.brecho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reveste.brecho.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
