package lgs.bbs.user.entity;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> searchList(User user);

    Long searchListCount(User user);

    List<User> searchDetail(User user);

}
