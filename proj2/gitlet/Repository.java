package gitlet;

import java.io.File;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Josh Yu
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /** The object directory. */
     public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");

     /** The reference directory */
     public static final File REFS_DIR = join(GITLET_DIR,"refs");

     /** The log directory. */
     public static final File LOGS_DIR = join(GITLET_DIR, "logs");

     /** Files */

     /** The reference to the current file*/
    public static final File HEAD = join(GITLET_DIR, "head");

    /** The staging area */
    public static final File INDEX = join(GITLET_DIR, "index");

    /*Methods*/
    /**
     Creates a new Gitlet version-control system in the current directory.
     This system will automatically start with one commit: a commit that contains no files
     and has the commit message initial commit (just like that, with no punctuation). It will
     have a single branch: master, which initially points to this initial commit, and master will
     be the current branch. The timestamp for this initial commit will be 00:00:00 UTC, Thursday,
     1 January 1970 in whatever format you choose for dates (this is called “The (Unix) Epoch”,
     represented internally by the time 0.) Since the initial commit in all repositories created
     by Gitlet will have exactly the same content, it follows that all repositories will
     automatically share this commit (they will all have the same UID) and all commits in
     all repositories will trace back to it.
     */
    public static void initialize(){

      //Abort if there is already a Gitlet version-control system in the current directory.
      if (GITLET_DIR.exists()){
        System.out.println("A Gitlet version-control system already exists in the current directory.");
        System.exit(1);
      }

      //Initialize.
      GITLET_DIR.mkdir();
      OBJECTS_DIR.mkdir();
      REFS_DIR.mkdir();
      LOGS_DIR.mkdir();
      join(REFS_DIR, "heads").mkdir();
      join(REFS_DIR, "remotes").mkdir();
      join(LOGS_DIR, "refs").mkdir();
      join(LOGS_DIR, "refs", "heads").mkdir();

      //Creates the staging area.
      writeObject(INDEX, new Index());

      //Creates default branch.
      Branch master = new Branch("master");
      writeContents(HEAD, master.getPath().toString());

      //Creates initial commit
      Commit init = new Commit;
      init.commit();
    }


    /**
      Adds a copy of the file as it currently exists to the staging area (see the description of
      the commit command). For this reason, adding a file is also called staging the file for
      addition. Staging an already-staged file overwrites the previous entry in the staging area
      with the new contents. The staging area should be somewhere in .gitlet. If the current
      working version of the file is identical to the version in the current commit, do not stage
      it to be added, and remove it from the staging area if it is already there (as can happen
      when a file is changed, added, and then changed back to it’s original version). The file
      will no longer be staged for removal.
     */
    public static void addFile(String fileName){
      File file = join(CWD, fileName);
      //File does not exist.
      if(!file.exists()){
        System.out.println("File does not exist.");
        System.exit(1);
      }

      Index idx = getIndex();
      idx.add(fileName, file);
    }


  /** Returns the object of the current commit. */
  public static Commit getCurrentCommit(){
    return getCurrentBranch().getCommit();
  }

  /** Returns the object of the current working branch. */
  public static Branch getCurrentBranch(){
    return readObject(join(GITLET_DIR, readContentsAsString(HEAD)), Branch.class);
  }

  /** Returns the repository staging area. */
  public static Index getIndex(){
    return readObject(INDEX, Index.class);
  }
}
