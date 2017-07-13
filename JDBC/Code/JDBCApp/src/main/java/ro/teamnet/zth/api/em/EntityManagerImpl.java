package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan.Barbu on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {
    @Override
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
       System.out.println(query);
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
            System.out.println(columnIdName + " " + tableName);
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
    public<T> Object insert(T entity) {
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> list = EntityUtils.getColumns(entity.getClass());
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entity.getClass(),Id.class);
        Long lastValue = new Long(0);
        try {
            Connection conn = DBManager.getConnection();
            Field f = null;
            Statement stmt = null;
            ResultSet rs = null;

            for(ColumnInfo ci : list){
                if(ci.isId()){
                    lastValue = getNextIdVal(tableName,list.get(0).getDbColumnName());

                     f =  entity.getClass().getDeclaredField(ci.getColumnName());

                }
                else {
                    f = entity.getClass().getDeclaredField(ci.getColumnName());
                }
                f.setAccessible(true);
                //f.set(entity.getClass().getDeclaredField(ci.getColumnName()),lastValue );
                //Object d = rs.getObject(list.get(0).getDbColumnName());
                Object d = ci.getDbColumnName();
               // f.set(entity.getClass().getDeclaredField(ci.getColumnName()), ci.getColumnName());
                f.set(entity.getClass().getDeclaredField(ci.getDbColumnName()), EntityUtils.castFromSqlType(d, f.getType()));


            }
            Condition condition = new Condition();
            if(fields.size() >1)
                throw new ArrayIndexOutOfBoundsException();
            else condition.setColumnName(fields.get(0).getName());

            QueryBuilder queryBuilder = new QueryBuilder();
            //adaugare tableName la query
            queryBuilder.setTableName(tableName);
            //adaugare type la query
            queryBuilder.setQueryType(QueryType.INSERT);
            //adaugare coloane la query
            queryBuilder.addQueryColumns(list);
            //adaugare conditie la query
            queryBuilder.addCondition(condition);
            String query = queryBuilder.createQuery();
            rs = stmt.executeQuery(query);
            return findById(entity.getClass(),lastValue);



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
            Statement stmt = null;
            ResultSet rs = null;
            ArrayList<T> array = new ArrayList<T>();
            Condition condition = new Condition();
            if(fields.size() >1)
                throw new ArrayIndexOutOfBoundsException();
            else condition.setColumnName(fields.get(0).getName());

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
            rs = stmt.executeQuery(query);
            T instance =entityClass.newInstance();

            while (rs.next()){
                for(ColumnInfo ci:list){
                    f =  instance.getClass().getDeclaredField(ci.getColumnName());
                    f.setAccessible(true);
                    f.set(instance,rs.getObject(ci.getDbColumnName()));
                    array.add(instance);
                }
            }
            return array;

                }catch(Exception ex){
            ex.printStackTrace();
        }

   return null; }
}
