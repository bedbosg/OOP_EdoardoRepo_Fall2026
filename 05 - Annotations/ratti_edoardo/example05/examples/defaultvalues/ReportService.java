package example05.examples.defaultvalues;

@interface Schedule {
    // mandatory — no default
    String dayOfWeek();

    // optional — defaults to 8
    int hour() default 8;

    // optional — defaults to empty array
    String[] tags() default {};
}

// dayOfWeek is mandatory, hour and tags use their defaults.
@Schedule(dayOfWeek = "Monday")
public class ReportService {
    // ...
}
