package lgs.bbs.user.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lgs.bbs.comm.UserSha256;
import org.springframework.util.ObjectUtils;

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
        builderCondition(builder, user, quser);

        return jpaQueryFactory
                .select(
                        Projections.bean(
                                User.class,
                                quser.idx,
                                quser.id,
                                quser.name,
                                quser.rule
                        )
                )
                .from(quser)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(User user) {

        QUser quser = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, user, quser);

        return  jpaQueryFactory
                .select(quser.count())
                .from(quser)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    @Override
    public List<User> searchDetail(User user) {

        QUser quser = QUser.user; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, user, quser);

        return jpaQueryFactory
                .select(quser)
                .from(quser)
                .where(builder)
                .fetch();
    }

    private void builderCondition(BooleanBuilder builder, User user, QUser quser){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(user.getId())) {
            builder.and(quser.id.eq(user.getId()));
        }
        if(!ObjectUtils.isEmpty(user.getName())) {
            builder.and(quser.name.eq(user.getName()));
        }
        /* 비밀번호 암호화 (해쉬-256) 하여 비교 */
        if(!ObjectUtils.isEmpty(user.getPassword())) {
            builder.and(quser.password.eq(UserSha256.encrypt(user.getPassword())));
        }
    }
}
