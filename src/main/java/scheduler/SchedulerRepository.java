package scheduler;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends MongoRepository<TestEntity, String> {
}
