package lgs.bbs.content.entity;

import lgs.bbs.content.entity.Content;

import java.util.List;

public interface ContentRepositoryCustom {

    List<Content> searchList(Content content);

    Long searchListCount(Content content);

}
