package Person;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class StudentManager {
    ObjectOutputStream oos_student = null;
    ObjectInputStream ois_student = null;
    File studentFile;
    ArrayList<Student> studentArrayList;

    @SuppressWarnings("unchecked")
    public StudentManager(){
        //create a representation of students.dat
        studentFile = new File("Students.dat");
        //initialize the studentArrayList
        studentArrayList = new ArrayList<>();
        //if studentFile exists add them to the studentArrayList
        if(studentFile.exists()){
            try {
                // Create an ObjectInputStream to read objects from the file "Students.dat"
                ois_student = new ObjectInputStream(new FileInputStream(studentFile));
                // Read and deserialize(convert byte stream into object) an ArrayList of Student objects from the ObjectInputStream
                studentArrayList= (ArrayList<Student>) ois_student.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void addStudent(Student student){
        studentArrayList.add(student);
    }
    public Student getStudent(int roll){
        for(Student student: studentArrayList){
            if(student.getRoll() == roll){
                return student;
            }
        }
        return  null;
    }

    public void viewAllStudents(){
        for(Student student: studentArrayList){
            System.out.println(student);
        }
    }
    public boolean updateStudent(int updateRoll, String name, String email, String phone, String address, String dob, int semester){
        ListIterator<Student> studentListIterator = (ListIterator<Student>) studentArrayList.listIterator();
        while(studentListIterator.hasNext()){
            Student student = studentListIterator.next();
            if(student.getRoll() == updateRoll){
                student.setName(name);
                student.setEmail(email);
                student.setPhone(phone);
                student.setAddress(address);
                student.setDob(dob);
                student.setSemester(semester);
                return true;
            }
        }
        return false;
    }
    public boolean deleteStudent(int delete_roll){
        ListIterator<Student> studentListIterator = (ListIterator<Student>) studentArrayList.listIterator();
        while(studentListIterator.hasNext()){
            Student student = studentListIterator.next();
            if(student.getRoll() == delete_roll){
                studentListIterator.remove();
                return true;
            }
        }
        return  false;
    }

public void writeToFile(){
    try{
        // Create a new ObjectOutputStream to write objects to the studentFile
        oos_student = new ObjectOutputStream(new FileOutputStream(studentFile));

        // Write the studentArrayList object to the output stream
        oos_student.writeObject(studentArrayList);
    }catch (IOException ioException){
        ioException.printStackTrace();
    }
}

}