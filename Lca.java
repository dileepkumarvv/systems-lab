import java.util.*;
class Lca{
  static int n,node1,node2,n1pos,n2pos;
  static int a[]=new int[10];
  static boolean v[]=new boolean[10];
  
  public static void main(String args[]) throws Exception{
     System.out.println("Enter the no of nodes");
     Scanner in = new Scanner(System.in);
     n = in.nextInt();
     
     System.out.println("Enter the nodes");
     for(int i=1;i<=n;i++){
       a[i]= in.nextInt();
       v[i]=false;
      }
     System.out.println("Enter two nodes");
     node1 = in.nextInt();
     node2 = in.nextInt();
     
     for(int i=1;i<=n;i++){ //find position of nodes
        if(a[i]==node1)
          n1pos=i;
        if (a[i]==node2)
          n2pos=i;
     }
     
     runthread t1 = new runthread(n1pos,v,a);
     runthread t2 = new runthread(n2pos,v,a);   
     
     t1.start();
     t2.start();  
     
     
}
}
     
class runthread extends Thread{
   int pos,a[];
   boolean v[],found;
    runthread(int i,boolean k[],int b[]){
      pos = i;
      a=b;
      v=k;
    }
    public void run(){
    
    for(int j= pos/2 ; found==false && j>0 ; j=j/2){
    
    		if ( v[j] == true ){
    		   System.out.println("LCA = "+ a[j]);
    		   found=true;// exiting for loop
    		   stop();
    	        }
    	       v[j] = true;                                          // two threads are executing parallely. one vists the parent and marks it as true. when the 										other thread visits the same node( checks the visited array)  it is the least common ancestor
       }
   
   }
}   
