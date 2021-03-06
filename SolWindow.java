import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SolWindow {

    private static CoolButton[][] buttons = new CoolButton[3][3];
    private static ArrayList<State> states;
    private static JFrame frame;
    private static JPanel panel;
    private static JPanel commandPanel;
    private static JButton controlButtons;
    private static int location;
    private static State currentState;

    private static JButton prev, next;


    public SolWindow(ArrayList<State> states) {
        this.states = states;
        frame = new JFrame("8-Puzzle Solution by Marcos");

        // Initialize panel
        initializeBoard();
        setBoardState(0);
        System.out.println("Yeah2");
        // Initialize CommandPanel
        initializeCommandPanel();
        // Initialize frame settings.
        frame.setSize(new Dimension(600, 700));
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(commandPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    private static void initializeCommandPanel() {
        commandPanel = new JPanel();

        prev = new JButton("Previous");
        next = new JButton("Next");

        prev.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent ev){
                if(location > 0) {
                    setBoardState(--location);
                }
            }
            public void mousePressed(MouseEvent ev){}
            public void mouseEntered(MouseEvent ev){}
            public void mouseReleased(MouseEvent ev){}
            public void mouseExited(MouseEvent ev){}

        });

        next.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent ev){
                if(location < states.size()-1) {
                    setBoardState(++location);
                }
            }
            public void mousePressed(MouseEvent ev){}
            public void mouseEntered(MouseEvent ev){}
            public void mouseReleased(MouseEvent ev){}
            public void mouseExited(MouseEvent ev){}

        });

        commandPanel.setSize(new Dimension(600, 100));
        commandPanel.add(prev);
        commandPanel.add(next);

    }

    private static void setBoardState(int x) {
        location = x;
        currentState = states.get(location);

        frame.setTitle("8-Puzzle Solution by Marcos: Step " + (location+1) + " of " + states.size());

        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(currentState.getValues()[i][j] != 0) {
                    // Draw a number if value is not zero
                    buttons[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setText(currentState.getValues()[i][j] + "");
                }
                else {
                    // If the number is zero, Do not draw the number and chage the background
                    buttons[i][j].setBackground(Color.GRAY);
                    buttons[i][j].setText("");
                }
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
            }
        }

    }

    private static void initializeBoard() {
        // Initializes the panel with buttons.
        // The states of the buttons are also initialized here.
        panel = new JPanel();
        panel.setSize(new Dimension(600, 600));
        panel.setLayout(new GridLayout(3, 3));

        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                buttons[i][j] = new CoolButton(i, j);
                buttons[i][j].setRolloverEnabled(false);
                buttons[i][j].setEnabled(false);
                panel.add(buttons[i][j]);
            }
        }

    }


    // Prevent implicit declaration
    private SolWindow() {}
}
