//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//public class Day3 {
//    public static void main(String[] args) throws IOException{
//        System.out.println("Part 1: " + run(1));
////        System.out.println("Part 2: " + run(2));
//        System.out.println(Integer.MAX_VALUE);
//
//    }
//    static int x = 0, y = 0, delay = 0;
//
//    public static int run(int part) throws IOException{
//
//        Scanner in = new Scanner(new FileReader("data3.txt"));
//
//        String[] line1 = in.next().split(",");
//
//        String[] line2 = in.next().split(",");
//        in.close();
//
//        int dis1;
//        char dir1;
//        int dis2;
//        char dir2;
//
//        Map<Position, Integer> tracker1 = new HashMap<Position, Integer>();
//        Map<Position, Integer> tracker2 = new HashMap<Position, Integer>();
//
//        for (String s : line1) {
//            dir1 = s.charAt(0);
//            dis1 = Integer.parseInt(s.substring(1));
////            System.out.println(dir1 + " " + dis1);
//            for (int j = 0; j < dis1; j++) {
//                delay ++;
//                tracker1.put(move(dir1), delay);
////                System.out.print(tracker1.get(new Position(x, y)) + ",");
////                System.out.println(tracker1.get(new Position(x, y)));
//            }
//        }
//
//        x = 0; y = 0; delay = 0;
//
//        for (String s : line2) {
////            System.out.println();
//            dir2 = s.charAt(0);
//            dis2 = Integer.parseInt(s.substring(1));
//            for (int j = 0; j < dis2; j++) {
//                delay ++;
//                tracker1.put(move(dir2), delay);
//            }
////            System.out.println();
//        }
//
//
////        System.out.println(tracker1.keySet());
//        tracker1.keySet().retainAll(tracker2.keySet());
//        int min = Integer.MAX_VALUE;
////        System.out.println(tracker1.keySet());
//        for(Position p : tracker1.keySet()) {
//            if (part == 1)
//                min = Math.min(min, Math.abs(p.x) + Math.abs(p.y));
//            else
//                min = Math.min(min, tracker1.get(p) + tracker2.get(p));
//        }
//
//        return min;
//    }
//
//    public static Position move(char direction) {
//        switch (direction) {
//            case 'L':
//                x --;
//                break;
//            case 'R':
//                x ++;
//                break;
//            case 'U':
//                y ++;
//                break;
//            case 'D':
//                y --;
//                break;
//        }
//        return new Position(x, y);
//    }
//
//    public static class Position {
//        private int x, y;
//
//        public Position(int x, int y){
//            this.x = x;
//            this.y = y;
//        }
//
//        public String toString() {
//            return "(" + this.x + "," + this.y + ")";
//        }
//    }
//
//
//}
import java.io.*;
import java.util.*;

public class Day3 {

    public static void main(String[] args) throws IOException {
        System.out.println("Part 1: " + run(1));
        System.out.println("Part 2: " + run(2));
    }

    private static int run(int part) throws FileNotFoundException {
        List<Map<Point, Integer>> points = Arrays.asList(new HashMap<>(), new HashMap<>());

        Scanner in = new Scanner(new BufferedReader(new FileReader("data3.txt")));
        List<String> wires = new ArrayList<>();
        wires.add(in.nextLine());
        wires.add(in.nextLine());

        for (int wire = 0; wire < wires.size(); wire++) {
            int x = 0, y = 0, time = 0;
            for (String move : wires.get(wire).split(",")) {
                char dir = move.charAt(0);
                int num = Integer.parseInt(move.substring(1));
                for (int i = 0; i < num; i++) {
                    time++;
                    if (dir == 'R')
                        x++;
                    else if (dir == 'L')
                        x--;
                    else if (dir == 'U')
                        y++;
                    else if (dir == 'D')
                        y--;
                    else
                        throw new RuntimeException();
                    points.get(wire).put(new Point(x, y), time);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        points.get(0).keySet().retainAll(points.get(1).keySet());
        for (Point p : points.get(0).keySet())
            if (part == 1)
                min = Math.min(min, Math.abs(p.x)+Math.abs(p.y));
            else
                min = Math.min(min, points.get(0).get(p)+points.get(1).get(p));
        return min;
    }

    public static class Point {
        public int x, y; public Point(int x, int y) { this.x = x; this.y = y; }
        public int getX() { return x; } public void setX(int x) { this.x = x; }
        public int getY() { return y; } public void setY(int y) { this.y = y; }
        @Override public int hashCode() { final int prime = 7759; int result = 1;
            result = prime * result + x; result = prime * result + y; return result; }
        @Override public boolean equals(Object obj) { if (this == obj) return true;
            if (obj == null) return false; if (getClass() != obj.getClass()) return false;
            Point other = (Point) obj; if (x != other.x) return false;
            if (y != other.y) return false; return true; }
        @Override public String toString() { return "(" + x + ", " + y + ")"; } }

}