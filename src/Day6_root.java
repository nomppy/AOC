import java.util.*;
import java.io.*;

class Day6_new {
    public static void main(String[] args) throws IOException{
        double s = System.currentTimeMillis();
        Scanner aoc = new Scanner(new BufferedReader(new FileReader("data6.txt")));
        Scanner test = new Scanner(new BufferedReader(new FileReader("test.txt")));

        Root root = new Root();
        while(aoc.hasNextLine()){
            // could also start at an arbitrary spot and traverse both ways and expand to the whole tree
            String orbit = aoc.nextLine();
            String child = orbit.split("\\)")[1];
            String parent = orbit.split("\\)")[0];

            Root c = root.get(child);
            Root p = root.get(parent);

            if (c == null && p == null){ // neither child nor parent are in tree
                root.addChild(parent);
                root.get(parent).addChild(child);
            }else if (c != null && p == null){ // child is in tree but parent is not
                root.addChild(new Root(parent, c.getHeight() - 1));
                root.get(parent).addChild(c);
            }else if(root.get(child) == null && root.get(parent) != null){ // parent is in tree but child is not
                p.addChild(child);
            }else if(root.get(child) != null && root.get(parent) != null){ // both are in tree
                p.addChild(c);
            }
            root.updateAll();
        }
        System.out.println(root.subtree());
        System.out.println(root.info());
        double e = System.currentTimeMillis();
        System.out.println("Completed in " + (e - s) + "ms");
//        Root.display(root);
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

    public static void display(Root root){
        // prints the tree in a human-readable format

        System.out.println("+--" + root.getName());
        int indents = 0;
        for (int i = root.getHeight() + 1; i < root.size(); i++){
            indents ++;
            ArrayList<Root> layer = root.getLayer(i);
            for (Root value : layer) {
                System.out.print("|");
                for (int k = 0; k < indents; k++) {
                    System.out.print("  ");
                }
                System.out.print("+--" + value.getName() + "\n");
            }
        }
    }

    public Root get(String name){
        // searches the subtree for the desired node
        // root.get() searches the entire tree

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

    private void removeChild(Root child){
        children.remove(child);
    }

    public void addChild(Root child){
        children.add(child);
        Root old = child.getParent();
        if (old != null && old.getChildren().indexOf(child) != -1){
            old.removeChild(child);
        }
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

    public String info(){
        return "Sub-layers: " + size + "\nSub-nodes: " + this.subtree().size();
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