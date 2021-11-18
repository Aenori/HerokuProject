package wcsdata.xmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wcsdata.xmen.entity.CerebookUser;

import java.util.List;

public interface CerebookUserRepository extends JpaRepository<CerebookUser, Integer> {
    CerebookUser findByUsername(String username);
    CerebookUser findByName(String name);

    @Query("SELECT c1, count(c1) as cc1 " +
           "FROM CerebookUser c1 " +
           "JOIN CerebookUser c2 ON c1 IN (SELECT c2f FROM c2.friends c2f) " +
           "JOIN CerebookUser c3 ON c2 IN (SELECT c3f FROM c3.friends c3f) " +
           "WHERE c3 = :user " +
                "AND c1 <> :user " +
                "AND c1 NOT IN (SELECT c3f FROM c3.friends c3f) " +
           "GROUP BY c1 " +
           "ORDER BY cc1 DESC "

    )
    List<Object[]> findFriendsSuggestions(
            @Param("user") CerebookUser cerebookUser
    );
}

