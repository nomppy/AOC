import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Day1 {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader("data.txt")));
        Scanner inp = new Scanner(System.in);
//        TODO Auto-generated code
        int fuel = 0;
       while(in.hasNextInt()){
           fuel += calculateFuel(in.nextInt());
       }
        System.out.println(fuel);

//        while(true){
//            int fuel = calculateFuel(inp.nextInt());
//            System.out.println(fuel);
//        }

    }

    public static int calculateFuel(int mass){
        int fuel = (mass / 3) - 2;
        int aux = 0;
        if((fuel/3)-2 > 0) {
            aux = calculateFuel(fuel);
        }
//        while(aux > 0) {
//            fuel += aux;
//            aux = calculateFuel(aux);
//        }
        return (fuel + aux);

    }

}