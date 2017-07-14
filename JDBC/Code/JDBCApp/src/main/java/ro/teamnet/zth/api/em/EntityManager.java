package ro.teamnet.zth.api.em;

import java.util.List;
import java.util.Map;

/**
 * Created by Bogdan.Barbu on 7/13/2017.
 */
public interface EntityManager {
    public <T> T findById(Class<T> entityClass, Long id);
    public <T> Long getNextIdVal(String tableName, String columnIdName);
    public <T> Object insert(T entity);
    public <T> List<T> findAll(Class<T> entityClass);
    public <T> T update(T entity);
    public void delete(Object entity);
    public <T> List<T> findByParamsClass(Class<T> entityClass, Map<String, Object> params);
}
