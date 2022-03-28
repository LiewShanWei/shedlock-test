package scheduler;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SchedulerService {

    @EventListener(ContextStartedEvent.class)
    public void startScheduler(){
        Flux.interval(Duration.ofMinutes(1L))
                .doOnNext(x -> runTask(UUID.randomUUID().toString()))
                .subscribe();
    }

    public void runTask(String txId){
        System.out.println(Thread.currentThread().getId() + " - " + LocalDateTime.now().toString() + " - " + txId);
    }
}
