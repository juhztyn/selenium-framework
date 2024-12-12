package cofig;

public enum Environment {
    LOCAL("local"),
    STAGING("staging"),
    PRODUCTION("production");

    private final String name;

    Environment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
