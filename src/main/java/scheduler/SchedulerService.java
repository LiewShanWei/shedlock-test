package scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    @Autowired
    private SchedulerRepository schedulerRepository;

    @EventListener(ContextStartedEvent.class)
    public void startScheduler(){
        String txId = UUID.randomUUID().toString() + " - ContextStartedEvent";
        Flux.interval(Duration.ofMinutes(1L))
                .doOnNext(x -> printTask(txId))
                .doOnNext(x -> insertTask(txId))
                .subscribe();
    }

    public void printTask(String txId){
            System.out.println(Thread.currentThread().getId() + " - " + LocalDateTime.now().toString() + " - " + txId);
    }

    public void insertTask(String txId){
        schedulerRepository.insert(new TestEntity(UUID.randomUUID().toString(), txId));
    }
}
