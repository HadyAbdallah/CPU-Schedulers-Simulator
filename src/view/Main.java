package view;

import java.util.Scanner;

import PriorityScheduling.PriorityScheduling;
import RoundRobiin.RoundRoben;
import preemptiveSJF.Process;
import preemptiveSJF.SJF;

public class Main {
	public static void main(String[] args) {
		//INPUT OBJECT
				Scanner input = new Scanner(System.in);
					//TAKING THE NUMBER OF PROCESSES FROM THE USER 
					System.out.println("Enter the Number of processes: ");
					int n = input.nextInt();
						
					//ARRAY THAT HOLD PROCCESS
					Process[] processes= new Process[n];
					
					//TAKING THE ID FROM THE USER
				    int ID[] = new int[n];
				    
				  //TAKING THE PRIORITY FROM THE USER
				    int proi[] = new int[n];
				    
					//TAKING THE INFO OF EACH PROCESS FROM THE USER
					for(int i = 0; i < n; i++) {
						
						System.out.println("Enter the name of process no." + (i+1));
						String name = input.next();
				        System.out.println("Enter the ID of process no." + (i+1));
				        ID[i] = input.nextInt();
						System.out.println("Enter the Arrival time of process no." + (i+1));
						int arrivalTime = input.nextInt();
						System.out.println("Enter the Brust time of process no." + (i+1));
						int brustTime = input.nextInt();
						System.out.println("Enter the priority of process no." + (i+1));
						proi[i] = input.nextInt();
						
						Process process = new Process(name, arrivalTime,brustTime);
						processes[i] =process;
					}

					//TAKING THE CONTEXT SWITCHING COST FROM THE USER
					System.out.println("Enter the context switching cost: ");
					int contextSwitchingCost =input.nextInt();
					
					//TAKING THE QUANTUM TIME FROM THE USER
					System.out.println("Enter the Number of Round robin Time Quantum");
				    int quant=input.nextInt();

				    
				  //FILLING THE BR[] WITH BRUST TIME OF EACH PROCESS
				    int br[]= new int[n];
				    for(int i = 0 ; i < n ; i++) {
				    	br[i]=processes[i].getBrustTime();
				    }
				    //FILLING THE AR[] WITH ARRIVAL TIME OF EACH PROCESS
				    int ar[]= new int[n];
				    for(int i = 0 ; i < n ; i++) {
				    	ar[i]=processes[i].getArrivalTime();
				    }
				    //FILLING THE PRO[] WITH THE NAME OF EACH PROCESS
				    String pro[]=new String [n];
				    for(int i = 0 ; i < n ; i++) {
				    	pro[i]=processes[i].getName();
				    }
				    
				    System.out.println();
				    //CHOOSING THE CHOOSED ALGORITHM
				    System.out.println("Enter your choosed option: ");
				    System.out.println("1) SJF ALGORITHM");
				    System.out.println("2) ROUND ROBIN ALGORITHM");
				    System.out.println("3) PRIORITY SCHEDULING ALGORITHM");
				    System.out.println();
				    int option = input.nextInt();
				    
				    if(option ==1) {
				    				    
						//FIRST POINT
					    
					    
						System.out.println("SJF ALGORITHM");

						SJF obj = new SJF(processes, n, contextSwitchingCost);
						obj.printData(processes, n, contextSwitchingCost);
				    }

				    else if (option==2) {
				    	//SECOND POINT 
						
						
						System.out.println("ROUND ROBIN ALGORITHM");
						insertionSort(ar,br,pro);
					    RoundRoben r=new RoundRoben(n,quant,contextSwitchingCost,pro,br,ar);
					    r.runOfRR();
				    }
				    
				    	//THIRD TIME
				    else if (option==3) {
				        
				    	System.out.println("PRIORITY SCHEDULING ALGORITHM");
				        int Starv =0;
				      //to sort Process by Arrival Time
				        int tempID, tempBt, temp, tempPR;
				        for (int i = 1; i < n; i++) {
				            for (int j = i; j > 0; j--) {
				                if (ar[j] < ar[j - 1]) {
				                    temp = ar[j];
				                    tempID = ID[j];
				                    tempBt = br[j];
				                    tempPR = proi[j];
				                    ar[j] = ar[j - 1];
				                    ID[j] = ID[j - 1];
				                    br[j] = br[j - 1];
				                    proi[j] = proi[j - 1];
				                    ar[j - 1] = temp;
				                    ID[j - 1] = tempID;
				                    br[j - 1] = tempBt;
				                    proi[j - 1] = tempPR;
				                }
				            }
				        }
				      //to insert process
				        for (int x = 0; x < n; x++) {
				        	processes[x].processID = ID[x];
				        	processes[x].priority = proi[x];
				        }
				        PriorityScheduling pS = new PriorityScheduling();
				        pS.Priority(processes,n);
				        System.out.println("Enter Starvation Number : ");
				        Starv = input.nextInt();
				        Process P_starvation[]=new Process[n];
				        for (int i = 1; i < n; i++){
				            proi[i]-=Starv;
				        }
				        for (int i = 0; i <n; i++){
				            P_starvation[i] = new Process(ID[i], br[i], ar[i], proi[i]);
				        }
				        pS.Priority(P_starvation,n);
				    }
	}
	
	//UTILITY FUNCTION OF INSERTION SORT
	public static void insertionSort(int array[],int array2[],String array3[]) {  
        int n = array.length;  
        for (int j = 1; j < n; j++) {  
            int key = array[j]; 
            int key2 = array2[j]; 
            String key3 = array3[j]; 
            int i = j-1;  
            while ( (i > -1) && ( array [i] > key ) ) {  
                array [i+1] = array [i];  
                array2 [i+1] = array2 [i];
                array3 [i+1] = array3 [i];
                i--;  
            }  
            array[i+1] = key; 
            array2[i+1] = key2;
            array3[i+1] = key3;
        }  
    }  
	
}
