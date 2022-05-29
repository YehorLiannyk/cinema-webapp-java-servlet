package yehor.epam.entities;

public class User extends BaseEntity {
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role userRole = Role.USER;
    private boolean notification;
    private String salt;

    public User(int id, String firstName, String secondName, String email, String password, String phoneNumber, User.Role role, boolean notification, String salt) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = role;
        this.notification = notification;
        this.salt = salt;
    }

    public User(int id, String firstName, String secondName, String email, String password, User.Role role, boolean notification,String salt) {
        this(id, firstName, secondName, email, password, null, role, notification, salt);
    }

    public User(int id, String firstName, String secondName, String email, String password, boolean notification, String salt) {
        this(id, firstName, secondName, email, password, null, Role.GUEST, notification, salt);
    }

    /**
     * Not recommended to use this constructor as might be forgotten to set the ID for user
     *
     * @param firstName   User's firstName
     * @param secondName  User's secondName
     * @param email       User's email
     * @param password    User's password
     * @param phoneNumber User's phoneNumber (not important)
     */
    public User(String firstName, String secondName, String email, String password, String phoneNumber, boolean notification, String salt) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.notification = notification;
        this.salt = salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getNotification() {
        return notification;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public enum Role {
        GUEST("GUEST"),
        USER("USER"),
        ADMIN("ADMIN");

        private final String name;

        Role(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static User.Role getUserRoleFromString(String roleValue) throws IllegalArgumentException {
            return User.Role.valueOf(roleValue.toUpperCase());
        }
    }
}
