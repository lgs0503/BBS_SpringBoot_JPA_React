package lgs.bbs.banner.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class BannerRepositoryCustomImpl implements BannerRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public BannerRepositoryCustomImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Banner> searchList(Banner banner) {

        QBanner qBanner = QBanner.banner; // 추가

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, banner, qBanner);

        return jpaQueryFactory
                .select(qBanner)
                .from(qBanner)
                .where(builder)
                .fetch();
    }

    @Override
    public Long searchListCount(Banner banner) {

        QBanner qBanner = QBanner.banner;

        BooleanBuilder builder = new BooleanBuilder();
        builderCondition(builder, banner, qBanner);

        return  jpaQueryFactory
                .select(qBanner.count())
                .from(qBanner)
                .where(builder)
                .fetch()
                .get(0);//fetchCount() 대신 사용 - QueryDSL 패치되서 안씀
    }

    private void builderCondition(BooleanBuilder builder, Banner banner, QBanner qBanner){
        /* 조건 조회 */
        if(!ObjectUtils.isEmpty(banner.getUseYn())) {
            builder.and(qBanner.useYn.eq(banner.getUseYn()));
        }
        if(!ObjectUtils.isEmpty(banner.getBannerName())) {
            builder.and(qBanner.bannerName.eq(banner.getBannerName()));
        }
        if(!ObjectUtils.isEmpty(banner.getBannerType())) {
            builder.and(qBanner.bannerType.eq(banner.getBannerType()));
        }
    }
}
