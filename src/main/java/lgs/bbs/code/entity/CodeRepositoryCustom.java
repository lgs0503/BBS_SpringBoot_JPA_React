package lgs.bbs.code.entity;

import lgs.bbs.board.entity.Board;

import java.util.List;

public interface CodeRepositoryCustom {

    List<Code> searchList(Code user);

    Long searchListCount(Code user);

}
