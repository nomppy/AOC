import java.io.*;
import java.util.*;

public class Day6 {
    static ArrayList<Node> forest = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        /*
        TODO
        NOTHING MUCH JUST CHANGE THE ENTIRE FUCKING STRUCTURE OF THE CODE
        ITS NOTHING, REALLY, ALL I GOTTA DO IS, UH, TURN FOREST INTO A WHOLE FUCKING MAP, AYE?
        NOT MUCH WORK AT ALL, AND DEFINITELY THE MOST FUCKING EFFICIENT WAY OF DOING IT, AYE?
        CODING WAS EASY, THEY SAID
        JUST KIDDING NOBODY FUCKING SAID THAT
        */
        Scanner aoc = new Scanner(new BufferedReader(new FileReader("data6.txt")));
        Scanner test = new Scanner(new BufferedReader(new FileReader("test.txt"))); // for testing with small inputs

        forest.add(new Node("COM"));
        while(test.hasNextLine()){
            String orbit = test.nextLine();
            forest.add(new Node(orbit.split("\\)")[1], orbit.split("\\)")[0]));
        }
        System.out.println(forest);



        Map<String, Node> tree = new HashMap<>();


        for (int i = 0; i < forest.size(); i ++){
//            if (forest.get(i).getParent().getName().equals("COM"))
            for (int j = 0; j < forest.size(); j ++){
                if (forest.get(i).getParent().equals(forest.get(j))) {
                    // j is parent, i is child
                    Node parent = forest.get(j);
                    Node child = forest.get(i);
                    parent.addChild(child.getName());
                    tree.put(parent.getName(), parent);
                    tree.put(child.getName(), child);
                    j = forest.size();
                }
            }
        }
        System.out.println(tree);

        int orbits = 0;

        for (Node i : tree.values()) {
            for (Node j : tree.values()) {
                if (i.hasAncestor(j)) orbits++;
            }
        }

        System.out.println("Direct orbits: " + (tree.size()));
        System.out.println("Indirect orbits: " + (orbits - tree.size()));
        System.out.println("Total orbits: " + (orbits));
    }

    static class Node {

        private int height;
        private String name;
        private String parent;
        private ArrayList<String> children = new ArrayList<>();

        public Node(String name, String parent, int height){
            this.name = name;
            this.height = height;
            this.parent = parent;
        }

        public Node(String name){
            this.name = name;
            height = 1;
            parent = new Node("null", 0, null);
            if (name.equals("COM")){
                height = 0;
            }
//            for (Node node : forest) {
//                if (node.getName().equals(name)) {
//                    height = node.getHeight();
//                    parent = node.getParent();
//                }
//            }
        }

        public String getParent(){
            return parent;
        }

        public String getName(){
            return name;
        }

        public int getHeight(){
            return height;
        }

        public ArrayList<String> getChildren(){
            return this.children;
        }

        public String toString(){
            return "Name: " + name + " Height: " + height + " Parent: " + parent;
        }

        public void setName(String name){
            this.name = name;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public void addChild(String child){
//            Node newNode = new Node(child, this.getHeight() + 1, this);
            this.children.add(child);
        }

        public boolean equals(Node node) {
            return this.getName().equals(node.getName());
        }

        public boolean isChildOf(Node parent){
            return this.getParent().equals(parent.getName());
        }

        public boolean hasAncestor(Node ancestor){
            Node node = this;
            for (int i = 0; i < this.getHeight() - ancestor.getHeight(); i++) {
                node = node.getParent();
                if (node.equals(ancestor)){
                    return true;
                }
            }
            return false;
        }
    }
}
