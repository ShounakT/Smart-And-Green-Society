package Authentication;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private String email;
    private String designation;

    private int phone;
    private String societyId;
    Map<String,Object> userMap = new HashMap<>();

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public Map toMapSecretary(String name,String email,String designation,int phone,String societyId){

        userMap.put("name",name);
        userMap.put("email",email);
        userMap.put("designation",designation);
        userMap.put("phone",phone);
        userMap.put("societyId",societyId);

        return userMap;
    }
}
