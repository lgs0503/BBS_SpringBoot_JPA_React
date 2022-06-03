package lgs.bbs.menu.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lgs.bbs.menu.entity.Menu;
import lgs.bbs.menu.entity.QMenu;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class MenuRepositoryCustomImpl implements MenuRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public MenuRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Menu> searchList(Menu menu) {

        QMenu qMenu = QMenu.menu; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, menu, qMenu);

        return jpaQueryFactory
                .select(qMenu)
                .from(qMenu)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Menu menu) {

        QMenu qMenu = QMenu.menu;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, menu, qMenu);

        return  jpaQueryFactory
                .select(qMenu.count())
                .from(qMenu)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Menu menu, QMenu qMenu){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(menu.getUseYn())) {
            builder.and(qMenu.useYn.eq(menu.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(menu.getMenuName())) {
            builder.and(qMenu.menuName.eq(menu.getMenuName()));
        }
        if(!ObjectUtils.isEmpty(menu.getUpperMenuId())) {
            builder.and(qMenu.upperMenuId.eq(menu.getUpperMenuId()));
        }
        if(!ObjectUtils.isEmpty(menu.getMenuType())) {
            builder.and(qMenu.menuType.eq(menu.getMenuType()));
        }
    }
}
