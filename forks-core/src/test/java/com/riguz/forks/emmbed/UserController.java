package com.riguz.forks.emmbed;

import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class UserController {
    public String foo() {
        return "bar";
    }

    public int number() {
        return 1024;
    }

    public UserDto getUser(int id) {
        UserDto user = new UserDto(1, "Riguz", new Date(), "Hello World!");
        return user;
    }
}
