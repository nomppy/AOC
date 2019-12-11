import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Day2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("d:\\code\\aoc\\data2.txt")));
//        Scanner in = new Scanner(System.in); for testing purposes

        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(in.next().split(",")));
        ArrayList<Integer> initialList = new ArrayList<Integer>();
        for(String i : strList) {
            initialList.add(Integer.parseInt(i));
        }

        for(int i = 0; i < 100; i ++){
            for(int j = 0; j < 100; j ++){
                int result = test(initialList, i, j);
                if (result == 19690720) {
                    System.out.println(i + "," + j);
                    System.out.println(100 * i + j);
                }
            }
        }
    }

    private static int test(ArrayList<Integer> initialList, int noun, int verb){
        ArrayList<Integer> list = new ArrayList<Integer>(initialList);
        list.set(1, noun);
        list.set(2, verb);
        for(int i = 0; i < list.size(); i += 4) {
            if(list.get(i) == 1){
//                list.set(list.get(i + 3), add(list, list.get(i + 1), list.get(i + 2)));
                list.set(list.get(i + 3), list.get(list.get(i + 1)) + list.get(list.get(i + 2)));
            }else if(list.get(i) == 2){
                list.set(list.get(i + 3), list.get(list.get(i + 1)) * list.get(list.get(i + 2)));
            }else if(list.get(i) == 99){
                break;
            }else{
                System.out.println("something went wrong");
                break;
            }
        }
        return list.get(0);
    }

    public static int add(ArrayList<Integer> list, int pos1, int pos2) {
        return list.get(pos1) + list.get(pos2);
    }

    public static int multiply(ArrayList<Integer> list, int pos1, int pos2) {
        return list.get(pos1) * list.get(pos2);
    }
}
