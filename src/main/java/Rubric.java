/**
 * Created by mash4 on 4/19/2017.
 */
public class Rubric {
    String name ;
    double time;
    int id  ;
    int incrementID = 0;




    Rubric(String playerName,int playerID ,double finishedTime){
        this.name = playerName;
        this.time = finishedTime;
        incrementID ++ ;
        playerID = incrementID;
        this.id = playerID;

    }
    Rubric(String playerName ,double finishedTime){
        this.name = playerName;
        this.time = finishedTime;

    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player name is " + name  + " with an ID "+ id+
                "  finished the Rubric in  " + time + " seconds !"
                ;
    }
}
