package lgs.bbs.post.entity;


import java.util.List;

public interface PostRepositoryCustom {

    List<Post> searchList(Post post);

    Long searchListCount(Post post);

}
