package lgs.bbs.comm.entity;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FileSpecification {

    public static Specification<FileInfo> equalIdx(Long idx) {
        return new Specification<FileInfo>() {
            @Override
            public Predicate toPredicate(Root<FileInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("idx"), idx);
            }
        };
    }

}
