import java.util.*;
import java.io.*;


class Day6 {

    static Map<String, Node> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        double startTime = System.currentTimeMillis();
        Scanner aoc = new Scanner(new BufferedReader(new FileReader("data6.txt")));
        Scanner test = new Scanner(new BufferedReader(new FileReader("test.txt"))); // for testing with small inputs

        tree.put("COM", new Node("COM", 0));
        while(aoc.hasNextLine()) {
            String orbit = aoc.nextLine();
            String child = orbit.split("\\)")[1];
            String parent = orbit.split("\\)")[0];

            Node c;
            Node p;
            if (tree.get(child) != null && tree.get(parent) == null){
                // if child is already in tree but parent isn't
                c = tree.get(child);
                p = new Node(parent, c.getHeight() - 1);
                c.setParent(p);
                p.addChild(c);
            }else if (tree.get(child) == null && tree.get(parent) != null){
                // if parent is already in tree but child isn't
                p = tree.get(parent);
                c = new Node(child, p);
                p.addChild(c);
            }else if (tree.get(child) != null && tree.get(parent) != null){
                // both child and parent are in tree
                p = tree.get(parent);
                c = tree.get(child);
                p.addChild(c);
                c.setParent(p);
            }else{
                // neither are in tree
                p = new Node(parent, 0);
                c = new Node(child, p);
                p.addChild(c);
            }
            tree.put(child, c);
            tree.put(parent, p);
            p.updateChildren();
        }

        Node root = tree.get("COM");
        System.out.println(tree);

        int orbits = 0;
        for (Node a : tree.values()){
            for (Node o : tree.values()){
                if (hasAncestor(o, a)) orbits ++;
            }
        }
        System.out.println(orbits);
        double endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");
    }

    static boolean hasAncestor(Node offspring, Node ancestor){
        Node next = offspring.getParent();
        for (int i = 0; i < offspring.getHeight() - ancestor.getHeight(); i++) {
            if (next.getName().equals(ancestor.getName())) return true;
            next = next.getParent();
        }
        return false;
    }

}

class Node{

    // there's a very big oopsie with this class in that the COM node actually contains the entire tree...
    private String name;
    private int height;
    private Node parent;
    private ArrayList<Node> children = new ArrayList<Node>();

    // constructor

    public Node(String name, Node parent){
        this.name = name;
        this.parent = parent;
    }

    public Node(String name, int height){
        this.name = name;
        this.parent = null;
        this.height = height;
    }

    public void updateChildren(){
        for (Node n : children){
            n.update();
            n.updateChildren();
        }
    }

    public void addChild(Node child){
        children.add(child);
    }

    public void update(){
        height = parent.getHeight() + 1;
    }

    public String toString(){
        try {
            return "Name: " + name + "  Height: " + height + "  Parent: " + parent.getName();
        }catch (Exception NullPointerException){
            return "Name: " + name + "  Height: " + height + "  Parent: " + "None";        }
    }


    // getters and setters

    public String getName(){
        return name;
    }

    public Node getParent(){
        return parent;
    }

    public int getHeight(){
        return height;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void setHeight(int height){
        this.height = height;
    }
}