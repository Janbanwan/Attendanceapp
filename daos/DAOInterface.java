package daos;

//import repositories.Repository;

import repositories.RepositoryGeneric;


/**
 *
 * @author mga
 */
public interface DAOInterface {

   public RepositoryGeneric load(String filename);

    public void store(String filename, RepositoryGeneric repository);
}
