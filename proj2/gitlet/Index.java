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

  /** Clear the staging area */
  public void clear(){
    staged.clear();
    removed.clear();
    save();
  }

  /** Check if the file is modified*/
  public void isModified(File file){
    Blob b;
    if(isStaged(file)){
      b = staged.get(file);
    }else{
      b = getCurrentCommit().getBlob(file);
    }
    if (b = null){
      return true;
    }
    byte[] oldFile = b.getContents();
    byte[] newFile = readContents(file);
    return !Arrays.equals(oldFile, newFile);
  }

  public boolean isStaged(File file){
    return staged.containsKey(file);
  }
  /** Adds the specified file into staged area.*/
  public void add(String fileName, File file){
    if(isModified(file)){

    }

  }




  public Map<File, Blob> getStaged() {
    return staged;
  }
}
