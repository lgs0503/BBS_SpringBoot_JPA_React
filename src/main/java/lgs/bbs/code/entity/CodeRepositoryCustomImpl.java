package lgs.bbs.code.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CodeRepositoryCustomImpl implements CodeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public CodeRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Code> searchList(Code code) {

        QCode qCode = QCode.code; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, code, qCode);

        return jpaQueryFactory
                .select(qCode)
                .from(qCode)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Code Code) {

        QCode qCode = QCode.code;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, Code, qCode);

        return  jpaQueryFactory
                .select(qCode.count())
                .from(qCode)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Code code, QCode qCode){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(code.getUseYn())) {
            builder.and(qCode.useYn.eq(code.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(code.getCodeName())) {
            builder.and(qCode.codeName.eq(code.getCodeName()));
        }
        if(!ObjectUtils.isEmpty(code.getCodeId())) {
            builder.and(qCode.upperCodeIdx.eq(code.getCodeId()));
        }
        if(!ObjectUtils.isEmpty(code.getUpperCodeIdx())) {
            builder.and(qCode.upperCodeIdx.eq(code.getUpperCodeIdx()));
        }
    }
}
