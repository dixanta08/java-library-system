package Person;

import java.io.Serializable;
import java.util.regex.Pattern;

//abstract because person class will not be instantiated anywhere in the entire system but will be inherited
abstract public class Person implements Serializable {
    protected String name;
    protected String email;
    protected String phone;
    protected String address;
    protected String dob;

    public Person(String name, String email, String phone, String address, String dob) {
        //calling respective setter method with the regular expressions
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.address = address;
        this.setDob(dob);
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        boolean isValidName = Pattern.matches("[a-zA-Z]+",name);//using regex
        if(isValidName) {
            this.name = name;
        }else{
            this.name = "Default Name";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        boolean isValidEmail = Pattern.matches("^[a-z][a-zA-Z0-9._]*[a-z0-9]+@[a-z]+[\\.][a-z]{2,3}$",email);
        if(isValidEmail){
            this.email = email;
        }else{
            this.email = "default@email.com";
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        boolean isValidPhone = Pattern.matches("\\d{10}",phone);
        if(isValidPhone){
            this.phone = phone;
        }else{
            this.phone = "9812345678";
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        boolean isValidDob = Pattern.matches("\\d{2}-\\d{2}-\\d{4}",dob);
        if(isValidDob){
            this.dob = dob;
        }else{
            this.dob = "01-06-2005";
        }
    }
}
