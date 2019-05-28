import java.awt.*;
import javax.swing.*;
/** MyWriter creates a graphics window that displays a sentence */
public class MyWriter extends JPanel
{ private int width;  // the frame's width
    private int height; // the frame's height
    private int num;
    public String[] sentence; // holds the sentence to be displayed
    public int x_position = 10;  // x-position of sentence
    public int y_position= 20;
    int offset = 0;// y-position of sentence in order

    /** Constructor MyWriter creates the Panel
     * @param w - the window's width
     * @param h - the window's height  */
    public MyWriter(int w, int h,int num)
    { width = w;
        height = h;
        this.num = num;
        x_position = width / 5;  // set the sentence's position
        y_position = height / 2;
        sentence  = new String[num];
        JFrame my_frame = new JFrame();
        my_frame.getContentPane().add(this);
        my_frame.setTitle("Airline Reservation");
        my_frame.setSize(width, height);
        my_frame.setVisible(true);
    }

    /** paintComponent paints the panel
     * @param g - the ``graphics pen'' that draws the items */
    public void paintComponent(Graphics g)
    { g.setColor(Color.black);
        for (int i = 0;i<num;i++){
            g.drawString(sentence[i], x_position, y_position+offset);
            offset = offset+20;}
    }

    /** writeSentence displays a new string in the window
     * @param s - the sentence to be displayed  */
    public void writeSentence(String s[],int x_position,int y_position)
    { sentence = s;
        this.x_position=x_position;
        this.y_position=y_position;
        this.repaint();  // indirectly forces  paintComponent  to execute
    }

    /** positionSentence redisplays the existing sentence in a new position
     * @param new_x - the new horizontal starting position
     * @param new_y - the new vertical starting position  */
    public void positionSentence(int new_x, int new_y)
    {   x_position = new_y;
        y_position = new_y;
        this.writeSentence(sentence,x_position,y_position);  // force a rewrite of the existing sentence
    }
}

