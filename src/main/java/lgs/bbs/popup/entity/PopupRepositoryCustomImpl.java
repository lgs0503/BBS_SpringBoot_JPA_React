package lgs.bbs.popup.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lgs.bbs.popup.entity.Popup;
import lgs.bbs.popup.entity.QPopup;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class PopupRepositoryCustomImpl implements PopupRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public PopupRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Popup> searchList(Popup popup) {

        QPopup qPopup = QPopup.popup; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, popup, qPopup);

        return jpaQueryFactory
                .select(qPopup)
                .from(qPopup)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Popup popup) {

        QPopup qPopup = QPopup.popup;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, popup, qPopup);

        return  jpaQueryFactory
                .select(qPopup.count())
                .from(qPopup)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Popup popup, QPopup qPopup){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(popup.getUseYn())) {
            builder.and(qPopup.useYn.eq(popup.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(popup.getPopupName())) {
            builder.and(qPopup.popupName.eq(popup.getPopupName()));
        }
        if(!ObjectUtils.isEmpty(popup.getPopupType())) {
            builder.and(qPopup.popupType.eq(popup.getPopupType()));
        }
    }
}
