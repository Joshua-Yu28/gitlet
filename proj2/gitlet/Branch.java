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

  /** Name of this branch.*/
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
    try{
      //Checks if a branch with the given name already exists.
      if(!getRefFile().createNewFile()|| !getLogFile().createNewFile()){
        exit("A branch with that name already exists.");
      }
    }catch(IOException e){
      throw new RuntimeException(e);
    }
    save();
  }

  public void save(){
    writeObject(this.getRefFile(),this);
  }

  public void remove(){
    if(getRefFile().exists()){
      getRefFile().delete();
    }
    if(getLogFile().exists()){
      getLogFile().delete();
    }
  }

  /** Synchronizes the branch with the specified branch history. */
  public void sync(Branch branch){
    this.commit = branch.commit;
    writeContents(this.getLogFile(), readContents(branch.getLogFile()));
    save();
  }

  /** Updates the branch with the latest commit and saves it. */
  public void setCommit(Commit commit){
    this.commit = commit;
    save();
  }

  /** Resets the branch with the specified commit.*/
  public void resetCommit(Commit newCommit){
    getCurrentCommit().deleteTrackedFiles((newCommit));
    newCommit.overwriteTrackedFiles();
    setCommit(newCommit);
    //Regenerates the branch log file.
    StringBuilder log = new StringBuilder();
    Commit c = newCommit;
  while (c != null){
    log.append(c.toString()).append("\n");
    c = c.getParent();
  }
  writeContents(getLogFile(), log.toString());
  //Clears the staging area.*/
  readObject(join(directory,"index"), Index.class).clear();
  }

  /**Returns all the commits in tne branch history as a set.*/
  public Set<Commit> getAllCommits(){
    Set<Commit> commits = new HashSet<Commit>();
    Commit c = commit;
    while (c.getParent() != null){
      commits.add(c);
      c = c.getParent();
    }
    return commits;
  }
  /** Finds all the existing branches and returns them as a set. */
  public static Set<Branch> findAll(){
    File dir = join(REFS_DIR, "heads");
    List<String> branchNames = plainFilenamesIn(dir);
    Set<Branch> branches = new HashSet<Branch>();
    if(branchNames != null){
      for(String name : branchNames){
        branches.add(readObject(join(dir,name), Branch.class));
      }
    }
    return branches;
  }

  /** Returns the Branch object of the specified branch name. */
  public static Branch find(String branchName, int key) {
    Branch branch = null;
    for (Branch b : findAll()) {
      if (branchName.equals(b.name)) {
        branch = b;
        break;
      }
    }
    if (branch == null && key == 1) {
      exit("No such branch exists.");
    } else if (branch == null && key == 2) {
      exit("A branch with that name does not exist.");
    }
    return branch;
  }

  public String getName() {
    return name;
  }

  public File getPath() {
    return path;
  }

  public Commit getCommit() {
    return commit;
  }

  /** Return the absolute path to the refs. */
  public File getRefFile() {
    return join(directory, String.valueOf(path));
  }

  /** Return the absolute path to the logs. */
  public File getLogFile() {
    return join(join(directory, "logs"), String.valueOf(this.path));
  }
}
