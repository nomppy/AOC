import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Day2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("d:\\code\\aoc\\data2.txt")));
//        Scanner in = new Scanner(System.in); for testing purposes

        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(in.next().split(",")));
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(String i : strList) {
            list.add(Integer.parseInt(i));
        }

        list.set(1, 12);
        list.set(2, 2);

        for(int i = 0; i < list.size(); i += 4) {
            if(list.get(i) == 1){
                list.set(list.get(i + 3), list.get(list.get(i + 1)) + list.get(list.get(i + 2)));
            }else if(list.get(i) == 2){
                list.set(list.get(i + 3), list.get(list.get(i + 1)) * list.get(list.get(i + 2)));
            }else if(list.get(i) == 99){
                break;
            }else{
                System.out.println("something went wrong");
            }
        }
        System.out.println(list);
    }
}
