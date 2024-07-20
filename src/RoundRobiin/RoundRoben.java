package RoundRobiin;
import java.util.ArrayList;
import java.util.List;
public class RoundRoben {

	private int numOfPro;
	private int time=0;
	private int total;
	private int totalTat=0;
	private int totalWait=0;
	private String pro[];
	private List<String> ans= new ArrayList<String>(); 
	private List<String> ready=new ArrayList<String>(); 
	private List<Integer> readybr=new ArrayList<Integer>(); 
	private int br[];
	private int ar[];
	private int tat[];
	private int wait[];
	private int quant;
    private int con;
    public RoundRoben(int n,int q,int c,String pro[],int br[],int ar[])
    {
    	this.numOfPro=n;
    	this.quant=q;
    	this.con=c;
    	this.pro=pro;
    	this.br=br;
    	this.ar=ar;	
    	this.tat=new int[n];
    	this.wait=new int[n];
}
    public void runOfRR() {
    	int x=0;
	    for(int i=x;i<numOfPro;i++)
	    {
	    	
	    	if(time<ar[i])
	    	{
	    		x=i;
	    		break ;
	    	}
	    	else
	    	{
	    		ready.add(pro[i]);
	    		readybr.add(br[i]);
	    	}	
	    }
	   
	   
	    do
	    {
	    	
	    	for(int i=0;i<ready.size();i++)
	    	{
	    		if(readybr.get(i)==0)
	    			continue;
	    		if(readybr.get(i)>quant)
	    		{
	    			ans.add(ready.get(i));
	    			ans.add("con");
	    			readybr.set(i,(readybr.get(i)-quant));
	    			time+=quant+con;
	    		}
	    		else
	    		{
	    			ans.add(ready.get(i));
	    			ans.add("con");
	    			time+=(readybr.get(i)+con);
	    			tat[i]=time-ar[i];
	    			totalTat+=tat[i];
	    			readybr.set(i,0);
	    		}
	    		
	    		for(int i1=x;i1<numOfPro;i1++)
			    {
			    	if(time-1<=ar[i1])
			    	{
			    		x=i1;
			    		break ;
			    	}
			    	else
			    	{
			    		ready.add(pro[i1]);
			    		readybr.add(br[i1]);
			    		if(i1==numOfPro-1)
			    			x=i1+1;
			    	}	
			    }
	    	}
	    	
	    	total=0;
	    	for(int i=0;i<ready.size();i++)
	    	{
	    		
	    		total+=readybr.get(i);
	    	}	
	    }
	    while(total!=0);
	    for(int i=0;i<ready.size();i++)
	    {
	    	wait[i]=tat[i]-br[i];
	    	totalWait+=wait[i];
	    }
	    System.out.println(ans);
	    System.out.println("Process Name"+"\t"+"Turnaround Time"+"\t"+"\t"+"Waiting Time");
	    for(int i=0;i<numOfPro;i++)
	    {
	    	System.out.println(ready.get(i)+"\t"+"\t"+tat[i]+"\t"+"\t"+"\t"+wait[i]);
	    }
	    System.out.println("Average Waiting Time: "+(float)totalWait/numOfPro);
	    System.out.println("Average Turnaround Time: "+(float)totalTat/numOfPro);
    }
}
