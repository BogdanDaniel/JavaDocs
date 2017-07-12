package ro.teamnet.zerotohero.reflection;

import ro.teamnet.zerotohero.reflection.demoobjects.FirstClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TODO
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public static void main(String[] args) {
        //TODO get the class for a String object, and print it
        FirstClass a = new FirstClass("abcd");
        Class cls = a.getClass();
     float i =20.2f;

        try{


            Field f = cls.getDeclaredField("abc");
            System.out.println("Private field found is : " + f.toString());
          // f = cls.getDeclaredField("en");

        //    System.out.println("Private field found is " + f.toString());
            f = cls.getDeclaredField("col");
            System.out.println("Private field found is: " + f.toString());
            f = cls.getDeclaredField("num");
            System.out.println("Private field found is " + f.toString());
            System.out.println(((Object)i).getClass().getName());
            System.out.println(a.getClass().getDeclaredField("num").getType());
            System.out.println("-----------------------");
            f = cls.getDeclaredField("i");
//           // System.out.println(a.getClass().getDeclaredField("i").getType());
//            f = FirstClass.class.getDeclaredField("i");
//            f.setAccessible(true);
//            String value = (String) f.get();


            System.out.println(a.getClass().getDeclaredField("i"));



        }catch(NoSuchFieldException e ){
            System.out.println(e.toString());
        }




        //get the class of an Enum, and print it

        // get the class of a collection, and print it
        

        // get the class of a primitive type, and print it
        

        // get and print the class for a field of primitive type
        

        //TODO get and print the class for a primitive type, using the wrapper class
        

        //TODO get the class for a specified class name
        try{
        Class c = Class.forName("java.lang.Integer");
            System.out.println("-----" + c.getName());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // get the superclass of a class, and print it
        try{
            System.out.println(a.getClass().getSuperclass());
            System.out.println(a.getClass().getSuperclass().getSuperclass());
        }catch(Exception e ){
            e.printStackTrace();
        }
        // get the superclass of the superclass above, and print it
        

        //TODO get and print the declared classes within some other class
        

        //print the number of constructors of a class
        try{
            System.out.println(a.getClass().getDeclaredConstructors().length);
            Constructor ct = a.getClass().getConstructor();
            Method m = FirstClass.class.getDeclaredMethod("FirstClass",null);
            System.out.println(m);
        }catch(Exception e ){
            e.printStackTrace();
        }

        //TODO get and invoke a public constructor of a class
        try{
                Method met = FirstClass.class.getMethod("test",int.class);
                int result = (Integer) met.invoke(new FirstClass(),100);
            System.out.println(result);
        }
                catch(Exception e){
            e.printStackTrace();
                }

        // get and print the class of one private field
        
		
		//set and print the value of one private field for an object
        

        //TODO get and print only the public fields class
        Class[] t = new Class[1];
                Method[] M = a.getClass().getMethods();
        System.out.println(M.length);


        // get and invoke one public method of a class
        

        //TODO get and invoke one inherited method of a class
       
		
		//TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
		//TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
		//what do you observe?
		
    }
}
