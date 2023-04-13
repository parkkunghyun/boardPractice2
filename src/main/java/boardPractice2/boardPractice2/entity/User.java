package boardPractice2.boardPractice2.entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private LocalDateTime regdate;
}
