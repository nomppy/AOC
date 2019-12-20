import java.io.*;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("data5.txt")));
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(in.nextLine().split(",")));
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (String s : list){
            intList.add(Integer.parseInt(s));
        }
        IntcodeComp.run(intList, intList.get(1), intList.get(2));
    }
}
