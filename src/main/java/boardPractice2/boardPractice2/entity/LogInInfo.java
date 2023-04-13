package boardPractice2.boardPractice2.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LogInInfo {
    private int userId;
    private String name;
    private String email;
}
