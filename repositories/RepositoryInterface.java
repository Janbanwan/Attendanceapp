package repositories;

import java.util.ArrayList;
import java.util.List;
import model.Swipe;

public interface RepositoryInterface<T extends RepositoryObject> {

    void add(T item);

    T getItem(int id);

    List<T> getItems();

    void remove(int id);

    void setItems(List<T> items);

    void store(String filename);

    String toString();
    
}
