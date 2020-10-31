package Authentication;

public class User {

    private String name;
    private String email;
    private String designation;
    private Boolean isSecretary;
    private int phone;
    private int societyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getSecretary() {
        return isSecretary;
    }

    public void setSecretary(Boolean secretary) {
        isSecretary = secretary;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }
}
