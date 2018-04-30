package forks.ioc;

import com.riguz.forks.ioc.Bind;
import com.riguz.forks.ioc.InjectException;
import com.riguz.forks.ioc.Injector;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Ignore;
import org.junit.Test;

public class ExceptionTest {

    public static class NoInjectConstructor {

        public NoInjectConstructor(String str) {

        }
    }

    public static class MultipleInjectConstructor {

        @Inject
        public MultipleInjectConstructor(String str) {

        }

        @Inject
        public MultipleInjectConstructor(String str, int a) {

        }
    }

    public static class InjectConstructorNotValid {

        @Inject
        public InjectConstructorNotValid(InjectConstructorNotValid circle) {
        }

    }

    public static class AmbiguousProvidesConfig {

        @Bind
        public String str1() {
            return "foo";
        }

        @Bind
        public String str2() {
            return "bar";
        }
    }

    public static class AmbiguousNamedProvidesConfig {

        @Bind
        @Named("foo")
        public String str1() {
            return "foo";
        }

        @Bind
        @Named("foo")
        public String str2() {
            return "bar";
        }
    }

    public static class NullPointerConfig {
        @Bind
        public String str() {
            return null;
        }
    }

    @Test(expected = InjectException.class)
    public void noInjectConstructor() {
        Injector injector = new Injector();
        injector.getInstance(NoInjectConstructor.class);
    }

    @Test(expected = InjectException.class)
    public void multipleInjectConstructor() {
        Injector injector = new Injector();
        injector.getInstance(MultipleInjectConstructor.class);
    }

    @Test(expected = InjectException.class)
    public void injectConstructorNotValid() {
        Injector injector = new Injector();
        injector.getInstance(InjectConstructorNotValid.class);
    }

    @Test(expected = InjectException.class)
    public void ambiguousConfig() {
        new Injector(new AmbiguousProvidesConfig());
    }

    @Test(expected = InjectException.class)
    public void ambiguousNamedConfig() {
        new Injector(new AmbiguousNamedProvidesConfig());
    }

    @Test(expected = InjectException.class)
    @Ignore
    public void nullConfig() {
        // TODO: exception when config method returns null
        new Injector(new NullPointerConfig());
    }
}
