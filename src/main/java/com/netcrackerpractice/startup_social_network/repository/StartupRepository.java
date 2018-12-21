package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Long> {
    Optional<Startup> findById(UUID uuid);

    void deleteById(UUID uuid);

    List<Startup> findStartupsByAccount_Id(UUID account);

    @Query(value = "SELECT s FROM Startup s JOIN s.startupResumes sr" +
            " JOIN sr.resume r " +
            " JOIN r.account " +
            "a WHERE a.id = ?1 and sr.accepted = true and LOWER(s.startupName) LIKE %?2% ")
    List<Startup> searchStartupsAsDeveloper(UUID accountId, String nameContains, Sort sort);

    @Query(value = "SELECT s FROM Startup s " +
            "JOIN s.account a " +
            "JOIN a.user u " +
            "WHERE LOWER(s.startupName) LIKE %?1% and u.login = ?2")
    List<Startup> searchStartupAsLeader (String startupNameContains, String creator, Sort sort);

   @Query(value = "SELECT s FROM Startup s " +
           "JOIN s.account a " +
           "JOIN a.user u " +
           "WHERE LOWER(s.startupName) LIKE %?1% and  LOWER(u.login) LIKE %?2%")
   List<Startup> searchAllStartups(String startupNameContains, String loginContains, Sort sort);
}
