package entities;

public class User {

    int id;
    String lastName,
            firstName,
            email,
            password,
            typeUtilisateur,
            is_active;
    Address shipAddress;

    public User() {
    }

    public User(int id, String lastName, String firstName, String email,
            String password, Address shipAddress, String typeUtilisateur) {
        super();
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.shipAddress = shipAddress;
        this.typeUtilisateur = typeUtilisateur;
        this.is_active = is_active;
    }

    public User(String lastName, String firstName, String email, String password, String typeUtilisateur, Address shipAddress) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
        this.shipAddress = shipAddress;
        this.is_active = is_active;
    }

    public User(String firstName, String lastName, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }
    public User(String firstName, String lastName, String email, String is_active, int id) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.is_active = is_active;
        this.id = id;
    }

    public User(int id, String lastName, String firstName, String email, String password, String typeUtilisateur, String is_active, Address shipAddress) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.typeUtilisateur = typeUtilisateur;
        this.is_active = is_active;
        this.shipAddress = shipAddress;
    }
    

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Address getShipAddress() {
        return shipAddress;
    }

    public String getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShipAdress(Address adress) {
        this.shipAddress = adress;
    }

    public void setTypeUtilisateur(String typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
    
    
}
