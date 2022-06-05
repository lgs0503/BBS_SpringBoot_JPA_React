package lgs.bbs.content.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Content")
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contentId;

    private String contentName;

    @Lob
    private Blob html;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Content(String contentName,
                  Blob html,
                  String useYn,
                  String deleteYn) {
        this.contentName = contentName;
        this.html = html;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
