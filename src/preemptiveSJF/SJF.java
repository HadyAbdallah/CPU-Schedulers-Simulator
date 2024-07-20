package preemptiveSJF;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SJF {
	private Process proc[];
	private int n;
	private int contextSwitchingCost;
	public SJF(Process proc[], int n, int contextSwitchingCost){
		this.contextSwitchingCost = contextSwitchingCost;
		this.n =n;
		this.proc = proc;
	}
	public ArrayList<String> ganttChart = new ArrayList<String>();
	//UTILITY FUNCTION TO FIND THE WAITING TIME FOR EACH PROCESS
	public void getWaitingTime(int waitingTime[]){
		
		//VARIABLE TO HANDLE COST OF CONTEXT SWITCHING
		int cost = contextSwitchingCost;
		
		int remainingTime[] = new int[n];
		//COPY BRUST TIMES INTO remainingTime[]
		//AS REMAINING TIME = BRUST TIME INITIALLY 
		for (int i = 0; i < n; i++) {
			remainingTime[i] = proc[i].getBrustTime();
		}
		//NUMBER OF PROCESSES COMPLETED
		int completedProcesses = 0;
		
		//STARTING TIMELAP FROM 0
		int timeLap = 0;
		
		//DEFINING THE MIN VARIABLE
		int minm = Integer.MAX_VALUE;
		
		//BOOLEAN VARIABLE TO CHECK IF THE PROCESS IS FOUND
		boolean check = false;
		
		//AN INDEX TO THE MIN REMAINING TIME
		int minIndex = 0;
		
		//COMPLETION TIME 
		int finish_time;
		
		
		//LOOP UNTILL ALL PROCESSES ARE COMPLETERD 
		while (completedProcesses != n) {
			
			//FIND THE MINIMUM REMAINING TIME 
			//FOR PROCESSES ARRIVED IN THE CURRENT TIME
			for (int j = 0; j < n; j++) {
             if ((proc[j].getArrivalTime() <= timeLap) &&
               (remainingTime[j] < minm) && remainingTime[j] > 0) {
            	 minm = remainingTime[j];
            	 minIndex = j;
                 check = true;
             }
         }
			//ADDING THE PROCESS TO THE PROCESS GANTT CHART
			ganttChart.add(proc[minIndex].getName());
			if(minIndex > 0 && minIndex < n
					&& ganttChart.get(ganttChart.size() - 1)!=ganttChart.get(ganttChart.size() -2)  ) {
				cost+= contextSwitchingCost;
			}
			
			//IF SUCH A PROCESS IS FOUND INCREMENT THE TIME LAP
			if (check == false) {
				timeLap++;
				continue;
				}
			//DECREMENT REMAINING TIME BY ONE
			remainingTime[minIndex]--;
			
			//UPDATE MINM
			minm = remainingTime[minIndex];
			if (minm == 0) {
				minm = Integer.MAX_VALUE;
			}
			
			//IF A PROCESS EXECUTED ALL ITS WORK
			if (remainingTime[minIndex] == 0) {
				
				//INCREMENT THE COMPLETED PROCESSES
	        	 completedProcesses++;
	        	 
	        	//SET CHECK VARIABLE TO INITIAL 
	             check = false;
	             
	            //SET THE COMPLETION TIME OF THE CURRNET PROCESS 
	             finish_time = timeLap + 1+ cost;
	    
	             
	            // CALCULATION OF THE WAITING TIME OF THE CURRNET PROCESS
	            //ACCORDING TO THR RULE
	            //[WAITING TIME = START TIME - ARRIVAL TIME]
	            //[WAITING TIME = COMPLETION TIME - BRUST TIME - ARRIVAL TIME]
	             waitingTime[minIndex] = finish_time -
	                          proc[minIndex].getBrustTime() -
	                          proc[minIndex].getArrivalTime();
	             //HANDLING THE POSSIBLE NEGATIVE VALUES
	             if (waitingTime[minIndex] < 0)
	            	 waitingTime[minIndex] = 0;
			}
			//AFTER EACH PROCESS INCREMENT THE TIME LAP
			timeLap++;
     }
 }
    
	
	//UTILITY FUNCTION TO FIND THE TURNAROUND TIME FOR EACH PROCESS
	//RULE
	//[TURNAROUND TIME = WAITING TIME + BRUST TIME]
	public void GetTurnAroundTime(int waitingTime[],int turnAroundTime[]){
		for (int i = 0; i < n; i++) {
			turnAroundTime[i] = proc[i].getBrustTime() + waitingTime[i];
    	 	}
     	}
 
    
	//UTILITY FUNCTION TO FIND THE AVERAGE TIME
	 public double[] findavgTime(){
		//AN ARRAY TO HOLD THE WAITING TIME
		int waitingTime[] = new int[n];
		
		//AN ARRAY TO HOLD THE TURN AROUND TIME
		int turnAroundTime[] = new int[n];
		
		//VARIABLES TO HOLD THE TOTAL WAITING TIME
		int total_wt = 0;
		
		//VARIABLES TO HOLD THE TOTAL TURNAROUND TIME
		int total_tat = 0;
    
		//CALCULATING THE WAITING TIME FOR WACH PROCESS
		getWaitingTime( waitingTime);
		
		//CALCULATING THE TURN AROUND TIME FOR WACH PROCESS
		GetTurnAroundTime(waitingTime, turnAroundTime);
		
		//CALCULATING BOTH TOTAL WAITING TIME & TURNAROUND TIME
		for (int i = 0; i < n; i++) {
			total_wt += waitingTime[i];
			total_tat += turnAroundTime[i];
     }
		 double[] avg = new double[2];
	        avg[0] = total_wt/(n+0.00);
	        avg[1] = total_tat/(n+0.00);
	        return avg;
 }
	 public void printGanttChart(SJF algorithm) {
			List<String> UniqueNames = algorithm.ganttChart.stream().distinct().collect(Collectors.toList());
			System.out.print("Gantt Chartt: ");
			System.out.print(UniqueNames);
		}
	 public void printData(Process proc[], int n, int contextSwitchingCost) {
			
			SJF algorithm = new SJF(proc, n, contextSwitchingCost);
			//AN ARRAY TO HOLD THE WAITING TIME
			int waitingTime[] = new int[n];
					
			//AN ARRAY TO HOLD THE TURN AROUND TIME
			int turnAroundTime[] = new int[n];
			//CALCULATING THE WAITING TIME FOR WACH PROCESS
			algorithm.getWaitingTime(waitingTime);
					
			//CALCULATING THE TURN AROUND TIME FOR WACH PROCESS
			algorithm.GetTurnAroundTime(waitingTime, turnAroundTime);
			 System.out.println("Processes " +
					 			" Burst time " +
					 			" Waiting time " +
	                 			" Turn around time");
			 for(int i = 0 ; i < n ; i++) {
				 System.out.println(" " + proc[i].getName() + "\t\t"
						 			+ proc[i].getBrustTime()+ "\t\t" 
						 			+ waitingTime[i]+ "\t\t" 
						 			+ turnAroundTime[i]);
			 }
			 
			//PRINTING THE AVG TIME
				double[] avg = algorithm.findavgTime();
				System.out.println("AVERAGE WAITING TIME: "+ avg[0]);
				System.out.println("AVERAGE TURNAROUND TIME: "+ avg[1]);
				System.out.println("\n");
				
				//PRINTING THE GANTT CHART
				printGanttChart(algorithm);

					
		}
}