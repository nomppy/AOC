import java.util.*;
import java.io.*;

class Day6_new {
    public static void main(String[] args) throws IOException{
        Scanner aoc = new Scanner(new BufferedReader(new FileReader("data6.txt")));
        Scanner test = new Scanner(new BufferedReader(new FileReader("test.txt")));

        Root root = new Root();
        while(test.hasNextLine()){
            String orbit = test.nextLine();
            String child = orbit.split("\\)")[1];
            String parent = orbit.split("\\)")[0];

            Root c = root.get(child);
            Root p = root.get(parent);

            if (c == null && p == null){ // neither child nor parent are in tree
                new Root(parent, 0).addChild(child);
            }else if (c != null && p == null){ // child is in tree but parent is not
                new Root(parent, c.getHeight() - 1).addChild(c);
            }else if(root.get(child) == null && root.get(parent) != null){ // parent is in tree but child is not
                p.addChild(child);
            }else if(root.get(child) != null && root.get(parent) != null){ // both are in tree
                p.addChild(c);
            }
            root.updateAll();
        }
        System.out.println(root.subtree());
    }
}

class Root {
    private static int size = 0;
    private String name = "COM";
    private int height = 0;
    private ArrayList<Root> children = new ArrayList<>();
    private Root parent;

    // constructor

    public Root(){

    }

    public Root(Root node){
        this.name = node.getName();
        this.height = node.getHeight();
        this.children = node.getChildren();
        this.parent = node.getParent();
    }

    public Root(String name, Root parent){
        this.name = name;
        this.parent = parent;
        size ++;
    }

    public Root(String name, int height){
        this.name = name;
        this.parent = null;
        this.height = height;
        size = Math.max(size, height);
    }

    public int size(){
        return size;
    }

    public static void setSize(int size){
        Root.size = size;
    }

    public Root get(String name){
        // searches the entire tree for the desired node under this subtree

        if (name.equals(this.getName())) return this;
        ArrayList<Root> next;
        for (int i = this.getHeight() + 1; i <= size; i ++){
            next = this.getLayer(i);
            for (Root n : next) {
                if (n.getName().equals(name)) {
                    return n;
                }
            }
        }
        return null;
    }

    public ArrayList<Root> nextLayer(){
        return this.getLayer(this.getHeight() + 1);
    }

    public ArrayList<Root> getLayer(int layer){

        // returns an ArrayList<Root> indexed with this at 0
        // layer means a certain layer of descendants from this

        if (layer > size) System.out.println("Invalid layer number.");

        ArrayList<Root> nodes = new ArrayList<>();
        nodes.add(this);
        ArrayList<Root> tmp = new ArrayList<>();
        for (int i = 0; i < layer; i ++){
            for (Root node : nodes) {
                tmp.addAll(node.getChildren());
            }
            nodes.clear();
            nodes.addAll(tmp);
            tmp.clear();
        }
        return nodes;
    }

    public ArrayList<Root> getChildren(){
        return children;
    }

    public void updateAll(){

        // update relationships between all nodes in the current subtree
        for (Root n : children){
            n.update();
            n.updateAll();
        }
    }

    public void addChild(String name){
        children.add(new Root(name, this));
    }

    public void addChild(Root child){
        children.add(child);
        child.setParent(this);
    }

    public void update(){
        height = parent.getHeight() + 1;
    }



    public ArrayList<Root> subtree(){
        ArrayList<Root> tree = new ArrayList<>();
        for (int i = 0; i < size; i ++){
            tree.addAll(this.getLayer(i));
        }
        return tree;
    }

    public String toString(){
        try {
            return "Name: " + name + "  Height: " + height + "  Parent: " + parent.getName();
        }catch (Exception NullPointerException){
            return "Name: " + name + "  Height: " + height + "  Parent: " + "None";
        }
    }


    // getters and setters

    public String getName(){
        return name;
    }

    public Root getParent(){
        return parent;
    }

    public int getHeight(){
        return height;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setParent(Root parent){
        this.parent = parent;
    }

    public void setHeight(int height){
        this.height = height;
    }
}