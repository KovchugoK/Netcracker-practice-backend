package com.netcrackerpractice.startup_social_network.model.repository;

import com.netcrackerpractice.startup_social_network.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
