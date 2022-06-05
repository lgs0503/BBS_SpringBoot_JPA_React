package lgs.bbs.post.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    private Long boardIdx;
    private String postName;

    @Lob
    private Blob postContent;

    private String fileNo1;
    private String fileNo2;
    private String fileNo3;

    private String createdUser;

    private long readCnt;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Post(Long boardIdx,
                String postName,
                Blob postContent,
                String fileNo1,
                String fileNo2,
                String fileNo3,
                String createdUser,
                long readCnt,
                String useYn,
                String deleteYn) {
        this.boardIdx = boardIdx;
        this.postName = postName;
        this.postContent = postContent;
        this.fileNo1 = fileNo1;
        this.fileNo2 = fileNo2;
        this.fileNo3 = fileNo3;
        this.createdUser = createdUser;
        this.readCnt = readCnt;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
