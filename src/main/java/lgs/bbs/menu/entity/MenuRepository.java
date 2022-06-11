package lgs.bbs.menu.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {

    /*
    * ORACLE CONNECT BY 사용하기위해 JPQL 사용
    */
    @Query(value =
        "SELECT LEVEL " +
        "   ,   MENU_ID " +
        "   ,   MENU_NAME " +
        "   ,   BOARD_ID " +
        "   ,   CONTENT_ID " +
        "   ,   MENU_TYPE " +
        "   ,   URL " +
        "   ,   MENU_VIEW_PATH " +
        "   ,   USE_YN " +
        "   ,   DELETE_YN " +
        "   ,   RULE " +
        "FROM   MENU " +
        "START WITH UPPER_MENU_ID IS NULL " +
        "CONNECT BY PRIOR MENU_ID = UPPER_MENU_ID "
            , nativeQuery = true)
    List<Menu> searchTreeList(Menu menu);
}
