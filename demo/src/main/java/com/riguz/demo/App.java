package com.riguz.demo;

import com.riguz.forks.Forks;

public class App {

    public static void main(String args[]) {
        Forks forks = new Forks(new AppConfig());
        forks.start();
    }
}
