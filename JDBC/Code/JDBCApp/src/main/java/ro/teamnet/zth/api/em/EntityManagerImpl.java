package ro.teamnet.zth.api.em;

import javafx.beans.binding.ObjectExpression;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 * Created by Bogdan.Barbu on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {

    public <T> T findById(Class <T> entityClass,Long id){

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> list = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass,Id.class);
        //Creare conditie
        Condition condition = new Condition();

            if(fields.size() >1)
                throw new ArrayIndexOutOfBoundsException();
        else condition.setColumnName(list.get(0).getDbColumnName());

        condition.setValue(id);


        QueryBuilder queryBuilder = new QueryBuilder();
        //adaugare tableName la query
        queryBuilder.setTableName(tableName);
        //adaugare type la query
        queryBuilder.setQueryType(QueryType.SELECT);
        //adaugare coloane la query
        queryBuilder.addQueryColumns(list);
        //adaugare conditie la query
        queryBuilder.addCondition(condition);
        String query = queryBuilder.createQuery();
       //System.out.println(query);
        Statement stmt = null;
        ResultSet rs = null;


        try{
            Connection conn = DBManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()){
                T instance =entityClass.newInstance() ;
                for(ColumnInfo ci: list){
                   Field f =  instance.getClass().getDeclaredField(ci.getColumnName());
                    f.setAccessible(true);
//                    System.out.println(ci.getDbColumnName() + " " + ci.getColumnName() + " " + ci.getValue() + " " + ci.getColumnType());
//                    System.out.println(f);
//                    System.out.println(instance.getClass().getDeclaredField(ci.getColumnName()));


                    Object d = rs.getObject(ci.getDbColumnName());

                    f.set(instance,EntityUtils.castFromSqlType(d, f.getType()));

                }
                return instance;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;

    }
    @Override
    public Long getNextIdVal(String tableName, String columnIdName){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Long max_value = new Long(0);
        try {
            Connection conn = DBManager.getConnection();
            stmt = conn.prepareStatement("select max("+columnIdName+ ") from " +tableName);
            //System.out.println(columnIdName + " " + tableName);
            rs = stmt.executeQuery();
            while(rs.next()){

                max_value  = rs.getLong(1) + 1;

            }

            return max_value;


        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if(stmt != null){
                //close(stmt);
            }
        }
        return null;
    }
    @Override
    public<T> Object insert(T entity) {
       // System.out.println( EntityUtils.getColumns(entity.getClass()));
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> list = EntityUtils.getColumns(entity.getClass());
        Statement stmt = null;
        ResultSet rs = null;



        try

        {
            Connection conn = DBManager.getConnection();
            stmt = conn.createStatement();
            System.out.println(list.get(0).getDbColumnName());
            System.out.println(getNextIdVal(tableName,list.get(0).getDbColumnName()));
//           list.get(0).setValue(getNextIdVal(tableName,list.get(0).getColumnName()));
            for(ColumnInfo ci: list){
                Field f = entity.getClass().getDeclaredField(ci.getColumnName());
                f.setAccessible(true);

                ci.setValue(f.get(entity));


            }

//            Condition condition = new Condition();
//            condition.setColumnName(list.get(0).getDbColumnName());
//            condition.setValue(getNextIdVal(tableName,list.get(0).getDbColumnName()));
//            System.out.println(list.get(0).getDbColumnName());
//            System.out.println(getNextIdVal(tableName,list.get(0).getDbColumnName()));




            QueryBuilder queryBuilder = new QueryBuilder();
            //adaugare tableName la query
            queryBuilder.setTableName(tableName);
            //adaugare type la query
            queryBuilder.setQueryType(QueryType.INSERT);
            //adaugare coloane la query
            queryBuilder.addQueryColumns(list);
            //adaugare conditie la query
          //  queryBuilder.addCondition(condition);
            String query = queryBuilder.createQuery();
            System.out.println(query);
            rs = stmt.executeQuery(query);
            //Return the update object :
            ;
            conn.commit();


            return findById((Class<T>)entity.getClass(), (Long)list.get(0).getValue());

        }catch(Exception ex){
            ex.printStackTrace();

        }

        return null;
    }
    public <T> List<T> findAll(Class<T> entityClass){
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> list = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass,Id.class);

        try {
            Connection conn = DBManager.getConnection();
            Field f = null;
            Statement stmt = conn.createStatement();
            ResultSet rs = null;
            ArrayList<T> array = new ArrayList<T>();

            QueryBuilder queryBuilder = new QueryBuilder();
            //adaugare tableName la query
            queryBuilder.setTableName(tableName);
            //adaugare type la query
            queryBuilder.setQueryType(QueryType.SELECT);
            //adaugare coloane la query
            queryBuilder.addQueryColumns(list);
            //adaugare conditie la query

            String query = queryBuilder.createQuery();
            rs = stmt.executeQuery(query);


            while (rs.next()){
                T instance =entityClass.newInstance();
                for(ColumnInfo ci:list){
                    f =  instance.getClass().getDeclaredField(ci.getColumnName());
                    f.setAccessible(true);
                    Object b = rs.getObject(ci.getDbColumnName());
                    f.set(instance,EntityUtils.castFromSqlType(b,f.getType()));

                }
                array.add(instance);
            }
            return array;

                }catch(Exception ex){
            ex.printStackTrace();
        }

   return null; }
  @Override
    public <T> T update (T entity){
       String tableName = EntityUtils.getTableName(entity.getClass());
       List<ColumnInfo> list = EntityUtils.getColumns(entity.getClass());
       Statement stmt = null;
       ResultSet rs = null;



       try

    {
        Connection conn = DBManager.getConnection();
        stmt = conn.createStatement();
        for(ColumnInfo ci: list){
            Field f = entity.getClass().getDeclaredField(ci.getColumnName());
            f.setAccessible(true);

            ci.setValue(f.get(entity));


        }
        Condition condition = new Condition();
        condition.setColumnName(list.get(0).getDbColumnName());
        condition.setValue(list.get(0).getValue());



        QueryBuilder queryBuilder = new QueryBuilder();
        //adaugare tableName la query
        queryBuilder.setTableName(tableName);
        //adaugare type la query
        queryBuilder.setQueryType(QueryType.UPDATE);
        //adaugare coloane la query
        queryBuilder.addQueryColumns(list);
        //adaugare conditie la query
        queryBuilder.addCondition(condition);
        String query = queryBuilder.createQuery();
        System.out.println(query);
        rs = stmt.executeQuery(query);
        //Return the update object :
      ;
      conn.commit();


        return findById((Class<T>)entity.getClass(), (Long)list.get(0).getValue());

    }catch(Exception ex){
       ex.printStackTrace();

   }
   return null;

}
@Override
    public void delete(Object entity){
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> list = EntityUtils.getColumns(entity.getClass());
        Statement stmt = null;
        ResultSet rs = null;



        try

        {
            Connection conn = DBManager.getConnection();
            stmt = conn.createStatement();
            for(ColumnInfo ci: list){
                Field f = entity.getClass().getDeclaredField(ci.getColumnName());
                f.setAccessible(true);

                ci.setValue(f.get(entity));


            }
            Condition condition = new Condition();
            condition.setColumnName(list.get(0).getDbColumnName());
            condition.setValue(list.get(0).getValue());



            QueryBuilder queryBuilder = new QueryBuilder();
            //adaugare tableName la query
            queryBuilder.setTableName(tableName);
            //adaugare type la query
            queryBuilder.setQueryType(QueryType.DELETE);
            //adaugare coloane la query
            queryBuilder.addQueryColumns(list);
            //adaugare conditie la query
            queryBuilder.addCondition(condition);
            String query = queryBuilder.createQuery();
            System.out.println(query);
            rs = stmt.executeQuery(query);
            //Return the update object :
            ;
            conn.commit();




        }catch(Exception ex){
            ex.printStackTrace();

        }



    }
    public <T> List<T>  findByParams(Class<T> entityClass, Map<String,Object> params){
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> list = EntityUtils.getColumns(entityClass);
        ArrayList<T> array = new ArrayList<T>();
        Statement stmt = null;
        ResultSet rs = null;



        try

        {
            Connection conn = DBManager.getConnection();
            stmt = conn.createStatement();
//            for(ColumnInfo ci: list){
//                Field f = entityClass.getDeclaredField(ci.getColumnName());
//                f.setAccessible(true);
//
//                ci.setValue(f.get(entityClass));
//
//
//            }
          Condition condition = new Condition();
            QueryBuilder queryBuilder = new QueryBuilder();

                    for(Map.Entry<String, Object> en: params.entrySet()){
                        condition.setColumnName(en.getKey());
                        condition.setValue(en.getValue());
                        queryBuilder.addCondition(condition);


                    }






            //adaugare tableName la query
            queryBuilder.setTableName(tableName);
            //adaugare type la query
            queryBuilder.setQueryType(QueryType.SELECT);
            //adaugare coloane la query
            queryBuilder.addQueryColumns(list);
            //adaugare conditie la query

            String query = queryBuilder.createQuery();
           // System.out.println(query);
            rs = stmt.executeQuery(query);

            while(rs.next()){
                T instance =entityClass.newInstance() ;
                for(ColumnInfo ci: list){
                    Field f =  instance.getClass().getDeclaredField(ci.getColumnName());
                    f.setAccessible(true);

                    Object d = rs.getObject(ci.getDbColumnName());

                    f.set(instance,EntityUtils.castFromSqlType(d, f.getType()));

                }
                array.add(instance);
            }


        return array;

        }catch(Exception ex){
            ex.printStackTrace();

        }
        return null;
    }
}
