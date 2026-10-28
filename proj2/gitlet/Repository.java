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

    public static final File BLOBS_DIR = join(GITLET_DIR,"blobs");

    public static final File STAGE_FILE = join(GITLET_DIR,"stage");

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
        BLOBS_DIR.mkdir();

        // 创建 initialCommit，并将其保存到磁盘。
        Commit initialCommit = new Commit();
        saveCommit(initialCommit);

        // 创建 master 并将其保存下来
        File masterFile = Utils.join(BRANCHES_DIR,"master");
        Utils.writeContents(masterFile,initialCommit.getId());

        // 创建 HEAD 并保存下来
        File headFile = Utils.join(GITLET_DIR,"HEAD");
        Utils.writeContents(headFile,"master");

        //创建 stage 并保存下来
        Stage stage = new Stage();
        writeObject(STAGE_FILE,stage);
    }

    private static void saveCommit(Commit commit) {
        // TODO
        String commitId = commit.getId();
        File commitFile = Utils.join(COMMITS_DIR, commitId);
        Utils.writeObject(commitFile,commit);
    }

    public static void add(String Filename) {
        /**
         1、检查目标文件是否存在*/
        File targetFile = join(CWD,Filename);
        if (!targetFile.isFile()){
            System.out.println("File does not exist.");
            return;
        }

        /**
         *目标文件存在，通过文件内容生成对应的文件名*/
        byte[] content = Utils.readContents(targetFile);
        String blobId = Utils.sha1(content);

        /**
         * 实现保存blob文件
         * 1、先检查文件是否存在
         * 2、如果文件存在则保持原样，不存在则开始创建*/
        File blobFile = join(BLOBS_DIR,blobId);
        if (!blobFile.isFile()){
            writeContents(blobFile,content);
        }

        /**获取当前commit的blobId 和当前 add 的 blobId 做对比，如果两者相同则需要取消暂存，因为内容并没有发生改变，如果不同则需要把文件内容提交暂存区。*/
        Commit currentCommit = getCurrentCommit();
        String trackedBlobId = currentCommit.getBlobId(Filename);
        Stage stage = loadStage();
        if (blobId.equals(trackedBlobId)){
            stage.unStage(Filename);
        }else {
            stage.add(Filename,blobId);
        }
        stage.unStageRemoval(Filename);
        saveStage(stage);

    }

    private static String getCurrentBranch(){
        /**读取HEAD指向的分支名称*/
       File currentBranch = join(GITLET_DIR,"HEAD");
       return readContentsAsString(currentBranch);
    }

    /**根据当前HEAD指向的分支，获取现在的commitId*/
    private static String getCurrentCommitId(){
      String currentBranch = getCurrentBranch();
      File currentBranchPath = join(BRANCHES_DIR,currentBranch);
      String commitId = readContentsAsString(currentBranchPath);
      return commitId;
    }

    /** 获取当前commit的具体内容 */
    private static Commit getCurrentCommit(){
        String currentCommitId = getCurrentCommitId();
        File commitFile = join(COMMITS_DIR,currentCommitId);
        Commit commit = readObject(commitFile,Commit.class);
        return commit;
    }

    /**将存入磁盘的stage 文件恢复成 object*/
    private static Stage loadStage(){
        return readObject(STAGE_FILE,Stage.class);
    }

    /** 将当前的stage 存入 stage文件中*/
    private static void saveStage(Stage stage) {
        // TODO
        writeObject(STAGE_FILE,stage);
    }
}