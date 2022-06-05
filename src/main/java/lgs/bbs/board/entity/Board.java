package lgs.bbs.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Board")
@NoArgsConstructor
@AllArgsConstructor
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    private String boardName;
    private String boardType;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Board(String boardName,
                  String boardType,
                  String useYn,
                  String deleteYn) {
        this.boardName = boardName;
        this.boardType = boardType;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
