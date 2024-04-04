package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import static gitlet.Utils.*;

/**
 * Represents a blob tracking each file in the repository.
 * @author josh Yu
 */
public class Blob implements Serializable {

  /** Name of the blob, relative path to the CWD.*/
  private final String name;
  /** Absolute path to the file of this blob.*/
  private final File path;
  /** Contents in the file of the blob. */
  private final byte[] contents;

  /** Create */
  public Blob(String name, File path, byte[] contents){
    this.name = name;
    this.path = path;
    this.contents = contents;
  }

  /** Read*/
  public Blob(String name, File path){
    this.name = name;
    this.path = path;
    if(path.exists()){
      this.contents = readContents(this.path);
    }
    else{
      this.contents = null;
    }
  }

  /*
    Delete file in blob.
   */
  public void delete(){
    if(path.exists()){
      path.delete();
    }
  }

  /*
    Overwrites the file contents.
   */
  public void overwrite() {
    try{
      path.createNewFile();
    } catch (IOException e){
      throw new RuntimeException(e);
    }
    writeContents(path, contents);
  }

  /*
    Returns whether the blob is in the specified map.
  */
  public boolean isIn(Map<File, Blob> map) {
    return map.containsKey(this.path);
  }

  /*
  Return whether the blob is modified.
   */
  public boolean isModified(Map<File, Blob> from, Map<File, Blob> to){
    byte[] fromContents = from.get(path).contents;
    byte[] toContents = to.get(path).contents;
    return !Arrays.equals(fromContents, toContents);
  }
  public String getName() {
    return name;
  }

  public File getPath() {
    return path;
  }

  public byte[] getContents() {
    return contents;
  }
}
