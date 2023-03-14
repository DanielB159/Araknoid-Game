import Levels.LevelOne;
import Levels.LevelTwo;
import Levels.LevelThree;
import Levels.LevelFour;
import Levels.LevelInformation;
import Levels.GameFlow;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Daniel B.
 */
public class Ass6Game {
    /**
     * this main function.
     * @param argv - the string of arguments.
     */
    public static void main(String[] argv) {
        //first, we create the objects - the level list and the available levels.
        List<LevelInformation> l = new ArrayList<LevelInformation>();
        LevelOne l1 = new LevelOne();
        LevelTwo l2 = new LevelTwo();
        LevelThree l3 = new LevelThree();
        LevelFour l4 = new LevelFour();
        //first, we check weather the user has inputted some arguments. if not, this if will be true.
        if (argv.length == 1 && argv[0].equals("${args}")) {
            l.add(l1);
            l.add(l2);
            l.add(l3);
            l.add(l4);
        } else {
            //will reach here if the user has inputted some arguments.
            for (String i : argv) {
                //for each argument, we try to parse it to an int. if the parsing has succeeded, we will check
                // if it is from {1,2,3,4} and if so, add the level to the level list.
                // otherwise, the parseInt will fail and throw an Exception.
                try {
                    if (Integer.parseInt(i) == 1) {
                        l.add(l1);
                    } else if (Integer.parseInt(i) == 2) {
                        l.add(l2);
                    } else if (Integer.parseInt(i) == 3) {
                        l.add(l3);
                    } else if (Integer.parseInt(i) == 4) {
                        l.add(l4);
                    }
                    //if the try has failed, it means that the argument was not an integer. I so, we will
                    //ignore and keep checking the other arguments.
                } catch (Exception ignore) { }
            }
        }
        //if after all of the arguments the list is still empty - it means that the user has written only
        //invalid arguments. so we will start the game as usual.
        if (l.size() == 0) {
            l.add(l1);
            l.add(l2);
            l.add(l3);
            l.add(l4);
        }
        GameFlow gamemaker = new GameFlow();
        gamemaker.runLevels(l);
    }
}
