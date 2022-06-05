package lgs.bbs.code.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Code")
@NoArgsConstructor
@AllArgsConstructor
public class Code {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String codeId;

    private String upperCodeIdx;
    private String codeName;
    private String codeValue;

    private String ref1;
    private String ref2;
    private String ref3;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Code(String codeId,
                 String upperCodeIdx,
                 String codeName,
                 String codeValue,
                 String ref1,
                 String ref2,
                 String ref3,
                 String useYn,
                 String deleteYn) {
        this.codeId = codeId;
        this.upperCodeIdx = upperCodeIdx;
        this.codeName = codeName;
        this.codeValue = codeValue;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
