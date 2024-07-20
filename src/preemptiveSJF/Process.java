package preemptiveSJF;

public class Process {
	//PROCESS INFO
	private String name;
	private int arrivalTime;
	private int brustTime;
	public int processID;
	public int priority;
    public int start_time;
    public int completion_time;
    public int turnaround_time;
    public int waiting_time;
    
    
    public Process(int processID, int burstTime,   int arrivalTime , int priority ){
        this.processID=processID;
        this.brustTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;

    }
	public Process(String name, int arrivalTime, int brustTime){
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.brustTime = brustTime;
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
}
