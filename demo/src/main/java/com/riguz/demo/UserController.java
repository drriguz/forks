package com.riguz.demo;

import com.riguz.forks.mvc.RequestContext;
import com.riguz.forks.mvc.view.JsonResult;

import java.util.Date;

public class UserController {

    public JsonResult<UserDto> getUser(RequestContext context) {
        UserDto user = new UserDto(1, "Riguz", new Date(), "Hello World!");
        JsonResult<UserDto> result = new JsonResult<>(user);
        return result;
    }
}
