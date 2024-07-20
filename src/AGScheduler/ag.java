package AGScheduler;


import java.util.*;
public class ag {
	int num=0;
    Queue<Process> r= new LinkedList<>();
    Queue<Process> c= new LinkedList<>();
    Queue<Process> n= new LinkedList<>();
    Queue<Process> n2= new LinkedList<>();
    Queue<Process> finished = new LinkedList<>();
    Queue<String> executionOrder= new LinkedList<>();
    
    public ag(Process[] processes,int number)
    {
        for(int i=0;i<number;i++){
            r.add(processes[i]);
        }
        this.num=number; 
    }
    public int quantum25(Process p){
        return (int)Math.ceil((double)p.getQuantum()/4);
    }
    public Queue<Process> istherenewprocess (Queue<Process> ri,int ti){
        Queue<Process> ni= new LinkedList<>();
        for(Process i:ri)
        {
            if(i.getArrivalTime()<ti){
                ni.add(i);
            }
        }
        return ni;
    }
    public Process hashighestpiriorty(Process p,Queue<Process> pi){
        Process ret=p;
        for(Process i:pi)
        {
            if(i.getPriority()<ret.getPriority()){
                ret=i;
            }
        }
        return ret;
    }
    public Process smallestbrusttime(Process sp,Queue<Process> si){
        Process ret=sp;
        for(Process i:si)
        {
            if(i.getBrustTime()<ret.getBrustTime()){
                ret=i;
            }
        }
        return ret;
    }
    
