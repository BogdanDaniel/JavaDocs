package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import javax.swing.text.html.parser.Entity;

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
    @Test
    public void testInsert(){
        String tableName = "DEPARTMENTS";
        Long departmentId = new Long(280);
        String departmentName = "Recruiting";
        Long location_id = new Long(1700);
        EntityManagerImpl a = new EntityManagerImpl();
        Department ins = new Department();
        ins.setDepartmentName(departmentName);
        ins.setId(departmentId);
        ins.setLocation(location_id);

        a.insert(ins);
        assertEquals(ins,a.insert(a));
    }
}
