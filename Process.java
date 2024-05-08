public class Process {
    int pid;
    int burstTime;
    int arrivalTime;
    int remainingTime;

    public Process(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.remainingTime = burstTime;
    }
}