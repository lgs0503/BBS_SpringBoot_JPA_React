package lgs.bbs.comm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    private String originFileName;
    private String fileName;
    private String fileExtension;
    private String filePath;

    private long fileSize;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public FileInfo(String originFileName,
                String fileName,
                String filePath,
                String fileExtension,
                    long fileSize) {
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
    }
}
