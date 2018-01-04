package ioc;

import com.riguz.forks.ioc.old.InjectException;
import com.riguz.forks.ioc.old.Injector;
import javax.inject.Inject;
import javax.inject.Provider;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CircularDependencyTest {

    public static class ServiceA {

        private final ServiceB serviceB;

        @Inject
        public ServiceA(ServiceB serviceB) {
            this.serviceB = serviceB;
        }
    }

    public static class ServiceB {

        private final ServiceA serviceA;

        @Inject
        public ServiceB(ServiceA serviceA) {
            this.serviceA = serviceA;
        }
    }

    public static class ServiceA1 {

        private final ServiceBWithProvider serviceB;

        @Inject
        public ServiceA1(ServiceBWithProvider serviceB) {
            this.serviceB = serviceB;
        }
    }

    public static class ServiceBWithProvider {

        private final Provider<ServiceA1> serviceA;

        @Inject
        public ServiceBWithProvider(Provider<ServiceA1> serviceA) {
            this.serviceA = serviceA;
        }
    }

    @Test(expected = InjectException.class)
    public void circularDependency() {
        Injector injector = new Injector();
        injector.getInstance(ServiceA.class);
    }

    @Test
    public void circularDependencySolvedByProvider() {
        Injector injector = new Injector();
        assertNotNull(injector.getInstance(ServiceA1.class));
    }
}
