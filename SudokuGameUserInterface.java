package sudokugame;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
public class SudokuGameUserInterface extends JPanel
{
    public JTextField right[][];
    public int[][] a = new int[9][9];
    public int pG=0;
    public JPanel Resetpanel,playpanel,leftPanel,rightPanel,Panel;
    private JButton button,reset;
    public String str;
    private final JLabel leftLabel;
    public JTextField[][] left = new JTextField[9][9];
    public SudokuGameUserInterface()
    {
        Panel=new JPanel(new FlowLayout());
        leftPanel=new JPanel(new GridLayout(9,9));
        leftPanel.setSize(400,400);
        rightPanel=new JPanel(new GridLayout(9,9));
        rightPanel.setSize(400,400);
        right=new JTextField[9][9];
        leftLabel=new JLabel();
        leftLabel.setText("Sudoku Solver");
        add(leftLabel, BorderLayout.SOUTH);
        leftPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));      
        rightPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));      
        playpanel=new JPanel(new GridLayout(1,1));
        playpanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        button=new JButton("PLAY");
        Resetpanel=new JPanel(new GridLayout(1,1));
        Resetpanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        reset=new JButton("RESET");
        playpanel.add(button);
        Resetpanel.add(reset);
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                a[i][j]=0;
                right[i][j]=new JTextField();
                left[i][j]=new JTextField();
                left[i][j].setColumns(2);
                right[i][j].setColumns(2);
                right[i][j].setEditable(false);
                if((i<3 && j<3) || (i>2 && i<6 && j>2 && j<6) || (i<3 && j<9 && j>5) || (j<3 && i<9 && i>5) || (i>5 && i<9 && j>5 && j<9))
                {
                    left[i][j].setBackground(Color.BLACK);
                    right[i][j].setBackground(Color.BLACK);
                    left[i][j].setForeground(Color.WHITE);
                    right[i][j].setForeground(Color.WHITE);
                }
                else
                {
                    left[i][j].setBackground(Color.WHITE);
                    right[i][j].setBackground(Color.WHITE);
                }               
                leftPanel.add(left[i][j]);
                rightPanel.add(right[i][j]);
            }
        }
        Panel.add(leftPanel,BorderLayout.WEST);
        Panel.add(playpanel);
        Panel.add(Resetpanel);
        Panel.add(rightPanel,BorderLayout.EAST);               
        add(Panel);        
        reset.addActionListener(new ActionListener()
        { 
            public void actionPerformed(ActionEvent event)
            {
                reset();
            }
         });
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                button.setEnabled(false);
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                    {
                        try
                        {
                            a[i][j]=Integer.parseInt(left[i][j].getText());
                        }
                        catch(NumberFormatException e)
                        {
                            a[i][j]=0;
                        }
                    }
                }
                try
                {
                    solve(0, 0);
                }
                catch (Exception ex)
                {
                }
            }
        });
    }    
    protected boolean checkRow(int i,int num)
    {
        for(int j=0;j<9;j++)
        {
            if(a[i][j]==num)
            {
                return false;
            }
        }
        return true;
    }
    protected boolean checkCol(int j,int num)
    {
        for(int i=0;i<9;i++)
        {
            if(a[i][j]==num)
            {
                return false;
            }
        }
        return true;
    }
    protected boolean checkBox(int i, int j, int num)
    {
        i=(i/3)*3;
        j=(j/3)*3;
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                if(a[i+r][j+c]==num)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public void solve(int i, int j) throws Exception
    {
        if(i>8)
        {
            button.setEnabled(true);            
            JOptionPane.showMessageDialog(null, "Sudoku Solved!!!"); 
            //reset();
        }
        if(a[i][j]!=0)
        {
            next(i,j);
        }
        else
        {
            for(int num=1;num<10;num++)
            {
                if(checkRow(i,num) && checkCol(j,num) && checkBox(i,j,num))
                {
                    a[i][j]=num;
                    print();
                    next(i,j);
                }
            }
            a[i][j]=0;
            print();
        }
    }
    public void next(int i, int j) throws Exception
    {
        if(j<8)
        {
            solve(i, j+1);
        }
        else
        {
            solve(i+1,0);
        }
    }
    protected void print()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(a[i][j]!=0)
                {
                    right[i][j].setText(String.valueOf(a[i][j]));
                }
                else
                {
                    right[i][j].setText("");
                }
            }
        }
    }
    public void reset()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                a[i][j]=0;
                left[i][j].setText("");
                right[i][j].setText("");
            }
        }
    }
}

