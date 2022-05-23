package yehor.epam.entities;

public class User extends BaseEntity {
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String phoneNumber;
    //private UserRole role;
    private Role userRole;
    private boolean notification;

    public User(int id, String firstName, String secondName, String email, String password, String phoneNumber, User.Role role, boolean notification) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = role;
        this.notification = notification;
    }

    public User(int id, String firstName, String secondName, String email, String password, User.Role role, boolean notification) {
        this(id, firstName, secondName, email, password, null, role, notification);
    }

    public User(int id, String firstName, String secondName, String email, String password, boolean notification) {
        this(id, firstName, secondName, email, password, null, Role.GUEST, notification);
    }
    /*public User(int id, String firstName, String secondName, String email, String password, String phoneNumber, UserRole role, boolean notification) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.notification = notification;
    }

    public User(int id, String firstName, String secondName, String email, String password, boolean notification) {
        this(id, firstName, secondName, email, password, null, null, notification);
    }*/

    /**
     * Not recommended to use this constructor as might be forgotten to set the ID for user
     *
     * @param firstName   User's firstName
     * @param secondName  User's secondName
     * @param email       User's email
     * @param password    User's password
     * @param phoneNumber User's phoneNumber (not important)
     */
    public User(String firstName, String secondName, String email, String password, String phoneNumber, boolean notification) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.notification = notification;
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

   /* public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }*/

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

    public enum Role {
        USER,
        ADMIN,
        GUEST;

        public static User.Role getUserRoleFromString(String roleValue) throws IllegalArgumentException {
            return User.Role.valueOf(roleValue.toUpperCase());
        }
    }
}
