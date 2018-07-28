package project.tic.tac.toe;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

class MainFrame1 extends Frame{
    Button []b;
    TextField tf;
    int count=0,end=0;
    MainFrame1()
    {
        b=new Button[10];
        for(int i=0;i<=9;i++)
        {
            char e;
            e = (char)(i+48);
            String w ="";
            w+=e;
            b[i]=new Button(w);
            b[i].setFont(new Font("Arial",Font.PLAIN,80));
            b[i].setBackground(Color.WHITE);
        }
        b[0].setLabel("Play-Again");
        b[0].setFont(new Font("Arial",Font.PLAIN,30));
        tf=new TextField();
        tf.setBackground(Color.yellow);
        tf.setForeground(Color.BLUE);
        tf.setFont(new Font("Serif",Font.ITALIC,25));
    }
    public String checkWin()
    {
        String []s=new String [10];
        for(int i=1;i<=9;i++)
        {
            s[i]=b[i].getLabel();
        }
        if(s[1].equals(s[2])&&s[1].equals(s[3]))
            return s[1];
        if(s[1].equals(s[4])&&s[1].equals(s[7]))
            return s[1];
        if(s[2].equals(s[5])&&s[2].equals(s[8]))
            return s[2];
        if(s[3].equals(s[6])&&s[3].equals(s[9]))
            return s[3];
        if(s[4].equals(s[5])&&s[4].equals(s[6]))
            return s[4];
        if(s[7].equals(s[8])&&s[7].equals(s[9]))
            return s[7];
        if(s[1].equals(s[5])&&s[1].equals(s[9]))
            return s[1];
        if(s[3].equals(s[5])&&s[3].equals(s[7]))
            return s[3];
        String q="A";
        return q;
    }
    public char checkWin1(String a1)
    {
        a1=" "+a1;
        char []s1=new char[10];
        for(int i=1;i<=9;i++)
            s1[i]=a1.charAt(i);
        if((s1[1]==s1[2])&&(s1[1]==s1[3]))
            return s1[1];
        if((s1[1]==s1[4])&&(s1[1]==s1[7]))
            return s1[1];
        if((s1[2]==s1[5])&&(s1[2]==s1[8]))
            return s1[2];
        if((s1[3]==s1[6])&&(s1[3]==s1[9]))
            return s1[3];
        if((s1[4]==s1[5])&&(s1[4]==s1[6]))
            return s1[4];
        if((s1[7]==s1[8])&&(s1[7]==s1[9]))
            return s1[7];
        if((s1[1]==s1[5])&&(s1[1]==s1[9]))
            return s1[1];
        if((s1[3]==s1[5])&&(s1[3]==s1[7]))
            return s1[3];
        char q = 'A';
        return q;
    }
    public boolean checkO(int ind,String p)
    {
        char e=p.charAt(ind-1);
        char[] myNameChars = p.toCharArray();
        myNameChars[ind-1] = 'O';
        p = String.valueOf(myNameChars);
        if(checkWin1(p)!='A')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkX(int ind,String p)
    {
        char e=p.charAt(ind-1);
        char[] myNameChars = p.toCharArray();
        myNameChars[ind-1] = 'X';
        p = String.valueOf(myNameChars);
        if(checkWin1(p)!='A')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void computerMove()
    {
        String curr="";
        for(int i=1;i<=9;i++)
        {
            curr+=b[i].getLabel();
        }
        for(int i=1;i<=9;i++)
        {
            if(curr.charAt(i-1)!='O'&&curr.charAt(i-1)!='X')
                if(checkX(i,curr))
                {
                    b[i].setLabel("X");
                    b[i].setBackground(Color.BLUE);
                    return;
                }
        }
        for(int i=1;i<=9;i++)
        {
            if(curr.charAt(i-1)!='O'&&curr.charAt(i-1)!='X')
                if(checkO(i,curr))
                {
                    b[i].setLabel("X");
                    b[i].setBackground(Color.BLUE);
                    return;
                }
        }
        for(int i=1;i<=9;i++)
        {
            if(curr.charAt(i-1)!='O'&&curr.charAt(i-1)!='X')
            {
                b[i].setLabel("X");
                b[i].setBackground(Color.BLUE);
                return;
            }
        }
    }
    private void centerFrame() {

            Dimension windowSize = getSize();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Point centerPoint = ge.getCenterPoint();

            int dx = centerPoint.x - windowSize.width / 2;
            int dy = centerPoint.y - windowSize.height / 2;    
            setLocation(dx, dy);
    }
    public void setup(MediaPlayer mediaPlayer)
    {
        mediaPlayer.play();
        for(int i=1;i<=9;i++)
        {
            add(b[i]);
            b[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Button b=(Button) e.getSource();
                    String f=b.getLabel();
                    if(f!="O"&&f!="X"&&end==0)
                    {
                        count++;
                        b.setLabel("O");   
                        b.setBackground(Color.RED);
                        checkWin();
                        String winner = checkWin();
                        if(winner!="A"&&winner!="")
                        {
                            tf.setText(winner+" is the winner!");
                            end=1;
                        }
                        else if(count==9)
                        {
                            end=1;
                            tf.setText("Match is Draw!");
                        }
                        count++;
                        try{
                        TimeUnit.MILLISECONDS.sleep(500);
                        }
                        catch(InterruptedException z){
                        System.out.println();
                        }
                        if(end==0)
                        {
                        computerMove();
                        winner = checkWin();
                        if(winner!="A"&&winner!="")
                        {
                            tf.setText(winner+" is the winner!");
                            end=1;
                        }
                        else if(count==9)
                            tf.setText("Match is Draw!");
                        }
                    }
                }
            });
        }
        add(tf);
        add(b[0]);
        b[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                FirstPage fp=new FirstPage();
                    fp.setup(mediaPlayer,1);
                dispose();
            }
        
        });
        setVisible(true);
        setSize(562,750);
        centerFrame();
        setTitle("Tic-Tac-Toe");
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                
                mediaPlayer.dispose();
                dispose();  
            }  
        });  
        setLayout(new GridLayout(4,3));
    }
}

