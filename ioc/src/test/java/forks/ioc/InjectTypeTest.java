package forks.ioc;

import com.riguz.forks.ioc.InjectType;
import javax.inject.Qualifier;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class InjectTypeTest {

    @Qualifier
    public @interface CustomQualifier {

    }

    public interface Foo {

    }

    @CustomQualifier
    public static class Foo1 implements Foo {

    }

    public static class Foo2 implements Foo {

    }

    @Test
    public void qualifierEquals() {
//        InjectType<?> t1 = InjectType.of(Foo1.class);
//        InjectType<?> t2 = InjectType.of(Foo2.class);
//        InjectType<?> t3 = InjectType.of(Foo2.class);
//        assertNotEquals(t1, t2);
//        assertNotEquals(t2, t3);
    }
}
