package scheduler;

import com.mongodb.client.MongoClient;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "15M", defaultLockAtLeastFor = "PT1M")
public class ShedlockConfig {
    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Bean
    public LockProvider lockProvider(MongoClient mongo) {
        return new MongoLockProvider(mongo.getDatabase(databaseName));
    }
}
