package AGScheduler;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Scanner sin=new Scanner(System.in);
        System.out.print("Enter the Number of process : ");
	int numOfPro=in.nextInt();
        Process[] processes= new Process[numOfPro];	
        //TAKING THE INFO OF EACH PROCESS FROM THE USER
        for(int i = 0; i < numOfPro; i++) {

                System.out.println("Enter the name of process no." + (i+1));
                String name = sin.next();
                System.out.println("Enter the Arrival time of process no." + (i+1));
                int arrivalTime = in.nextInt();
                System.out.println("Enter the Brust time of process no." + (i+1));
                int brustTime = in.nextInt();
                System.out.println("Enter the priority of process no." + (i+1));
                int priority = in.nextInt();
                System.out.println("Enter the quantum of process no." + (i+1));
                int quantum = in.nextInt();

                Process process = new Process(name, arrivalTime,brustTime,priority,quantum);
                processes[i]=process;
        } 

        for(int i=0;i<numOfPro;i++)
        {
            
            for(int j=i+1;j<numOfPro;j++)
            {
            	
                String tname;
                int tarrivalTime,tbrustTime,tpriority,tquantum;
                if(processes[j].getArrivalTime()<processes[i].getArrivalTime())
                {
                    tname=processes[i].getName();
                    tarrivalTime=processes[i].getArrivalTime();
                    tbrustTime=processes[i].getBrustTime();
                    tpriority=processes[i].getPriority();
                    tquantum=processes[i].getQuantum();
                    processes[i].setName(processes[j].getName());
                    processes[i].setArrivalTime(processes[j].getArrivalTime());
                    processes[i].setBrustTime(processes[j].getBrustTime());
                    processes[i].setPriority(processes[j].getPriority());
                    processes[i].setQuantum(processes[j].getQuantum());
                    processes[j].setName(tname);
                    processes[j].setArrivalTime(tarrivalTime);
                    processes[j].setBrustTime(tbrustTime);
                    processes[j].setPriority(tpriority);
                    processes[j].setQuantum(tquantum);
                    System.out.println(i);
                }
            }
        }

        ag Ag = new ag(processes,numOfPro);
        Ag.agRun();
	}


}
