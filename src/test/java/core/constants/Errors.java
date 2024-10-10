package core.constants;

public enum Errors {
    LOGIN_FAILURE_MESSAGE("Epic sadface: Username and password do not match any user in this service");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}