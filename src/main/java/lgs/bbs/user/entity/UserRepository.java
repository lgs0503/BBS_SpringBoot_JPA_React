package lgs.bbs.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, UserRepositoryCustom {

//    @Query( value =
//            "SELECT COUNT(*) " +
//            "FROM   USER_INFO " +
//            "WHERE  ID          = :id " +
//            "AND    PASSWORD    = :password",
//            nativeQuery = true)
//    long loginProcessing(String id, String password);
//
//    @Query( value =
//            "SELECT COUNT(*) " +
//            "FROM   USER_INFO " +
//            "WHERE  ID          = :id ",
//            nativeQuery = true)
//    long userOverlapChk(String id);
}
