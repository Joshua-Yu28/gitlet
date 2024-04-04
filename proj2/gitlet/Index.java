package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.Repository.*;


public class Index implements Serializable {
  /** Map of the files staged.*/
  private final Map<File, Blob> staged;
  /** Map of the  files removed.*/
  private final Map<File, Blob> removed;

  /**Default constructor**/
  public Index(){
    staged = new HashMap<File, Blob>();
    removed = new HashMap<File, Blob>();
  }

  /** Updates and writes to the staging area. */
  public void save() {
    writeObject(INDEX, this);
  }

  public void clear(){
    staged.clear();
    removed.clear();
    save();
  }

  /** Adds the specified file into staged area.*/
  public Map<File, Blob> getStaged() {
    return Staged;
  }
}
