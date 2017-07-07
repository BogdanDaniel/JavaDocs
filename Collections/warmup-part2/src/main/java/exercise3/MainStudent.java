package exercise3;

import java.math.BigDecimal;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bogdan.Barbu on 7/7/2017.
 */
public class MainStudent {
    public static void main(String[] args){

        Map<Student2,BigDecimal> s1 = new HashMap<Student2,BigDecimal>();
        Map<Student3,BigDecimal> s2 = new HashMap<Student3,BigDecimal>();
        Map<Student4,BigDecimal> s3 = new HashMap<Student4,BigDecimal>();
        Map<Student5,BigDecimal> s4 = new HashMap<Student5,BigDecimal>();


    //Test a) Student 2
        /*
        hash code: returns the hashcode ONLY for the first name property.
        equals: checks if two objects are equal taking into account ONLY the first name property.
         */
        s1.put(new Student2("Popescu2","Ion"),new BigDecimal(10));
        s1.put(new Student2("Popescu","Ion"),new BigDecimal(20));
        s1.put(new Student2("Popescu","Ionut"),new BigDecimal(30));
        s1.put(new Student2("Popescu","Ionut2"),new BigDecimal(40));
        for(Map.Entry<Student2,BigDecimal> o:s1.entrySet()){
            System.out.println("First name: " + o.getKey().getLastName() + " " + o.getKey().getFirstName() +" " + o.getValue() + " ");

        }
        System.out.println("------------------------------------------");
       //Test b) Student 3
        /*
         hash code: returns the hashcode ONLY for the first name property.
        equals: must be correctly implemented for all the properties.
         */
        s2.put(new Student3("Popescu2","Ion"),new BigDecimal(10));
        s2.put(new Student3("Popescu","Ion"),new BigDecimal(20));
        s2.put(new Student3("Popescu","Ionut"),new BigDecimal(30));
        s2.put(new Student3("Popescu","Ionut2"),new BigDecimal(40));
        for(Map.Entry<Student3,BigDecimal> o:s2.entrySet()){
            System.out.println("First name: " + o.getKey().getLastName() + " " + o.getKey().getFirstName() +" " + o.getValue() + " ");

        }
        System.out.println("-----------------");
        //Test c) Student 4
        /*
         hash code: must be correctly implemented for all the properties.
         equals: checks if two objects are equal taking into account ONLY the first name property.
         */
        s3.put(new Student4("Popescu2","Ion"),new BigDecimal(10));
        s3.put(new Student4("Popescu","Ion"),new BigDecimal(20));
        s3.put(new Student4("Popescu2","Ion"),new BigDecimal(50));
        s3.put(new Student4("Popescu","Ionut"),new BigDecimal(30));
        s3.put(new Student4("Popescu","Ionut2"),new BigDecimal(40));
        for(Map.Entry<Student4,BigDecimal> o:s3.entrySet()){
            System.out.println("First name: " + o.getKey().getLastName() + " " + o.getKey().getFirstName() +" " + o.getValue() + " ");

        }
        //Test d) Student 5
        /*
        hash code: must be correctly implemented for all the properties.
        equals: must be correctly implemented for all the properties.
         */
        System.out.println("-------------------");
        s4.put(new Student5("Popescu2","Ion"),new BigDecimal(10));
        s4.put(new Student5("Popescu","Ion"),new BigDecimal(20));
        s4.put(new Student5("Popescu2","Ion"),new BigDecimal(50));
        s4.put(new Student5("Popescu","Ionut"),new BigDecimal(30));
        s4.put(new Student5("Popescu","Ionut2"),new BigDecimal(40));
        for(Map.Entry<Student5,BigDecimal> o:s4.entrySet()){
            System.out.println("First name: " + o.getKey().getLastName() + " " + o.getKey().getFirstName() +" " + o.getValue() + " ");

        }




    }
}
