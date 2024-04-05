package gitlet;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static gitlet.Utils.*;
import static gitlet.Repository.*;
import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Josh Yu
 */
public class Commit implements Serializable{
    /**
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    private final Map<File, Blob> blobs = new HashMap<File, Blob>();
    private final String message;
    private final Date date;
    private final Commit parent;
    private final Commit mergeParent;
    private String UID;

    public Commit (){
        this.message = "initial commit";
        this.date = new Date(0);
        this.parent = null;
        this.mergeParent = null;
    }

    public Commit(String message){
        this.message = message;
        this.date = new Date();
        this.parent = getCurrentCommit();
        this.mergeParent = null;
        this.blobs.putAll(parent.blobs);
    }

    /** Constructor of a merged commit. */
    public Commit(Branch current, Branch other){
        this.message = String.format("Merged %s into %s.", other.getName(), current.getName());
        this.date = new Date();
        this.parent = current.getCommit();
        this.mergeParent = other.getCommit();
        this.blobs.putAll(getIndex().getStaged());
    }

    /** Creates and writes to the Commit object. */
    public void save(){
        try{
            if(!getPathFolder().exists()){
                getPathFolder().mkdir();
            }
            getPathFile().createNewFile();
            writeObject(getPathFile(), this);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

    }

    public String getMessage(){
        return this.message;
    }

    public Map<File, Blob> getBlobs() {
        return blobs;
    }

    public Date getDate() {
        return date;
    }

    public Commit getMergeParent() {
        return mergeParent;
    }

    public String getUID() {
        return UID;
    }

    public Commit getParent() {
        return parent;
    }

    /** Returns the path to the objs subfolder. */
    public File getPathFolder() {
        return join(OBJECTS_DIR, this.UID.substring(0, 2));
    }

    /** Returns the path to the file holding the commit object. */
    public File getPathFile() {
        return join(getPathFolder(), this.UID.substring(2));
    }


}
