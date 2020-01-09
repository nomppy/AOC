import java.io.*;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        Scanner aoc = new Scanner(new BufferedReader(new FileReader("data5.txt")));
        Scanner test = new Scanner(System.in); // for testing the computer with small inputs

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(aoc.nextLine().split(",")));
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (String s : list){
            intList.add(Integer.parseInt(s));
        }
        IntcodeComp.run(intList, intList.get(1), intList.get(2));
    }
}
