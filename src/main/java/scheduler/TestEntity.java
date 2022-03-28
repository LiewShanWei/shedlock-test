package scheduler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("test")
@Data
@AllArgsConstructor
public class TestEntity {
    @Id
    private long threadId;
    private String txId;
    private String localDateTimeString;
}
