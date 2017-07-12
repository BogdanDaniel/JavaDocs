package ro.teamnet.zth.api.em;


import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.Annotation;

/**
 * Created by Bogdan.Barbu on 7/12/2017.
 */
public class EntityUtils {
    EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {

        Table annotation = (Table) entity.getClass().getAnnotation(Table.class);
        if (annotation == null)
            return entity.getSimpleName();

        return annotation.name();
    }

    public static List<ColumnInfo> getColumns(Class entity) {

        List<ColumnInfo> col = new ArrayList<ColumnInfo>();
        ColumnInfo c = new ColumnInfo();
       // Object b = new Object();
        Annotation[] cols = entity.getClass().getAnnotations();

        for (Field t : entity.getFields()) {
            if (t.getClass().getAnnotation(Id.class) != null) {
                c.setId(true);
            } else c.setId(false);
            if (t.getClass().getAnnotation(Column.class) != null) {
                try {
                    c.setColumnName(t.getName());
                    c.setColumnType(t.getType());
                    //c.setValue(t.get(b));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            col.add(c);
        }


        return col;
    }


    public static Object castFromSqlType(Object value, Class wantedType) {
        Object integ = new Integer(0);
        long l = 0;
        float f = 0.0f;
        double d=0;
        if (value instanceof BigDecimal && wantedType.equals(Integer.class)) {
            return new Integer(0);
        }
        else if (value instanceof BigDecimal && wantedType.equals(Long.class)) {
            return new Long(0);
        }
        else if (value instanceof BigDecimal && wantedType.equals(Float.class))
            return new Float(0);
        else if (value instanceof BigDecimal && wantedType.equals(Double.class))
            return new Double(0);

        else if(!(value instanceof BigDecimal)){
            return  value;
        }

        return null;
    }


    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation){
      List<Field> list = new ArrayList<Field>();
      Field[] f = clazz.getFields();
      for(Field t :f){
          if(t.getAnnotation(annotation)!=null){
              list.add(t);
          }
      }

      return list;
    }

    public static Object getSqlValue(Object object){

        if(object.getClass().getAnnotation(Table.class)!= null){
          Field[] t = object.getClass().getFields();
          for(Field f:t) {
              if (f.getClass().getAnnotation(Id.class) != null) {
                  f.setAccessible(true);
                  return f;


              }
          }
        }
        else return object;

   return null; }
}
