package m.s.h.cloudserver.repository;

import m.s.h.cloudserver.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Members, Integer> {

    @Query("SELECT m FROM Members m WHERE email = :email")
    Members loadbyemail(@Param("email") String email);

}
