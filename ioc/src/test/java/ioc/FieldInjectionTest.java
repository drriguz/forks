package ioc;

import com.riguz.forks.ioc.old.Injector;
import javax.inject.Inject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FieldInjectionTest {

    public static class Controller {

        @Inject
        private Service service;

    }

    public static class Service {

        @Inject
        private Repository repository;
    }

    public static class Repository {

    }

    @Test
    public void injectFields() {
        Injector injector = new Injector();
        Controller controller = new Controller();

        injector.injectFields(controller);
        injector.injectFields(controller.service);

        assertNotNull(controller);
        assertNotNull(controller.service);
        assertNotNull(controller.service.repository);
    }
}
