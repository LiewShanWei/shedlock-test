import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SchedulerApplication {
    public static void main(String args[]){
        ConfigurableApplicationContext context = SpringApplication.run(SchedulerApplication.class, args);
        context.start();
        System.out.println("Application started");
    }
}
