package com.riguz.demo;

import com.riguz.forks.Forks;
import com.riguz.forks.ioc.Injector;

public class App {

    public static void main(String args[]) {
        final AppConfig config = new AppConfig();
        final Injector injector = new Injector(config);
        Forks forks = new Forks(injector);
        forks.start();
    }
}