class MainFrame2 extends Frame{
    Button []b;
    TextField tf;
    int count=0,end=0;
    MainFrame2()
    {
        b=new Button[10];
        for(int i=0;i<=9;i++)
        {
            char e;
            e = (char)(i+48);
            String w ="";
            w+=e;
            b[i]=new Button(w);
            b[i].setFont(new Font("Arial",Font.PLAIN,80));
            b[i].setBackground(Color.WHITE);
        }
        b[0].setLabel("Play-Again");
        b[0].setFont(new Font("Arial",Font.PLAIN,30));
        tf=new TextField();
        tf.setBackground(Color.yellow);
        tf.setFont(new Font("Serif",Font.ITALIC,25));
    }
    public String checkWin()
    {
        String []s=new String [10];
        for(int i=1;i<=9;i++)
        {
            s[i]=b[i].getLabel();
        }
        if(s[1].equals(s[2])&&s[1].equals(s[3]))
            return s[1];
        if(s[1].equals(s[4])&&s[1].equals(s[7]))
            return s[1];
        if(s[2].equals(s[5])&&s[2].equals(s[8]))
            return s[2];
        if(s[3].equals(s[6])&&s[3].equals(s[9]))
            return s[3];
        if(s[4].equals(s[5])&&s[4].equals(s[6]))
            return s[4];
        if(s[7].equals(s[8])&&s[7].equals(s[9]))
            return s[7];
        if(s[1].equals(s[5])&&s[1].equals(s[9]))
            return s[1];
        if(s[3].equals(s[5])&&s[3].equals(s[7]))
            return s[3];
        String q="A";
        return q;
    }
    private void centerFrame() {

            Dimension windowSize = getSize();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Point centerPoint = ge.getCenterPoint();

            int dx = centerPoint.x - windowSize.width / 2;
            int dy = centerPoint.y - windowSize.height / 2;    
            setLocation(dx, dy);
    }
    public void setup(MediaPlayer mediaPlayer)
    {
        mediaPlayer.play();
        for(int i=1;i<=9;i++)
        {
            add(b[i]);
            b[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Button b=(Button) e.getSource();
                    String f=b.getLabel();
                    if(f!="O"&&f!="X"&&end==0)
                    {
                        count++;
                        if(count%2==1)
                        {
                            b.setLabel("O");
                            b.setBackground(Color.RED);
                        }
                        else
                        {
                            b.setLabel("X");
                            b.setBackground(Color.BLUE);
                        }
                    }
                    String winner = checkWin();
                        if(winner!="A"&&winner!="")
                        {
                            tf.setText(winner+" is the winner!");
                            end=1;
                        }
                        else if(count==9)
                            tf.setText("Match is Draw!");
                }
            });
        }
        add(tf);
        add(b[0]);
        b[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                FirstPage fp=new FirstPage();
                fp.setup(mediaPlayer,1);
                dispose();
            }
        
        });
        setVisible(true);
        setSize(562,750);
        centerFrame();
        setTitle("Tic-Tac-Toe 2 Player");
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                
                mediaPlayer.dispose();
                dispose();  
            }  
        });  
        setLayout(new GridLayout(4,3));
    }
}

class FirstPage extends Frame
{
    Button b1,b2;
    Label l;
    MediaPlayer mediaPlayer;
    FirstPage()
    {
        l=new Label("Choose Game-Type");
        b1=new Button("1 Player");
        b2=new Button("2 Player");
        final JFXPanel fxPanel = new JFXPanel();
    }
    public void action()
    {
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
              MainFrame1 d=new MainFrame1();
              d.setup(mediaPlayer);
              dispose();
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                MainFrame2 d=new MainFrame2();
                d.setup(mediaPlayer);
                dispose();
            }
        });
    }
     private void centerFrame() {

            Dimension windowSize = getSize();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Point centerPoint = ge.getCenterPoint();

            int dx = centerPoint.x - windowSize.width / 2;
            int dy = centerPoint.y - windowSize.height / 2;    
            setLocation(dx, dy);
    }
    public void setup(MediaPlayer mp,int state) 
    {   
        if(state!=0)
        {
            mp.stop();
        }
        String bip = "E:\\songs &Videos\\Love song\\01 KITNA HASEEN CHEHRA {DILWALE}.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        setBackground(Color.orange);
        setLayout(null);
        l.setBounds(120,100,200,30);
        Font font =new Font("Serif",Font.ITALIC,20);
        l.setFont(font);
        l.setForeground(Color.darkGray);
        b1.setBounds(150,150,100,30);
        b2.setBounds(150,200,100,30);
        action();
        add(l);
        add(b1);
        add(b2);
        setVisible(true);
        setSize(400,400);
        centerFrame();
        setTitle("First Page");
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();
                mediaPlayer.dispose();
            }  
        });  
    }
}
public class ProjectTicTacToe {

    public static void main(String[] args) {
        FirstPage fp=new FirstPage();
        fp.setup(null,0);
    }
    
}
