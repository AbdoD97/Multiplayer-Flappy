/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyafterfix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kondos
 */
public class MultiplayerHandler implements Runnable {

    public void run() {
        try {
            peerDiscovery();
        } catch (IOException ex) {
            Logger.getLogger(MultiplayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DatagramSocket socket = null;
    public static String IP ;
      public static int tcpPort;

    public static void broadcast(
            String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);

        byte[] receiveData = new byte[1000];

        socket.send(packet);
        socket.setSoTimeout(3000);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(receivePacket);
            buffer = receivePacket.getData();
            System.out.println(new String(buffer));
            
            String Here = new String(buffer); //>>>>>extract ip and port mn l string and connect tcp <<<<<<<<<<<<
            
            String IPADDRESS_PATTERN = 
        "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b (and Port is\\ (\\d+))";

Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
Matcher matcher = pattern.matcher(Here);
matcher.find();
TCPhandler2.address = matcher.group(1)+"."+matcher.group(2)+"."+matcher.group(3)+"."+matcher.group(4);
TCPhandler2.port = Integer.valueOf(matcher.group(6));
//TCPhandler.Connect(matcher.group(1)+"."+matcher.group(2)+"."+matcher.group(3)+"."+matcher.group(4) , Integer.valueOf(matcher.group(6)));
      TCPhandler2 m3 = new TCPhandler2();
            Thread tz = new Thread(m3);
            tz.start();

            

        } catch (SocketTimeoutException e) {
            System.out.println("Timeout reached!!! I will be the server :@ ");
            socket.setSoTimeout(0);
            
            //>>>>>>>>>>>>open thread el tcp hna bta3 el listening <<<<<<<
            TCPhandler m2 = new TCPhandler();
            Thread t2 = new Thread(m2);
            t2.start();
            
            MultiplayerHandler m1 = new MultiplayerHandler();
            Thread t1 = new Thread(m1);
            t1.start();

        }

        // socket.close();
    }

    public static void peerDiscovery() throws SocketException, IOException {
        socket = new DatagramSocket(4445);
        byte[] buffer = new byte[1000];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("waiting");
        socket.receive(packet);
        
     /*  buffer = packet.getData();
       System.out.println( new String(buffer) );*/
       InetAddress localhost = InetAddress.getLocalHost(); 
       IP = localhost.getHostAddress().trim();
       //tcpPort = 111; //static for testing comment it and set el global var port lma tlisten 3la el tcp
        String connectMe ="Connect at TCP ip is " + IP +  " and Port is " + tcpPort ;
       // buffer = new byte[1000];
        buffer = connectMe.getBytes();

        DatagramPacket confirm = new DatagramPacket(buffer, buffer.length , packet.getAddress() , packet.getPort());
       // System.out.println(packet.getAddress().toString() );
              

       // socket = new DatagramSocket();
        socket.send(confirm);
        socket.close();

    }
    
    

}
