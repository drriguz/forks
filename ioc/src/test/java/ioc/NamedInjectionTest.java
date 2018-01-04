package ioc;

import com.riguz.forks.ioc.old.Injector;
import com.riguz.forks.ioc.old.Bind;
import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NamedInjectionTest {

    public interface Service {

    }

    public static class FooService implements Service {

    }

    public static class BarService implements Service {

    }

    public static class NamedConfig {

        @Bind
        @Named("foo")
        public Service fooService() {
            return new FooService();
        }

        @Bind
        @Named("bar")
        public Service barService() {
            return new BarService();
        }
    }

    public static class Controller {

        @Inject
        @Named("foo")
        private Service fooService;

        @Inject
        @Named("bar")
        private Service barService;

    }

    @Test
    public void namedInjection() {
        Injector injector = new Injector(new NamedConfig());
        Controller c1 = new Controller();
        injector.injectFields(c1);
        assertNotNull(c1.fooService);
        assertNotNull(c1.barService);
    }
}
