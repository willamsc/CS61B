package gitlet;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Stage implements Serializable {

    private HashMap<String,String> additions;
    private HashSet<String> removal;
    private static final long serialVersionUID = 1L;

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

    public boolean isEmpty() {
        return this.additions.isEmpty() && this.removal.isEmpty();
    }

    public HashMap<String, String> getAdditions() {
        // 返回 additions 的独立副本
        return  new HashMap<>(this.additions);
    }

    public HashSet<String> getRemoval() {
        // 返回 removal 的独立副本
        return new HashSet<>(this.removal);
    }

    public void stageRemoval(String fileName) {
        // 将文件名加入 removal 集合
        this.removal.add(fileName);
    }

    public boolean isStagedForAddition(String fileName) {
        // 判断 additions 是否包含这个文件名
        return this.additions.containsKey(fileName);
    }
}
