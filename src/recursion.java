import java.io.*;
import java.util.ArrayList;


/**
 * @author RECEP TAYYIP ERDOGAN
 * @since 05.12.2020
 */
public class recursion extends Map{
    int x;
    int y;
    ArrayList<String> paths = new ArrayList<>();
    File file = new File("output.txt");
    int numberofCombination = 0;

    public recursion() throws IOException {
        file.delete();
        print();//Map print to the console
        findPlayer(map);
        recursiveMethod(x,y);// we call recursive method in here
        System.out.println("All number of combinations = " + numberofCombination);
        /* we use above the code to show how many paths in map to reach to
         Final point on the console */
    }

    public void  findPlayer(String[][] map) throws IOException {
            /*we use this method to find player's coordinate on the map

                         */
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j].contains("P")){
                    x = i;
                    y = j;
                    return;
                }
            }
        }
    }


    public void recursiveMethod(int x , int y) throws IOException {
        /*
        Our recursive method in here.
         */

        if ((x >= 0 && x < getHeight() && y >= 0 && y  < getWidth())
                && map[x][y].equals("FI") ) {

            pathSaving(x,y);
            System.out.println(paths.toString());
            write(paths);
            numberofCombination++;
            paths.remove(paths.size()-1);

            return;

        } else if (!(x >= 0 && x < getHeight() && y >= 0 && y  < getWidth())
                || (map[x][y].contains("O"))
                || paths.contains(x + "," + y)) {
            return;
        }

        pathSaving(x, y);//we call this method to Save the paths.
        recursiveMethod(x - 1, y);//call recursive methods
        recursiveMethod(x, y + 1);//call recursive methods
        recursiveMethod(x + 1, y);//call recursive methods
        recursiveMethod(x, y - 1);//call recursive methods
        paths.remove(paths.size()-1);//We use this method to see more
        // combinations of path.

      }

    public void write(ArrayList<String> paths) throws FileNotFoundException {
        //We are writing the txt file in here.
        PrintWriter output = new PrintWriter(new FileOutputStream(file,
                true));
        String [] tempArray = new String[paths.size()];

        for (int i = 0; i < paths.size(); i++) {
            tempArray[i] = "(" + paths.get(i) + ") ";
            output.append(tempArray[i]);
        }

        output.println();
        output.close();
    }

    public void pathSaving(int x, int y) {  //Path adding method
            paths.add(x + "," + y);
        }
    public void print() {
        /* we use this method to print console
        map to see clear map to check errors
        */
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
