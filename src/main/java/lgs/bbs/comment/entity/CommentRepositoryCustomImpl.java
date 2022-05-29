package lgs.bbs.comment.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public CommentRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Comment> searchList(Comment comment) {

        QComment qComment = QComment.comment; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, comment, qComment);

        return jpaQueryFactory
                .select(qComment)
                .from(qComment)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Comment comment) {

        QComment qComment = QComment.comment;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, comment, qComment);

        return  jpaQueryFactory
                .select(qComment.count())
                .from(qComment)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Comment Comment, QComment qComment){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(Comment.getUseYn())) {
            builder.and(qComment.useYn.eq(Comment.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(Comment.getCommentName())) {
            builder.and(qComment.commentName.eq(Comment.getCommentName()));
        }
        if(!ObjectUtils.isEmpty(Comment.getCreatedUser())) {
            builder.and(qComment.createdUser.eq(Comment.getCreatedUser()));
        }
        if(!ObjectUtils.isEmpty(Comment.getPostIdx())) {
            builder.and(qComment.postIdx.eq(Comment.getPostIdx()));
        }
    }
}
