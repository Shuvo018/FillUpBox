import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Play implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JLabel txtLabel = new JLabel();
    JLabel scoreTxtLabel = new JLabel();
    JPanel button_panel = new JPanel();
    int rows = 5, columns = 8;
    JButton[] buttons = new JButton[rows*columns];
    boolean player1 ;
    String white = "0", blue = "1", orange = "2", red = "3", green = "4";
    int plyr1 = 0, plyr2 = 0;
    Play(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        txtLabel.setText("HELLO");
        txtLabel.setBackground(Color.DARK_GRAY);
        txtLabel.setForeground(Color.RED);
        txtLabel.setHorizontalAlignment(JLabel.CENTER);
        txtLabel.setFont(new Font("Ink Free",Font.BOLD,35));
        txtLabel.setOpaque(true);

        scoreTxtLabel.setText("Score : ");
        scoreTxtLabel.setForeground(Color.RED);
        scoreTxtLabel.setBackground(Color.DARK_GRAY);
        scoreTxtLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreTxtLabel.setFont(new Font("Ink Free",Font.BOLD,35));
        scoreTxtLabel.setOpaque(true);

        title_panel.setBounds(0,0,800,100);
        title_panel.setBackground(Color.DARK_GRAY);
        title_panel.setLayout(new BorderLayout());

        title_panel.add(txtLabel,BorderLayout.WEST);
        title_panel.add(scoreTxtLabel,BorderLayout.EAST);

        frame.add(title_panel,BorderLayout.NORTH);

        button_panel.setLayout(new GridLayout(rows,columns));
        button_panel.setBackground(new Color(150,150,150));
        for (int i=0; i<(rows*columns); i++){

            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,80));
            buttons[i].setText(white);
//            buttons[i].setForeground(Color.GREEN);
//            buttons[i].setBackground(Color.GREEN);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        frame.add(button_panel);
        firstTime();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<(rows*columns); i++){
            if(e.getSource() == buttons[i]){

                // two player logic
                if(player1){
                    player1 = false;
                    clicked(i,"M");
                    txtLabel.setText("player N");
                }else{
                    player1 = true;
                    clicked(i,"N");
                    txtLabel.setText("player M");
                }

                // computer logic
//                clicked(i,"M");
//                txtLabel.setText("player N");
//                computer();
            }
        }
        if(plyr1+plyr2 == (rows*columns)){
            wins();
        }
    }

    private void nearBox(int i) {
        if((i%columns)+1<columns){//right
                if(buttons[i+1].getText() != blue && buttons[i+1].getText() != orange && buttons[i+1].getText() != red && buttons[i+1].getText() != "M" && buttons[i+1].getText() != "N"){
                buttons[i+1].setText(blue);
                buttons[i+1].setBackground(Color.blue);
            }
        }
        if(i%columns != 0){//left
            if(buttons[i-1].getText() != blue && buttons[i-1].getText() != orange && buttons[i-1].getText() != red && buttons[i-1].getText() != "M" && buttons[i-1].getText() != "N") {
                buttons[i-1].setText(blue);
                buttons[i-1].setBackground(Color.blue);
            }
        }
        if(i+columns<(rows*columns)){//down
            if(buttons[i+columns].getText() != blue && buttons[i+columns].getText() != orange && buttons[i+columns].getText() != red && buttons[i+columns].getText() != "M" && buttons[i+columns].getText() != "N") {
                buttons[i+columns].setText(blue);
                buttons[i+columns].setBackground(Color.blue);
            }
        }
        if(i-columns>=0){//up
            if(buttons[i-columns].getText() != blue && buttons[i-columns].getText() != orange && buttons[i-columns].getText() != red && buttons[i-columns].getText() != "M" && buttons[i-columns].getText() != "N") {
                buttons[i - columns].setText(blue);
                buttons[i - columns].setBackground(Color.blue);
            }
        }
    }

    void clicked(int i, String player){
        if(buttons[i].getText()==white){
            buttons[i].setText(blue);
            buttons[i].setBackground(Color.blue);
        }
        else if(buttons[i].getText() == blue){
            buttons[i].setText(orange);
            buttons[i].setBackground(Color.ORANGE);
        }else if(buttons[i].getText() == orange){
            buttons[i].setText(red);
            buttons[i].setBackground(Color.red);
        }
        else if(buttons[i].getText() == red){
            buttons[i].setText(player);
            buttons[i].setBackground(Color.GREEN);
            buttons[i].setEnabled(false);
            if(player == "M"){
                plyr1++;
            }else{
                plyr2++;
            }
            scoreTxtLabel.setText("M : "+plyr1+" N : "+plyr2);
            nearBox(i);
        }
    }
    void firstTime(){
        if(random.nextInt(2) == 0){
            player1 = true;
            txtLabel.setText("player M");
        }else{
            //computer();
            player1 = false;
            txtLabel.setText("player N");
        }
    }
    void wins(){
        if(plyr1>plyr2) {
            txtLabel.setText(" M win");
        }else if(plyr1 == plyr2){
            txtLabel.setText(" draw");
        }
        else{
            txtLabel.setText(" N win");
        }
    }
    void computer(){
        int com = random.nextInt((rows*columns));
        if (buttons[com].getText() != "M" && buttons[com].getText() != "N" ){

            clicked(com,"N");
        }else{
            computer();
        }
        txtLabel.setText("player M");
    }
}
