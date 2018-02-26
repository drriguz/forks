package com.riguz.demo;

import com.riguz.demo.api.UserApi;
import com.riguz.forks.mvc.view.JsonResult;

import java.util.Date;

public class UserController implements UserApi {

    @Override
    public JsonResult<UserDto> getUser(String id) {
        UserDto user = new UserDto(1, "Riguz", new Date(), "Hello World!");
        JsonResult<UserDto> result = new JsonResult<>(user);
        return result;
    }
}
