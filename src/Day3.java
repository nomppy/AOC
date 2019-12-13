import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("data3.txt"));
        String[] line1 = in.next().split(",");
        String[] line2 = in.next().split(",");
        in.close();

        Character[] dir1 = new Character[line1.length];
        Integer[] dis1= new Integer[line1.length];
        Character[] dir2 = new Character[line2.length];
        Integer[] dis2= new Integer[line1.length];


        Position[] tracker1 = new Position[line1.length + 1];
        tracker1[0] = new Position();
        for (int i = 0; i < line1.length; i++){
            dir1[i] = line1[i].charAt(0);
            dis1[i] = Integer.parseInt(line1[i].substring(1));
            tracker1[i + 1] = Position.move(tracker1[i], dir1[i], dis1[i]);
            System.out.print(tracker1[i].toString() + " ");
        }

        Position[] tracker2 = new Position[line2.length + 1];
        tracker2[0] = new Position();
        System.out.println();
        for (int i = 0; i < line2.length; i++){
            dir2[i] = line2[i].charAt(0);
            dis2[i] = Integer.parseInt(line2[i].substring(1));
            tracker2[i + 1] = Position.move(tracker2[i], dir2[i], dis2[i]);
            System.out.print(tracker2[i].toString() + " ");
        }

        ArrayList<Position> intersections = new ArrayList<Position>();
        for (int i = 0; i < tracker1.length; i++) {
            if (tracker1[i].equals(tracker2[i])){
                intersections.add(tracker1[i]);
            }
        }
        System.out.println(intersections);



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
                case 'U':
                    y += distance;
                case 'D':
                    y -= distance;
            }
            return new Position(x, y);
        }

        public boolean equals(Position pos1, Position pos2) {
            return pos1.x == pos2.x && pos1.y == pos2.y;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
