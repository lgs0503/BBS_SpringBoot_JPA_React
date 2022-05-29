package lgs.bbs.comment.entity;


import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> searchList(Comment comment);

    Long searchListCount(Comment comment);

}
