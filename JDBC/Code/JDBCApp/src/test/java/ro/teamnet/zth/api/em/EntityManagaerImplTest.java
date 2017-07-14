package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import javax.swing.text.html.parser.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static ro.teamnet.zth.api.em.EntityManagerImpl.*;

/**
 * Created by Bogdan.Barbu on 7/13/2017.
 */
public class EntityManagaerImplTest {
    @Test
    public void testFindById(){
        EntityManagerImpl entity = new EntityManagerImpl();
        Department d = entity.findById(Department.class, 260L);
        assertEquals("Recruiting",d.getDepartmentName());

    }
    @Test
    public void testGetNextIdVal(){
        String tableName = "EMPLOYEES";
        String columnIdName = "EMPLOYEE_ID";
        EntityManagerImpl a = new EntityManagerImpl();
        Long max = a.getNextIdVal(tableName,columnIdName);
        assertEquals(new Long(207),max);
    }
//    @Test  Gresala Lu Serdin IORDAN FLORINEL !!!!!!!!! CNP 1950
//    public void testInsert(){
//        String tableName = "DEPARTMENTS";
//        Long departmentId = new Long(360);
//        String departmentName = "Recq3";
//        Long location_id = new Long(1700);
//        EntityManagerImpl a = new EntityManagerImpl();
//        Department ins = new Department();
//        ins.setDepartmentName(departmentName);
//      //  ins.setId(departmentId);
//        ins.setLocation(location_id);
//
//a.insert(ins);
////        assertEquals(ins,a.insert(ins));
//    }
    @Test
    public void testUpdate(){
        String tableName = "DEPARTMENTS";
        Long departmentId = new Long(20);
        String departmentName = "Recruiting";
        Long location_id = new Long(1900);
        EntityManagerImpl a = new EntityManagerImpl();
        Department upd = new Department();
        upd.setDepartmentName(departmentName);
        upd.setId(departmentId);
        //System.out.println(upd.getDepartmentName());
        upd.setLocation(location_id);
        Department nou = a.update(upd);
        assertEquals(nou,upd);
    }

    @Test public void testDelete(){
        String tableName = "LOCATIONS";
        Long locationId = new Long(1000);
        String postalCode = "1297 Via Cola di Rie";
       // Long location_id = new Long(1900);
        EntityManagerImpl a = new EntityManagerImpl();
       Location del = new Location();
        del.setId(locationId);
        //System.out.println(upd.getDepartmentName());

        a.delete(del);
       // assertEquals(nou,upd);

    }
    @Test public void testFindByParams(){
        EntityManagerImpl entity = new EntityManagerImpl();
        Map<String,Object> map = new TreeMap<String,Object>();
        map.put("location_id",1700);
       // ArrayList<T>  a = entity.findByParams(Department.class,map );
        System.out.println(entity.findByParams(Department.class,map ).get(0).getDepartmentName());
        System.out.println(entity.findByParams(Department.class,map ).get(1).getDepartmentName());
        System.out.println(entity.findByParams(Department.class,map ).get(2).getDepartmentName());

    }
    @Test
    public void testFindAll(){
        EntityManagerImpl a = new EntityManagerImpl();
        List<Department> l = a.findAll(Department.class);
        System.out.println(l.get(0).getDepartmentName());
    }
}
