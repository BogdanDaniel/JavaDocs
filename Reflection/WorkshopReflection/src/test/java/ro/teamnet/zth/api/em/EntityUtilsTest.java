package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Bogdan.Barbu on 7/12/2017.
 */
public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod(){
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "Department",tableName);
    }
    @Test
    public void testCastForSqlType(){
        BigDecimal a =  new BigDecimal(3);
        Integer b = new Integer(0);
        Long al = new Long(0);
        assertEquals(new Integer(0), EntityUtils.castFromSqlType(a,b.getClass()));
        assertEquals(new Long(0), EntityUtils.castFromSqlType(a,al.getClass()));

    }
}
