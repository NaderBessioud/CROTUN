package tn.esprit.CROTUN.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.CROTUN.Entities.UserActivity;

@Repository
public interface ActivityRepository extends JpaRepository<UserActivity, Integer> {
	List<UserActivity> findByUserAndIp(String user,String ip);

}
