/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import helpers.InputHelper;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import repositories.RepositoryGeneric;

/**
 *
 * @author janne
 */
public class DAOObjectImpl implements DAOInterface{
    InputHelper ih = new InputHelper();

    public RepositoryGeneric load(String filename) {
        
        RepositoryGeneric repository = new RepositoryGeneric();
        
        try{
            FileInputStream fin = new FileInputStream(filename);
            try (ObjectInputStream ois = new ObjectInputStream(fin)) {
                while(true){
                repository = (RepositoryGeneric) ois.readObject();           
                }      
            }
            catch (EOFException eof) {
            
            }
        } catch(IOException | ClassNotFoundException ex){
            System.out.println("file not found try again");
            return this.load(ih.readString("Enter filename Again"));
        }        

        return repository;
    }

    public void store(String filename, RepositoryGeneric repository) {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(filename));       
            output.writeObject(repository);
            output.close();
        } catch (IOException ex) {
            
        } finally {
            try {
                output.close();
            } catch (IOException ex) { }
        }      
    }

}

