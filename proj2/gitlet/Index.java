package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;


public class Index implements Serializable {
  /** Map of the files staged.*/
  private final Map<File, Blob> Staged;
  /** Map of the  files removed.*/
  private final Map<File,Blob> removed;

  /**Default constructor**/
  public Index(){
    staged = new HashMap<File, Blob>();
    removed = new HashMap<File, Blob>();
  }

  /** Updates and writes to the staging area. */
  public void save() {
    writeObject(INDEX, this);
  }

}
