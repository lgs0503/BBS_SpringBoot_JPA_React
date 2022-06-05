package lgs.bbs.banner.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Banner")
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bannerId;

    private String bannerName;

    private String bannerType; //IMAGE, HTML

    private long fileNo;

    @Lob
    private Blob html;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Banner(String bannerName,
                  String bannerType,
                  long fileNo,
                  Blob html,
                  String useYn,
                  String deleteYn) {
        this.bannerName = bannerName;
        this.bannerType = bannerType;
        this.fileNo = fileNo;
        this.html = html;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
