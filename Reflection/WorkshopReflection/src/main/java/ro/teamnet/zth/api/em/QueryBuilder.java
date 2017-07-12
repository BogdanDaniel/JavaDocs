package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Bogdan.Barbu on 7/12/2017.
 */
public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    public String getValueForQuery(Object value ){
      if(value.getClass().equals(String.class)){
          return value.toString();
      }
      else if(value.getClass().equals(Date.class)){

          DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
          return "TO_DATE('"+dateFormat.format((Date)value)+"','mm-dd-YYYY'";
      }
      else return value.toString();
    }

    public QueryBuilder addCondition(Condition condition){


    }
}
