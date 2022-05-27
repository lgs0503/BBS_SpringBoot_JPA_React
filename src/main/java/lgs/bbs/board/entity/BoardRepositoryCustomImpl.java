package lgs.bbs.board.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> searchList(Board Board) {

        QBoard qBoard = QBoard.board; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, Board, qBoard);

        return jpaQueryFactory
                .select(qBoard)
                .from(qBoard)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Board Board) {

        QBoard qBoard = QBoard.board;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, Board, qBoard);

        return  jpaQueryFactory
                .select(qBoard.count())
                .from(qBoard)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Board Board, QBoard qBoard){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(Board.getUseYn())) {
            builder.and(qBoard.useYn.eq(Board.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(Board.getBoardName())) {
            builder.and(qBoard.boardName.eq(Board.getBoardName()));
        }
        if(!ObjectUtils.isEmpty(Board.getBoardType())) {
            builder.and(qBoard.boardType.eq(Board.getBoardType()));
        }
    }
}
