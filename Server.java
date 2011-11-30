// There are 3 web servers waiting for requests.Each serves the request for 10msec.Requests are generated in each 4msec. The system can serve maximum  of 10 requests.

class Server{
  public static void main(String args[]) throws Exception{
     Admin a = new Admin();
     Ser s[]=new Ser[3];
     for(int i=0;i<3;i++)
        s[i]=new Ser(i,a);
     Req r = new Req(a);
     r.start();
     for(int i=0;i<3;i++)
        s[i].start();
        
     
     }
  }
  
  class Ser extends Thread{
     Admin a;
     int id;
     Ser(int i,Admin a1){
        id=i;
        a=a1;
     }
     public void run(){
       while(true)
       {
         
         int k= a.accept(id);
         if(k==1) try{stop();}catch(Exception e){}
         try{sleep(1000);}catch(Exception e){}
       }
     }
  }
  
  class Req extends Thread{
     Admin a;
     Req(Admin a1){
       a=a1;
     }
     public void run(){
        for(int i=0;i<10;i++){
         int k=a.requ(i); if(k==1) stop();
          try{sleep(400);}catch(Exception e){}
        }
     }
  }
  
  class Admin {
     int count;
     int r,f;
     boolean avail;
     public synchronized int accept(int l){
       count++;
       while(avail==false){
        try{  wait();}catch(Exception e){}
       }   
       
          
       if(count<10){
       
       System.out.println("server "+l+" serves request"+ r);
       avail=false;
       notifyAll();

       return 0;
       }
       else{
       
       System.out.println("can't serve more than 10 request");
       return 1;
       }
      }
     
     
     public synchronized int requ(int i){
       while(avail==true){
        try{  wait();}catch(Exception e){}
       }   
        r=i;
        System.out.println("request"+ r);
        avail=true;
        notifyAll();
        return f;
        
      }
}
