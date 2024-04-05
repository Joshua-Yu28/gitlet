package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import static gitlet.Utils.*;
import static gitlet.Repository.*;

/**
 * Represents a gitlet branch object.
 */
public class Branch implements Serializable {

  /** Name of this barnch.*/
  private final String name;

  /** Relative path to this branch. */
  private final File path;

  /** Absolute path to the repository (.gitlet) of this branch.*/
  private final File directory;

  /** Latest commit of this branch. */
  private Commit commit;

  /** Constructor*/
  public Branch (String name){
    this.name = name;
    this.path = join("refs","heads", this.name.replace("/","_"));
    this.directory = GITLET_DIR;
  }

  public String getName() {
    return name;
  }

  public File getPath() {
    return path;
  }

  public File getDirectory() {
    return directory;
  }

  public Commit getCommit() {
    return commit;
  }
}
