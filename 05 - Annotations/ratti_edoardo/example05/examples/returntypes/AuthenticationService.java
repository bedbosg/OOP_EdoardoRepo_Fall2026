package example05.examples.returntypes;

// String and primitivy types
@interface Metrics {
    boolean enabled() default true;

    int sampleRate() default 100;

    String label() default "";
}

@Metrics(sampleRate = 10, label = "login")
public class AuthenticationService {
    // ...
}
