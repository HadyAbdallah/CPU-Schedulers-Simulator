package AGScheduler;
import java.util.*;
public class Process {
	private String name;
    private int arrivalTime;
    private int actualbrustTime;
    private int brustTime;
    private int priority;
    private int actualquantum;
    private int quantum;
    private int waitingTime;
    private int turnaroundTime;
    private int completionTime;
    public static int t;
    Queue<Integer> quantumTimeHistory= new LinkedList<>();
	
	public Process(String name, int arrivalTime, int brustTime,int priority,int quantum){
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.actualbrustTime = brustTime;
                this.brustTime = brustTime;
                this.priority = priority;
                this.actualquantum = quantum;
                this.quantum = quantum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getBrustTime() {
		return brustTime;
	}
	public void setBrustTime(int brustTime) {
		this.brustTime = brustTime;
	}
        public int getActualBrustTime() {
		return actualbrustTime;
	}
	public void setActualBrustTime(int actualbrustTime) {
		this.actualbrustTime = actualbrustTime;
	}
        public int getActualquantum() {
		return actualquantum;
	}
	public void setActualquantum(int actualquantum) {
		this.actualquantum = actualquantum;
        }
        public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
        public int getQuantum() {
		return quantum;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
        public int getWaitingTime() {
		return waitingTime;
	}
	public void setwaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
        }        
        public int getTurnaroundTime(){
		return turnaroundTime;
	}
	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
        }       
        public int getCompletionTime(){
		return completionTime;
	}
	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
        }
}
