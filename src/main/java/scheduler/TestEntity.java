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
    private String id;
    private String txId;
}
