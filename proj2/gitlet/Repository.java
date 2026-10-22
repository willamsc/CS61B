package gitlet;

import java.io.File;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author wiilamsc
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");

    public static final File BRANCHES_DIR = join(GITLET_DIR,"branches");

    /* TODO: fill in the rest of this class. */
    public static void init() {
        // Check whether the .gitlet directory exist
        if (GITLET_DIR.exists()) {
            System.out.println(
                    "A Gitlet version-control system already exists in the current directory."
            );
            return;
        }

        // create the .gitlet directory
        GITLET_DIR.mkdir();
        COMMITS_DIR.mkdir();
        BRANCHES_DIR.mkdir();

        // 创建 initialCommit，并将其保存到磁盘。
        Commit initialCommit = new Commit();
        saveCommit(initialCommit);

        // 创建 master 并将其保存下来
        File masterFile = Utils.join(BRANCHES_DIR,"master");
        Utils.writeContents(masterFile,initialCommit.getId());

        // 创建 HEAD 并保存下来
        File headFile = Utils.join(GITLET_DIR,"HEAD");
        Utils.writeContents(headFile,"master");

    }

    private static void saveCommit(Commit commit) {
        // TODO
        String commitId = commit.getId();
        File commitFile = Utils.join(COMMITS_DIR, commitId);
        Utils.writeObject(commitFile,commit);
    }
}