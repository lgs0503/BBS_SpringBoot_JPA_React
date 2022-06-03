package lgs.bbs.menu.entity;

import lgs.bbs.menu.entity.Menu;

import java.util.List;

public interface MenuRepositoryCustom {

    List<Menu> searchList(Menu menu);

    Long searchListCount(Menu menu);

}
