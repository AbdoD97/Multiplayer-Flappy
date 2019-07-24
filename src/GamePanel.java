import flappyafterfix.TCPhandler;
import flappyafterfix.TCPhandler2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
   
    private Game game;
public static Graphics2D g2D;
    public GamePanel() {
        game = new Game();
        new Thread(this).start();
    }

    public void update() throws IOException {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

         g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);


        g2D.setColor(Color.BLACK);

        if (!game.started && TCPhandler.allReady) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press SPACE to start", 150, 240);
        } else if (!game.started && !TCPhandler.allReady) 
        {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Waiting for friends :( ", 150, 240);
        }
        else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2D.drawString(Integer.toString(game.score), 10, 465);
        }

        if (game.gameover && TCPhandler.peerScore.equals("-1")&& TCPhandler2.peerScore.equals("-1") ) {
            
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            if(TCPhandler.allReady == true)
            { g2D.drawString("You Lost! Your score is " + Game.score + " waiting your peer to finish!"  , 1, 240);
              
            }
           
            
        }
        else  if(game.gameover )
        {
              g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
           if(TCPhandler.allReady == true)
            g2D.drawString("Your score is " + Game.score +" Your peer score is " + TCPhandler.peerScore , 20, 240);
          
           
            if(TCPhandler2.allReady == true)
            g2D.drawString("Your score is " + Game.score +" Your peer score is " + TCPhandler2.peerScore , 20, 240);
        }
                
                
                
        
       
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
