import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "scheduler")
@EnableReactiveMongoRepositories(basePackages = "scheduler")
public class SchedulerApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(SchedulerApplication.class, args);
        context.start();
        System.out.println("Application started");
    }
}
