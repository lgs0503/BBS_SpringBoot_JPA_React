package lgs.bbs.banner.entity;

import lgs.bbs.board.entity.Board;

import java.util.List;

public interface BannerRepositoryCustom {

    List<Banner> searchList(Banner banner);

    Long searchListCount(Banner banner);

}
