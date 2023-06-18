package Person;

import java.io.Serializable;

public class Student extends Person  {
    private int roll;
    private int semester;

    public Student(){
        super();
    }
    public Student(String name, String email, String phone, String address, String dob, int roll, int semester) {
        super(name, email, phone, address, dob);
        this.roll = roll;
        this.semester = semester;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Student{" +
                "roll=" + roll +
                ", semester=" + semester +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
