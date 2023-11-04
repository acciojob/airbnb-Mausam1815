package com.driver.repository;

import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {
//    User u1 = new User(12345, "Mausam Raj", 22);
    private final HashMap<Integer, User> userDB = new HashMap<>();

    public int addUser(User user) {
        userDB.put(user.getaadharCardNo(), user);
        return user.getaadharCardNo();
    }
}
