import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by mash4 on 4/19/2017.
 */
public class RubrickCubicGUI extends JFrame {
    private JPanel rootPanel;
    private JTextField timetextField;
    private JTextField nametextField;
    private JList<Rubric> platersJlist;
    private JButton editTimeButton;
    private JButton deletePlayerButton;
    private JButton findPlayerButton;
    private JButton addPlayerButton;
    private JLabel allPlayersLists;

    private DefaultListModel<Rubric> playersListModel;
    private Controllers  cntrol;



    RubrickCubicGUI(Controllers controllers, ArrayList<Rubric> allRubics){
        this.cntrol = controllers;
        setTitle("Rubric Players !!!");

        setContentPane(rootPanel);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        playersListModel = new DefaultListModel<>();

        for (Rubric r : allRubics) {
            playersListModel.addElement(r);
        }

        platersJlist.setModel(playersListModel);
        platersJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Read data from JText
                String name = nametextField.getText();
                if(name.trim().length() == 0){
                    JOptionPane.showMessageDialog(RubrickCubicGUI.this,
                            "Please enter player name : ");
                    return;
                }
                double time;
                try{
                    time = Double.parseDouble(timetextField.getText());
                    if(time < 0){
                        JOptionPane.showMessageDialog(RubrickCubicGUI.this, "Please enter" +
                                " finished time");
                        return;
                    }

                }catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(RubrickCubicGUI.this, "Enter finished time : ");
                    return;
                }

                Rubric newRubric = new Rubric(name, time);
                playersListModel.addElement(newRubric);
                cntrol.addRecord(newRubric);
                
                
                nametextField.setText("");
                timetextField.setText("");
            }
        });


        editTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Rubric> allData = cntrol.getAllInfo();
//                for(Rubric rb : allData)    {
//                   // playersListModel.addElement(rb);
//
//                }
               if(!(platersJlist.isSelectionEmpty()))   {
                    Rubric rb =  platersJlist.getSelectedValue();
                   double time = Double.parseDouble(JOptionPane.showInputDialog(RubrickCubicGUI.this,
                            "Please enter a new time record"));
                   String name = rb.name;
                   Rubric rub = new Rubric(name, time) ;
                   playersListModel.removeElement(rb);
                   playersListModel.addElement(rub);
                   // Send controller a message to update the database with the new time for the Rubics object.

                   controllers.updateTime(rub);

                }
                                                                                            
            }

        });
    }







    private void addRecord(Rubric newRubric) {
    }


}
