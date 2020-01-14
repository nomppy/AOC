import java.util.ArrayList;

class Day6_new {
    public static void main(String[] args) {

    }
}

class Root {
    private static int size = 0;
    private String name = "COM";
    private int height = 0;
    private ArrayList<Root> children = new ArrayList<>();
    private Root parent;

    // constructor

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
        ArrayList<Root> next = this.getChildren();
        for (int i = 0; i < size; i ++){

        }
        System.out.println("Could not find node " + name);
        return null;
    }

    public ArrayList<Root> getLayer(int layer){
        if (layer > size) System.out.println("Invalid layer number.");

        ArrayList<Root> nodes = new ArrayList<>();
        nodes.add(this);
        for (int i = 0; i < layer; i ++){
            for (int j = 0; j < nodes.size(); j ++){
                nodes.addAll(nodes.get(j).getChildren());
                nodes.remove(nodes.get(j));
            }
        }
        return nodes;
    }

    public ArrayList<Root> getChildren(){
        return children;
    }

    public void updateChildren(){
        for (Root n : children){
            n.update();
            n.updateChildren();
        }
    }

    public void addChild(Root child){
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