package Person;

public class Librarian extends Person{
    private int id;
    private String doj;

    public Librarian(String name, String email, String phone, String address, String dob, int id, String doj) {
        super(name, email, phone, address, dob);
        this.id = id;
        this.doj = doj;
    }

    public Librarian() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", doj='" + doj + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
