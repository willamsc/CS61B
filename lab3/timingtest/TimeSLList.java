package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCount = new AList<>();

        int [] testN = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        for(int n :testN){
            SLList<Integer> test = new SLList<>();
            int i = 0;
            while(i<n){
                test.addLast(1);
                i +=1;
            }
            Stopwatch sw = new Stopwatch();
            int j =0;
            while(j<10000){
                test.getLast();
                j +=1;
            }
            double time = sw.elapsedTime();
            Ns.addLast(n);
            times.addLast(time);
            opCount.addLast(10000);
        }
        printTimingTable(Ns,times,opCount);
    }

}
