package gitlet;

import java.io.File;
import java.io.Serializable;

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

  public Blob
}
