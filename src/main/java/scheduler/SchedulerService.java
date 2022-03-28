package scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
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

    // This only runs once
    @EventListener(ContextStartedEvent.class)
    @SchedulerLock(name = "startSchedulerWithEventListener")
    public void startSchedulerWithEventListener() {
        String txId = "startSchedulerWithEventListener";
        Flux.interval(Duration.ofMinutes(1L))
                .doOnNext(x -> insertTask(txId))
                .subscribe();
    }

    // This will spawn another running thread after each fixedRate duration
    @Scheduled(fixedRate = 1000*60)
    public void startSchedulerWithScheduledAnnotation(){
        String txId = "startSchedulerWithScheduledAnnotation";
        Flux.interval(Duration.ofMinutes(1L))
                .doOnNext(x -> insertTask(txId))
                .subscribe();
    }

    public void insertTask(String from){
        String id = UUID.randomUUID().toString();
        long threadId = Thread.currentThread().getId();
        String dateTimeNowString = LocalDateTime.now().toString();
        System.out.println(id + " - " + from + " - " + threadId + " - " + dateTimeNowString);

        schedulerRepository.insert(new Record(id, from, threadId, dateTimeNowString));
    }
}
