package com.riguz.demo.api;

import com.riguz.demo.UserDto;
import com.riguz.forks.mvc.view.JsonResult;

public interface UserApi {

    JsonResult<UserDto> getUser(int id);
}
