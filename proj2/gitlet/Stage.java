package gitlet;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Stage implements Serializable {

    private HashMap<String,String> additions;
    private HashSet<String> removal;

    public Stage(){
        this.additions = new HashMap<>();
        this.removal = new HashSet<>();
    }

    public void add(String fileName, String blobId) {

        // 使用 HashMap 的方法将文件加入 additions
        this.additions.put(fileName,blobId);

    }

    public void unStage(String fileName) {
        // TODO: 从 additions 中删除指定文件的记录
        this.additions.remove(fileName);
    }

    public void unStageRemoval(String fileName) {
        // 从 removal 集合中取消指定文件的待删除标记
        this.removal.remove(fileName);
    }
}
