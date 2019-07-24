package flappyafterfix;

import java.net.*; 
import java.io.*; 
  
public class TCPhandler2 implements Runnable
{ 
    
    
    //initialize socket and input stream 
    private static Socket          socket   = null; 
    private static ServerSocket    server   = null; 
    private static DataInputStream in       =  null; 
    private static DataOutputStream out     = null; 
    public static boolean allReady = false;
    public static String peerScore = "-1" ;
    public static String address;
            public static int port;
    // constructor with port 
    public static void  acceptTCP() 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(0); 
             MultiplayerHandler.tcpPort = server.getLocalPort();
            System.out.println("TCP socket opened at " + server.getLocalPort());
            socket = server.accept(); 
            System.out.println("Client accepted"); 
           allReady = true;
            // takes score from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
             out    = new DataOutputStream(socket.getOutputStream()); 
           // System.out.println(in.available());
                  waitScore () ;

            
           // peerScore = str.toString();
          System.out.println("Closing connection"); 
              
            // close connection 
           // socket.close(); 
           // in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 

    public static void Connect () 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
             allReady = true;
            
        
              in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
             out    = new DataOutputStream(socket.getOutputStream()); 
            
           waitScore () ;
           
            
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
  
  
        // close the connection 
      /*  try
        { 
            in.close(); 
            out.close(); 
            //socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } */
    } 
    
    
    @Override
    public void run() {
        Connect();
    }
  
    public static void imDied(int score) throws IOException
    {
      
        PrintWriter printOut = new PrintWriter(out, true);
       // System.out.println("    " + Integer.toString(score));
            printOut.println(Integer.toString(score));
            printOut.flush();
            System.out.println("score sent");
    }
    public static void waitScore () throws IOException
    {
         String data = null;

                   BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));  
                  
        while ( (data = in.readLine()) != null ) {
            
            System.out.println("Peer Score is " + ": " + data);
           peerScore = data;
        }
    }
} 