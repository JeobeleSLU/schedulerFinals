import java.util.LinkedList;
import java.util.Queue;

class Process {
    int pid;
    int burstTime;
    int arrivalTime;

    public Process(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class RoundRobinScheduler {

    public static void main(String[] args) {
        // Example processes
        Process p1 = new Process(1, 452, 0);
        Process p2 = new Process(2, 329, 1);
        Process p3 = new Process(3, 123, 300);
        Process p4 = new Process(3, 123, 300);


        Queue<Process> queue = new LinkedList<>();
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);

        roundRobin(queue, 3); // Quantum time set to 3
    }

    public static void roundRobin(Queue<Process> queue, int quantum) {
        int time = 1;
        int agingCounter = 0;

        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();

            // Check arrival time
            while (currentProcess.arrivalTime > time) {
                time++;
            }

            // Execute process for quantum time
            for (int i = 0; i < quantum; i++) {
                if (currentProcess.burstTime > 0) {
                    System.out.println("Executing process " + currentProcess.pid + " at time " + time);
                    currentProcess.burstTime--;
                    time++;

                    // Aging: add +1 every 3 executions of quantum time
                    agingCounter++;
                    if (agingCounter == 3) {
                        agingCounter = 0;
                        for (Process p : queue) {
                            p.arrivalTime++;
                        }
                    }
                } else {
                    break;
                }
            }

            // If process is not finished, add back to queue
            if (currentProcess.burstTime > 0) {
                queue.add(currentProcess);
            }
        }

        System.out.println("All processes completed.");
    }
}
