import java.util.ArrayList;

/**
 * Created by mash4 on 4/19/2017.
 */
public class Controllers {
    static RubrickCubicGUI gui;
    static DB db;


    public static void main(String[] args) {
        Controllers controller = new Controllers();
        controller.startApp();
    }

    private void startApp() {
        db = new DB();
        db.createTables();
        ArrayList<Rubric> allInfo = db.fetchAllRecords();
        gui = new RubrickCubicGUI(this);
    }
    void addRecord(Rubric newRubric) {

        db.addRecord(newRubric);
    }
    ArrayList<Rubric>getAllInfo() {return db.fetchAllRecords();}

   
}
