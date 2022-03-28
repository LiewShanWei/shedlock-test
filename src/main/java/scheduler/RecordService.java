package scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Scheduled(cron = "${scheduler.cron}")
    @SchedulerLock(name = "insertTask")
    public void insertTask(){
        String id = UUID.randomUUID().toString();
        long threadId = Thread.currentThread().getId();
        String dateTimeNowString = LocalDateTime.now().toString();
        System.out.println("InsertTask - " + id + " - " + threadId + " - " + dateTimeNowString);

        recordRepository.insert(new Record(id, threadId, dateTimeNowString));
    }

    @Scheduled(cron = "${scheduler.cron}")
    @SchedulerLock(name = "insertTask")
    public void insertTask2(){
        String id = UUID.randomUUID().toString();
        long threadId = Thread.currentThread().getId();
        String dateTimeNowString = LocalDateTime.now().toString();
        System.out.println("InsertTask2 - " + id + " - " + threadId + " - " + dateTimeNowString);

        recordRepository.insert(new Record(id, threadId, dateTimeNowString));
    }
}
