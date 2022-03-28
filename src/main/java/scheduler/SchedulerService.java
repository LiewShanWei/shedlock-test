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
    public void startSchedulerWithEventListener(){
        String txId = UUID.randomUUID().toString() + " - startSchedulerWithEventListener";
        Flux.interval(Duration.ofMinutes(1L))
                .doOnNext(x -> insertTask(txId))
                .subscribe();
    }

    public void insertTask(String txId){
        long threadId = Thread.currentThread().getId();
        String dateTimeNowString = LocalDateTime.now().toString();
        System.out.println(threadId + " - " + dateTimeNowString + " - " + txId);

        schedulerRepository.insert(new TestEntity(Thread.currentThread().getId(), txId, LocalDateTime.now().toString()));
    }
}
