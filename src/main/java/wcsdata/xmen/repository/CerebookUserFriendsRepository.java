package wcsdata.xmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wcsdata.xmen.entity.CerebookUserFriends;
import wcsdata.xmen.entity.ids.CerebookUserFriendsId;

public interface CerebookUserFriendsRepository extends JpaRepository<CerebookUserFriends, CerebookUserFriendsId> {
}
