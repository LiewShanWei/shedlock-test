package scheduler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("record")
@Data
@AllArgsConstructor
public class Record {
    @Id
    private String id;
    private String from;
    private long threadId;
    private String localDateTimeString;
}
