package gitlet;

// TODO: any imports you need here

import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Josh Yu
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timestamp;
    //Something that keeps track of what files this commit is tracking
    private Commit parent;

    public Commit(String message, Commit parent){
        this.message = message;
        this.parent = parent;
        if(this.parent == null){
            this.timestamp = "00:00:00 UTC, Thursday, 1 January 1970";
        }
    }

    public String getMessage(){
        return this.message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Commit getParent() {
        return parent;
    }
    /* TODO: fill in the rest of this class. */
}
