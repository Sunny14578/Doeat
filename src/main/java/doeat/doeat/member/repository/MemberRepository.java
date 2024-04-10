package doeat.doeat.member.repository;

import doeat.doeat.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Boolean existsByName(String name);
}
