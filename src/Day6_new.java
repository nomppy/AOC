import java.util.ArrayList;

class Day6_new {
    public static void main(String[] args) {

    }
}

class TreeNode{
    private String name = "COM";
    private int height = 0;
    private ArrayList<TreeNode> children = new ArrayList<>();
    private TreeNode parent;

    // constructor

    public TreeNode(String name, TreeNode parent){
        this.name = name;
        this.parent = parent;
    }

    public TreeNode(String name, int height){
        this.name = name;
        this.parent = null;
        this.height = height;
    }

    public void updateChildren(){
        for (TreeNode n : children){
            n.update();
            n.updateChildren();
        }
    }

    public void addChild(TreeNode child){
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

    public TreeNode getParent(){
        return parent;
    }

    public int getHeight(){
        return height;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setParent(TreeNode parent){
        this.parent = parent;
    }

    public void setHeight(int height){
        this.height = height;
    }
}