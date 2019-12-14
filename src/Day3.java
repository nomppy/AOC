import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("data3full.txt"));

        String[] line1 = in.next().split(",");
        String[] line2 = in.next().split(",");
        in.close();

        int dis1;
        char dir1;
        int dis2;
        char dir2;

        ArrayList<Position> tracker1 = new ArrayList<>();
        ArrayList<Position> tracker2= new ArrayList<>();
        tracker1.add(new Position());
        tracker2.add(new Position());

        for (String s : line1) {
            dir1 = s.charAt(0);
            dis1 = Integer.parseInt(s.substring(1));
            for (int j = 0; j < dis1; j++) {
                tracker1.add(Position.move(tracker1.get(tracker1.size() - 1), dir1, 1));
//                System.out.print(tracker1.get(tracker1.size() - 1).toString() + " ");
            }
        }

        for (String s : line2) {
//            System.out.println();
            dir2 = s.charAt(0);
            dis2 = Integer.parseInt(s.substring(1));
            for (int j = 0; j < dis2; j++) {
                tracker2.add(Position.move(tracker2.get(tracker2.size() - 1), dir2, 1));
//                System.out.print(tracker2.get(tracker2.size() - 1).toString() + " ");
            }
//            System.out.println();
        }

        System.out.println();

        ArrayList<Position> intersections = new ArrayList<Position>();
        for (Position value : tracker1) {
            for (Position position : tracker2) {
                if (value.equals(position)) {
                    intersections.add(value);
                }
            }
        }
        System.out.println(intersections);

        int min = 0;
        for(Position p : intersections){
            min = Math.min(min, manhattanDistance(p));
        }


    }


    public static int manhattanDistance(Position pos){
        return pos.x + pos.y;
    }

    public static class Position {
        private int x, y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Position(){
            x = 0;
            y = 0;
        }

        public static Position move(Position current, Character direction, int distance) {
            int x = current.x;
            int y = current.y;
            switch (direction) {
                case 'L':
                    x -= distance;
                    break;
                case 'R':
                    x += distance;
                    break;
                case 'U':
                    y += distance;
                    break;
                case 'D':
                    y -= distance;
                    break;
            }
            return new Position(x, y);
        }

        public boolean equals(Position pos) {
            return this.x == pos.x && this.y == pos.y;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
