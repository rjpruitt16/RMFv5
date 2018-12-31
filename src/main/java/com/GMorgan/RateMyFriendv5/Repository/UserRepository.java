package com.GMorgan.RateMyFriendv5.Repository;

import com.GMorgan.RateMyFriendv5.Entitiy.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
}
