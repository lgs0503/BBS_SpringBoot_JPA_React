package lgs.bbs.popup.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Entity(name = "Popup")
@NoArgsConstructor
@AllArgsConstructor
public class Popup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long popupId;

    private String popupName;

    private String popupType; //IMAGE, HTML

    private long fileNo;

    private long xPos;
    private long yPos;

    private long width;
    private long height;

    @Lob
    private Blob html;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Popup(String popupName,
                 String popupType,
                 long fileNo,
                 long xPos,
                 long yPos,
                 long width,
                 long height,
                 Blob html,
                 String useYn,
                 String deleteYn) {
        this.popupName = popupName;
        this.popupType = popupType;
        this.fileNo = fileNo;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.html = html;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
