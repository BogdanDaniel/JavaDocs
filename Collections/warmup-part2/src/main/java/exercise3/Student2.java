package exercise3;

/**
 * Created by Bogdan.Barbu on 7/7/2017.
 */
public class Student2 extends  Student {
    public Student2(String last, String first){
       this.setFirstName(first);
       this.setLastName(last);

    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Student)) return false;
        //  Exercise 2 c2) Check if Object o is null
        if((o.equals(null))) return false;

        //  Exercise 2 c3) Check if Object o class type is the same as the current instance's type
        if(getClass() != o.getClass()) return false;
        //T Exercise 2 c4) Now you know for sure that the Object o is of type Student so you
        //  need to cast it to a Student type object
        Student s = (Student) o;
        if(!this.getFirstName().equals(s.getFirstName()))
            return false;

        return true;
    }
    @Override
    public int hashCode(){
        int result = this.getFirstName().hashCode();

        return result;
    }
}
