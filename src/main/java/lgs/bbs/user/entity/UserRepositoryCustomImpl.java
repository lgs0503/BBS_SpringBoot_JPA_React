package lgs.bbs.user.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<User> searchList(User user) {

        QUser quser = QUser.user; // 추가

        BooleanBuilder builder = new BooleanBuilder();

        /* 조건 조회 */
        if(user.getId() != null) {
            builder.and(quser.id.eq(user.getId()));
        }
        if(user.getName() != null) {
            builder.and(quser.name.eq(user.getName()));
        }

        return jpaQueryFactory.selectFrom(quser)
                .where(builder)
                .fetch();
    }
}
