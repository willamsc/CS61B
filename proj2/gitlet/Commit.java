package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author willamsc
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private Date timeStamp;
    private String parentId;
    private String secondParentId;
    private HashMap<String,String> blobs;


    /* TODO: fill in the rest of this class. */
    // 专门提供给 initialCommit 的构造方法。
    public Commit(){
        this.message = "initial message";
        this.parentId = null;
        this.secondParentId = null;
        this.timeStamp = new Date(0);
        this.blobs = new HashMap<>();
    }

    // 提供给普通 Commit 的构造方法。
    public Commit (
            String message,
            String parentId,
            HashMap<String,String> newBlobs
    ){
        this.message = message;
        this.parentId = parentId;
        this.blobs = new HashMap<>(newBlobs);
        this.timeStamp = new Date();
        this.secondParentId = null;

    }

    // 生成commit id 以区分每一次 commit
    public String getId(){
        return Utils.sha1(Utils.serialize(this));
    }
}
