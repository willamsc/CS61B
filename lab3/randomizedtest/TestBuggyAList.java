package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }

    @Test
    public void RandomComparison() {
        // L 是正确无误的参考答案 (Oracle)
        AListNoResizing<Integer> L = new AListNoResizing<>();
        // B 是我们要测试的可能有 Bug 的实现
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                // addLast：两个列表都要添加相同的随机数
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1) {
                // size：获取两者的 size 并使用 assertEquals 对比
                int sizeL = L.size();
                int sizeB = B.size();
                System.out.println("size: " + sizeL);
                assertEquals(sizeL, sizeB); // 【关键对比】

            } else if (operationNumber == 2) {
                // removeLast：安全锁保护下，两者都删除并对比返回的结果
                if (L.size() > 0) {
                    int removeL = L.removeLast();
                    int removeB = B.removeLast();
                    System.out.println("removeLast(" + removeL + ")");
                    assertEquals(removeL, removeB); // 【关键对比】
                }

            } else if (operationNumber == 3) {
                // getLast：安全锁保护下，两者都获取末尾元素并对比
                if (L.size() > 0) {
                    int lastL = L.getLast();
                    int lastB = B.getLast();
                    System.out.println("getLast(" + lastL + ")");
                    assertEquals(lastL, lastB); // 【关键对比】
                }
            }
        }
    }
}

