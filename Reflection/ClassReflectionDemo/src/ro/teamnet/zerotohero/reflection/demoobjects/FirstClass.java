package ro.teamnet.zerotohero.reflection.demoobjects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Bogdan.Barbu on 7/12/2017.
 */
public class FirstClass extends SecondClass {
    private int num = 20;
    private String abc;

    private enum en {Luni {}, Marti {}, Miercuri {}, Joi {}}

    ;
    private Collection<String> col = new ArrayList<String>();
    Integer i = Integer.valueOf(num);

    public FirstClass(String str1) {
        this.abc = str1;

    }

    public FirstClass() {
    }

    public int test(int nr) {
        return nr + 1;
    }
}
