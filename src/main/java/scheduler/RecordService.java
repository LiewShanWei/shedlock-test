package scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
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
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @EventListener(ContextStartedEvent.class)
    public void startSchedulerWithEventListener() {
        String txId = "startSchedulerWithEventListener";
        Flux.interval(Duration.ofMinutes(1L))
                .doOnNext(x -> insertTask(txId))
                .doOnNext(x -> insertTask2(txId))
                .subscribe();
    }

    @SchedulerLock(name = "insertTask", lockAtMostFor = "PT2M")
    public void insertTask(String from){
        String id = UUID.randomUUID().toString();
        long threadId = Thread.currentThread().getId();
        String dateTimeNowString = LocalDateTime.now().toString();
        System.out.println("InsertTask - " + id + " - " + from + " - " + threadId + " - " + dateTimeNowString);

        recordRepository.insert(new Record(id, from, threadId, dateTimeNowString));
    }

    @SchedulerLock(name = "insertTask", lockAtMostFor = "PT2M")
    public void insertTask2(String from){
        String id = UUID.randomUUID().toString();
        long threadId = Thread.currentThread().getId();
        String dateTimeNowString = LocalDateTime.now().toString();
        System.out.println("InsertTask2 - " + id + " - " + from + " - " + threadId + " - " + dateTimeNowString);

        recordRepository.insert(new Record(id, from, threadId, dateTimeNowString));
    }
}
