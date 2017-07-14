package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Location;

/**
 * Created by Bogdan.Barbu on 7/14/2017.
 */
public class LocationDao
{
     private EntityManager entity = new EntityManagerImpl();
     public void findById(Long id){


         System.out.println(entity.findById(Location.class,id));
     }
     public void findAll(){
         System.out.println(entity.findAll(Location.class));
     }
     public void insert(Location l){
         System.out.println(entity.insert(l));

     }
     public void update(Location l2){
         System.out.println(entity.update(l2));
         findAll();

     }
    public void delete (Location l){
        entity.delete(l);
        findAll();

    }
    public void findByParams(){

    }
}
