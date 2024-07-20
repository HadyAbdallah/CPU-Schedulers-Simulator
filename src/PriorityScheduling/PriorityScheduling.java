package PriorityScheduling;
import static java.lang.Math.max;
import static java.lang.Math.min;
import preemptiveSJF.Process;
public class PriorityScheduling {

    public void Priority(Process pb[],int num){

        int br[] = new int[num];
        float avg_turnaround_time =0;
        float avg_waiting_time = 0;
        int total_turnaround_time = 0;
        int total_waiting_time = 0;
        int total_idle_time = 0;
        int[] burst_remaining = new int[1000];
        int[] is_completed = new int[1000];
        int current_time = 0;
        int completed = 0;
        int prev = 0;

        for (int i = 0; i < num; i++) {
            br[i]=pb[i].getBrustTime();
            burst_remaining[i] = br[i];
        }

        while (completed != num) {
            int idx = -1;
            int mx = -1;
            for (int i = 0; i < num; i++) {
                if (pb[i].getArrivalTime() <= current_time && is_completed[i] == 0) {
                    if (pb[i].priority > mx) {
                        mx = pb[i].priority;
                        idx = i;
                    }
                    if (pb[i].priority == mx) {
                        if (pb[i].getArrivalTime() < pb[idx].getArrivalTime()) {
                            mx = pb[i].priority;
                            idx = i;
                        }
                    }
                }
            }
            if (idx != -1) {
                if (burst_remaining[idx] == pb[idx].getBrustTime()) {
                    pb[idx].start_time = current_time;
                    total_idle_time += pb[idx].start_time - prev;
                }
                burst_remaining[idx] -= 1;
                current_time++;
                prev = current_time;

                if (burst_remaining[idx] == 0) {
                    pb[idx].completion_time = current_time;
                    pb[idx].turnaround_time = pb[idx].completion_time - pb[idx].getArrivalTime();
                    pb[idx].waiting_time = pb[idx].turnaround_time - pb[idx].getBrustTime();
                    total_turnaround_time += pb[idx].turnaround_time;
                    total_waiting_time += pb[idx].waiting_time;
                    if(pb[idx].waiting_time < 0){pb[idx].waiting_time =0;}

                    is_completed[idx] = 1;
                    completed++;
                    System.out.print("p"+ pb[idx].processID + " ");


                }

            } else {
                current_time++;
            }

            int min_arrival_time = 10000000;
            int max_completion_time = -1;
            for (int i = 0; i < num; i++) {
                min_arrival_time = min(min_arrival_time, pb[i].getArrivalTime());
                max_completion_time = max(max_completion_time, pb[i].completion_time);
            }

            avg_turnaround_time = (float) total_turnaround_time / num;
            avg_waiting_time = (float) total_waiting_time / num;



        }


        System.out.println("\n");
        System.out.println("Process Name"+"\t"+"\t"+"Turnaround Time"+"\t"+"\t"+"Waiting Time"+"\t"+"\t"+"Priority");
        for(int i=0;i<num;i++)
        {
            System.out.println(pb[i].processID+"\t\t"+"\t"+"\t"+pb[i].turnaround_time+"\t\t"+"\t"+pb[i].waiting_time+"\t\t "+pb[i].priority);
        }
        System.out.println("Average Waiting Time: "+(float)avg_waiting_time);
        System.out.println("Average Turnaround Time: "+(float)avg_turnaround_time);
    }











}
