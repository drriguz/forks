package com.riguz.demo;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {

    private final Integer id;
    private final String name;
    private final Date birthday;
    private final String remark;

    public UserDto(Integer id, String name, Date birthday, String remark) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getRemark() {
        return remark;
    }
}
