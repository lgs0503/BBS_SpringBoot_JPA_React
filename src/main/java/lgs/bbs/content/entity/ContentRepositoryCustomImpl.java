package lgs.bbs.content.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lgs.bbs.content.entity.Content;
import lgs.bbs.content.entity.QContent;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ContentRepositoryCustomImpl implements ContentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ContentRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Content> searchList(Content content) {

        QContent qContent = QContent.content; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, content, qContent);

        return jpaQueryFactory
                .select(qContent)
                .from(qContent)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Content content) {

        QContent qContent = QContent.content;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, content, qContent);

        return  jpaQueryFactory
                .select(qContent.count())
                .from(qContent)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Content content, QContent qContent){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(content.getUseYn())) {
            builder.and(qContent.useYn.eq(content.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(content.getContentName())) {
            builder.and(qContent.contentName.eq(content.getContentName()));
        }
    }
}
