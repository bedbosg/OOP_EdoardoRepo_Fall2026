package example05.examples.returntypes;

interface Validator {
    boolean isValid(String string);
}

class EmailValidator implements Validator {
    @Override
    public boolean isValid(String string) {
        return string != null && string.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }
}

// Class with optional bounded type parameter
@interface Validated {
    Class<? extends Validator> validatedBy();
}

public class User {
    @Validated(validatedBy = EmailValidator.class)
    private String email;
}