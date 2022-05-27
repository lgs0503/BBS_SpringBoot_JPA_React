package lgs.bbs.board.entity;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> searchList(Board user);

    Long searchListCount(Board user);

}
