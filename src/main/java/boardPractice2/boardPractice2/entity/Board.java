package boardPractice2.boardPractice2.entity;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {
    private int boardId;
    private String title;
    private String content;
    private int userId;
    private LocalDateTime regdate;

    private int viewCnt;
    private String name; // join을 위해 일부러 추가!
}