    public void agRun(){
        int done=num;
        boolean start=true;
        while(done!=0){
            if(c.isEmpty())
                c.add(r.poll());
            executionOrder.add(c.peek().getName());
            int q25 = quantum25(c.peek());
            c.peek().quantumTimeHistory.add((c.peek().getQuantum())-q25);
            if(start==true){
                Process.t=c.peek().getArrivalTime()+q25;
                start=false;
            }
            else
                Process.t += q25;
            n=istherenewprocess(r,Process.t);
            if(n.isEmpty()==false){
                Process ph = hashighestpiriorty(c.peek(),n);
                if(ph==c.peek()){
                    int q50 = (quantum25(c.peek()))*2;
                    c.peek().setQuantum(c.peek().getQuantum()-q50);
                    c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                    c.peek().setBrustTime(c.peek().getBrustTime()-q50);
                    Process.t+=q25;
                    if(c.peek().getBrustTime()==0){
                        c.peek().setQuantum(0);
                        c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                        c.peek().setCompletionTime(Process.t);
                        c.peek().setwaitingTime(c.peek().getCompletionTime()-c.peek().getActualBrustTime()-c.peek().getArrivalTime());
                        c.peek().setTurnaroundTime(c.peek().getWaitingTime()+c.peek().getActualBrustTime());
                        finished.add(c.poll());
                        done--;
                    }
                    n2=istherenewprocess(r,Process.t);  
                    if(n2.isEmpty()==false){
                        Process sb = smallestbrusttime(c.peek(),n2);
                        if(sb==c.peek()){
                            c.peek().setBrustTime(c.peek().getBrustTime()-c.peek().getQuantum());
                            Process.t+=c.peek().getQuantum();
                            if(c.peek().getBrustTime()==0){
                                c.peek().setQuantum(0);
                                c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                                c.peek().setCompletionTime(Process.t);
                                c.peek().setwaitingTime(c.peek().getCompletionTime()-c.peek().getActualBrustTime()-c.peek().getArrivalTime());
                                c.peek().setTurnaroundTime(c.peek().getWaitingTime()+c.peek().getActualBrustTime());
                                finished.add(c.poll());
                                done--;
                            }
                            else{
                                c.peek().setQuantum(c.peek().getQuantum()+2);
                                c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                                r.add(c.poll());
                                c.add(sb);
                                executionOrder.add(sb.getName());
                            }
                        }
                        else{
                            c.peek().setQuantum(c.peek().getActualquantum()+c.peek().getQuantum());
                            c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                            r.add(c.poll());
                            c.add(sb);
                            executionOrder.add(sb.getName());
                        }
                    }
                    else{
                        c.peek().setBrustTime(c.peek().getBrustTime()-c.peek().getQuantum());
                        Process.t+=c.peek().getQuantum();
                        if(c.peek().getBrustTime()==0){
                            c.peek().setQuantum(0);
                            c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                            c.peek().setCompletionTime(Process.t);
                            c.peek().setwaitingTime(c.peek().getCompletionTime()-c.peek().getActualBrustTime()-c.peek().getArrivalTime());
                            c.peek().setTurnaroundTime(c.peek().getWaitingTime()+c.peek().getActualBrustTime());
                            finished.add(c.poll());
                            done--;
                        } 
                        else{
                            c.peek().setQuantum(c.peek().getQuantum()+2);
                            c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                            r.add(c.poll());
                        }
                    }
                }
                else{
                    c.peek().setQuantum(c.peek().getActualquantum()+(int)Math.ceil((double)c.peek().getQuantum()-q25)/2);
                    c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                    r.add(c.poll());
                    c.add(ph);
                    executionOrder.add(ph.getName());
                }
            } 
            else{
                int q50 = (quantum25(c.peek()))*2;
                c.peek().setQuantum(c.peek().getQuantum()-q50);
                c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                c.peek().setBrustTime(c.peek().getBrustTime()-q50);
                Process.t+=q25;
                n2=istherenewprocess(r,Process.t); 
                if(n2.isEmpty()==false){
                        Process sb = smallestbrusttime(c.peek(),n2);
                        if(sb==c.peek()){
                            c.peek().setBrustTime(c.peek().getBrustTime()-c.peek().getQuantum());
                            Process.t+=c.peek().getQuantum();
                            if(c.peek().getBrustTime()==0){
                                c.peek().setQuantum(0);
                                c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                                c.peek().setCompletionTime(Process.t);
                                c.peek().setwaitingTime(c.peek().getCompletionTime()-c.peek().getActualBrustTime()-c.peek().getArrivalTime());
                                c.peek().setTurnaroundTime(c.peek().getWaitingTime()+c.peek().getActualBrustTime());
                                finished.add(c.poll());
                                done--;
                            }
                            else{
                                c.peek().setQuantum(c.peek().getQuantum()+2);
                                c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                                r.add(c.poll());
                                c.add(sb);
                                executionOrder.add(sb.getName());
                            }
                        }
                        else{
                            c.peek().setQuantum(c.peek().getActualquantum()+c.peek().getQuantum());
                            c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                            r.add(c.poll());
                            c.add(sb);
                            executionOrder.add(sb.getName());
                        }
                    }
                    else{
                        c.peek().setBrustTime(c.peek().getBrustTime()-c.peek().getQuantum());
                        Process.t+=c.peek().getQuantum();
                        if(c.peek().getBrustTime()==0){
                            c.peek().setQuantum(0);
                            c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                            c.peek().setCompletionTime(Process.t);
                            c.peek().setwaitingTime(c.peek().getCompletionTime()-c.peek().getActualBrustTime()-c.peek().getArrivalTime());
                            c.peek().setTurnaroundTime(c.peek().getWaitingTime()+c.peek().getActualBrustTime());
                            finished.add(c.poll());
                            done--;
                        }
                        else{
                            c.peek().setQuantum(c.peek().getQuantum()+2);
                            c.peek().quantumTimeHistory.add(c.peek().getQuantum());
                            Process x=c.poll();
                            r.add(x);
                        }
                    }    
            }
        }

        System.out.println("Process execution order : ");
        for(Process i:finished)
            {
                System.out.println(i.getName()+' ');
            }   
        
        System.out.println("Waiting time for each process : ");
        for(Process i:finished)
            {
                if(i.getWaitingTime()<0)
                    i.setwaitingTime(0);
                System.out.println(i.getName()+" : " +i.getWaitingTime());
            } 
        
        System.out.println("Turnaround time for each process : ");
        for(Process i:finished)
            {
                System.out.println(i.getName()+" : " +i.getTurnaroundTime());
            } 
        
        double avgw=0;
        for(Process i:finished)
            {
                avgw+=i.getWaitingTime();
            } 
        avgw=avgw/num;
        System.out.println("Average waiting time : "+avgw);  
        
        double avgt=0;
        for(Process i:finished)
            {
                avgt+=i.getTurnaroundTime();
            } 
        avgt=avgt/num;
        System.out.println("Average Turn around time : "+avgt); 
        
        System.out.println("History of quantum time to each process :"); 
        for(Process i:finished)
            {
                System.out.println(i.getName()+" : "); 
                for(Integer j: i.quantumTimeHistory){
                    if(j<0){
                        j=0;}
                    System.out.println(j+" ");
                }
            } 
        }  
    }

