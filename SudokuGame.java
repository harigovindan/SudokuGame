package sudokugame;
import javax.swing.*;
public class SudokuGame extends JFrame
{
    public static void main(String[] args)
    {
        SudokuGameUserInterface ff=new SudokuGameUserInterface();
        JFrame frame = new JFrame("Sudoku Solver-Hari");
        frame.add(ff);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(650,250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}