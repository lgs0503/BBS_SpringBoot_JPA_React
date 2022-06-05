package lgs.bbs.comment.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    private Long postIdx;
    private String upperCommentIdx;
    private String commentName;
    private String commentContent;
    private String createdUser;

    private long likeCnt;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Comment(Long postIdx,
                   String upperCommentIdx,
                   String commentName,
                   String commentContent,
                   String createdUser,
                   long likeCnt,
                   String useYn,
                   String deleteYn) {
        this.postIdx = postIdx;
        this.upperCommentIdx = upperCommentIdx;
        this.commentName = commentName;
        this.commentContent = commentContent;
        this.createdUser = createdUser;
        this.createdUser = createdUser;
        this.likeCnt = likeCnt;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
