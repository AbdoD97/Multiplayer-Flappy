import flappyafterfix.MultiplayerHandler;
import static flappyafterfix.MultiplayerHandler.broadcast;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.*;

public class App {

    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    public static void main(String[] args) throws UnknownHostException, IOException {
      //  System.out.println(  MultiplayerHandler.socket.);
     InetAddress localhost = InetAddress.getLocalHost(); 
      /*  System.out.println("System IP Address : " + 
                      (localhost.getHostAddress()).trim()); */
      String IP = localhost.getHostAddress().trim();
        broadcast(IP , InetAddress.getByName("255.255.255.255"));
       /* MultiplayerHandler m1 = new MultiplayerHandler();
        Thread t1 =new Thread(m1);  
        t1.start();  */
        //System.out.println("dsadsa");
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel();
     
        
        frame.add(panel);
    }
}
