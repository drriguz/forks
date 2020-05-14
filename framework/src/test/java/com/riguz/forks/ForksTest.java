package com.riguz.forks;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class ForksTest {
    Forks forks = new Forks(new DefaultConfig());

    @Before
    public void init() {
        forks.start();
    }

    @After
    public void stop() {
        forks.stop();
    }

    @Test
    @Ignore
    public void getString() {
        when().
                get("/foo").
                then().
                statusCode(200).
                body(equalTo("bar"));
    }

    @Test
    public void getInt() {
        when().
                get("/number").
                then().
                statusCode(200).
                body(equalTo("1024"));
    }
}
