import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws IOException{
        System.out.println("Part 1: " + run(1));
        System.out.println("Part 2: " + run(2));
        System.out.println(Integer.MAX_VALUE);

    }

    public static int run(int part) throws IOException{
        Scanner in = new Scanner(new FileReader("data3.txt"));

        String[] line1 = in.next().split(",");
        String[] line2 = in.next().split(",");
        in.close();

        int dis1;
        char dir1;
        int dis2;
        char dir2;

        Map<Position, Integer> tracker1 = new HashMap<Position, Integer>();
        Map<Position, Integer> tracker2 = new HashMap<Position, Integer>();

        int x = 0, y = 0, delay = 0;
        for (String s : line1) {
            dir1 = s.charAt(0);
            dis1 = Integer.parseInt(s.substring(1));
            for (int j = 0; j < dis1; j++) {
                delay ++;
                tracker1.put(Position.move(x, y, dir1), delay);
//                System.out.print(tracker1.get(tracker1.size() - 1).toString() + " ");
            }
        }

        x = 0; y = 0; delay = 0;
        for (String s : line2) {
//            System.out.println();
            dir2 = s.charAt(0);
            dis2 = Integer.parseInt(s.substring(1));
            for (int j = 0; j < dis2; j++) {
                delay ++;
                tracker1.put(Position.move(x, y, dir2), delay);
//                System.out.print(tracker2.get(tracker2.size() - 1).toString() + " ");
            }
//            System.out.println();
        }

        System.out.println();

        tracker1.keySet().retainAll(tracker2.keySet());
        int min = Integer.MAX_VALUE;
        for(Position p : tracker1.keySet()) {
            if (part == 1)
                min = Math.min(min, Math.abs(p.x) + Math.abs(p.y));
            else
                min = Math.min(min, tracker1.get(p) + tracker2.get(p));
        }

        return min;
    }

    public static class Position {
        private int x, y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        public static Position move(int x, int y, Character direction) {
            switch (direction) {
                case 'L':
                    x --;
                    break;
                case 'R':
                    x ++;
                    break;
                case 'U':
                    y ++;
                    break;
                case 'D':
                    y --;
                    break;
            }
            return new Position(x, y);
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
