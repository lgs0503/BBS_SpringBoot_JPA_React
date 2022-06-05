package lgs.bbs.menu.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Menu")
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuId;

    private long upperMenuId;
    private String menuName;

    private String menuType; //게시판, 컨텐츠, URL

    private String boardId;
    private String url;
    private String contentId;

    private String menuViewPath;

    private String useYn;
    private String deleteYn;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder
    public Menu(long upperMenuId,
                String menuName,
                String menuType,
                String boardId,
                String url,
                String contentId,
                String menuViewPath,
                String useYn,
                String deleteYn) {
        this.upperMenuId = upperMenuId;
        this.menuName = menuName;
        this.menuType = menuType;
        this.boardId = boardId;
        this.url = url;
        this.contentId = contentId;
        this.menuViewPath = menuViewPath;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
