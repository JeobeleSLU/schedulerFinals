import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class SRTFScheduler {

    public static void main(String[] args) {
        // Example processes
        Process p1 = new Process(1, 300, 0);
        Process p2 = new Process(2, 90, 20);
        Process p3 = new Process(3, 30, 2);

        List<Process> processes = new ArrayList<>();
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);

        srtf(processes);
    }

    public static void srtf(List<Process> processes) {
        int currentTime = 1;
        int completed = 0;
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.remainingTime));

        while (completed < processes.size()) {
            // Add arriving processes to the queue
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && !queue.contains(process) && process.remainingTime > 0) {
                    queue.add(process);
                }
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();
                System.out.println("Executing process " + currentProcess.pid + " at time " + currentTime);
                int executionTime = Math.min(currentProcess.remainingTime, 1); // Process for at most 1 unit of time
                currentTime += executionTime;
                currentProcess.remainingTime -= executionTime;

                if (currentProcess.remainingTime == 0) {
                    completed++;
                }
            } else {
                currentTime++;
            }

            for (Process process : queue) {
                if (process != null && process != queue.peek()) {
                    process.remainingTime--;
                }
            }
        }

        System.out.println("All processes completed.");
    }
}