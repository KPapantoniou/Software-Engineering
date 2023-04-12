package myy803.springboot.Diploma_Managment.model;

public enum Role {
	USER("Student"),
    ADMIN("Professor");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
