package forks.ioc.test;

import com.riguz.forks.ioc.Injector;
import com.riguz.forks.ioc.Provides;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class SingletonTest {

    public static class Service {

    }

    public static class NestedService {

        SingletonService singletonService;

        @Inject
        public NestedService(SingletonService singletonService) {
            this.singletonService = singletonService;
        }
    }

    @Singleton
    public static class SingletonService {

    }

    public static class Config {

        int calledCount = 0;

        @Provides
        public Service service() {
            calledCount += 1;
            return new Service();
        }
    }

    public static class SingletonConfig {

        int calledCount = 0;

        @Provides
        @Singleton
        public Service service() {
            calledCount += 1;
            return new Service();
        }
    }

    @Test
    public void noneSingleton() {
        Injector injector = new Injector();

        assertNotNull(injector.getInstance(Service.class));
        assertNotEquals(injector.getInstance(Service.class), injector.getInstance(Service.class));

        Provider<Service> provider = injector.getProvider(Service.class);
        assertNotNull(provider.get());
        assertNotEquals(provider.get(), provider.get());
    }

    @Test
    public void noneSingletonWithConfig() {
        Config config = new Config();
        Injector injector = new Injector(config);

        assertNotNull(injector.getInstance(Service.class));
        assertNotEquals(injector.getInstance(Service.class), injector.getInstance(Service.class));
        assertEquals(3, config.calledCount);

        Provider<Service> provider = injector.getProvider(Service.class);
        assertNotNull(provider.get());
        assertNotEquals(provider.get(), provider.get());
    }

    @Test
    public void singleton() {
        Injector injector = new Injector();

        assertNotNull(injector.getInstance(SingletonService.class));
        assertEquals(injector.getInstance(SingletonService.class), injector.getInstance(SingletonService.class));

        Provider<SingletonService> provider = injector.getProvider(SingletonService.class);
        assertNotNull(provider.get());
        assertNotEquals(provider.get(), injector.getInstance(Service.class));
    }

    @Test
    public void singletonByConfig() {
        SingletonConfig config = new SingletonConfig();
        Injector injector = new Injector(config);

        assertNotNull(injector.getInstance(Service.class));
        assertEquals(injector.getInstance(Service.class), injector.getInstance(Service.class));
        assertEquals(1, config.calledCount);

        Provider<SingletonService> provider = injector.getProvider(SingletonService.class);
        assertNotNull(provider.get());
        assertNotEquals(provider.get(), injector.getInstance(Service.class));
        assertEquals(1, config.calledCount);
    }

    @Test
    public void nestedService() {
        Injector injector = new Injector();
        
        NestedService s1 = injector.getInstance(NestedService.class);
        NestedService s2 = injector.getInstance(NestedService.class);

        assertNotEquals(s1, s2);
        assertNotNull(s1.singletonService);
        assertEquals(s1.singletonService, s2.singletonService);

        assertEquals(s1.singletonService, injector.getInstance(SingletonService.class));
    }
}
