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
        db.createTable();
        ArrayList<Rubric> allInfo = db.fetchAllRecords();
        gui = new RubrickCubicGUI(this, allInfo);
    }

    void addRecord(Rubric newRubric) {
        db.addRecord(newRubric);
    }

    ArrayList<Rubric>getAllInfo() {return db.fetchAllRecords();}


    public void updateTime(Rubric rub) {
        db.updateTime(rub);
    }
}
