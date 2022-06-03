package lgs.bbs.popup.entity;

import lgs.bbs.popup.entity.Popup;

import java.util.List;

public interface PopupRepositoryCustom {

    List<Popup> searchList(Popup popup);

    Long searchListCount(Popup popup);

}
