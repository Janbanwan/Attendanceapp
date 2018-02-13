/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import daos.DAOObjectImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author janne
 */
public class RepositoryGeneric<T extends RepositoryObject> implements RepositoryInterface<T>, Serializable {
    private List<T> items;    

    
    public RepositoryGeneric() {
        this.items = new ArrayList<>();
        //this.items = new LinkedList<>();        
    }
    
    public RepositoryGeneric(List<T> items) {        
        this.items = items;
    }
    
    public RepositoryGeneric(String filename) {
        this();
        DAOObjectImpl dao = new DAOObjectImpl();
        this.items = dao.load(filename).getItems();
    }
    
    @Override
    public List<T> getItems() {        
        return this.items;
    }
    
    @Override
    public void setItems(List<T> items) {        
        this.items = items;
    }
    
    @Override
    public void add(T item) {
        this.items.add(item);
    }
       
    @Override
    public void remove(int id) {
        Predicate<T> predicate = e->e.getId() == id;       
        this.items.removeIf(predicate);
    }
    
    @Override
    public T getItem(int id) {
        for (T item:this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "\nEmployees: " + this.items;
    }
    
    @Override
    public void store(String filename) {       
        DAOObjectImpl dao = new DAOObjectImpl();
        dao.store(filename, this);        
    }        
}