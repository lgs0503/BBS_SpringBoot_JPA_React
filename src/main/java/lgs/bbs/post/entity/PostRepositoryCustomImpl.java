package lgs.bbs.post.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Post> searchList(Post post) {

        QPost qPost = QPost.post; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, post, qPost);

        return jpaQueryFactory
                .select(qPost)
                .from(qPost)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Post Post) {

        QPost qPost = QPost.post;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, Post, qPost);

        return  jpaQueryFactory
                .select(qPost.count())
                .from(qPost)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Post Post, QPost qPost){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(Post.getUseYn())) {
            builder.and(qPost.useYn.eq(Post.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(Post.getPostName())) {
            builder.and(qPost.postName.eq(Post.getPostName()));
        }
        if(!ObjectUtils.isEmpty(Post.getCreatedUser())) {
            builder.and(qPost.createdUser.eq(Post.getCreatedUser()));
        }
        if(!ObjectUtils.isEmpty(Post.getBoardIdx())) {
            builder.and(qPost.boardIdx.eq(Post.getBoardIdx()));
        }
    }
}
