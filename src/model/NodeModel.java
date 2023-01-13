package model;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Node;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class NodeModel {

    private int value;
    private int balance = 0;

    private NodeModel parent = null;

    private NodeModel left;
    private NodeModel right;

    public static NodeModel unbalancedNode = null;

    public NodeModel(int value) {
        this.value = value;
    }

    public void addChild(NodeModel root, NodeModel nodeModel){

        if(nodeModel.getValue() < root.getValue()){

            if (root.getLeft() == null)
                root.setLeft(nodeModel);
            else
                addChild(root.getLeft(), nodeModel);

        } else if(nodeModel.getValue() > root.getValue()){

            if (root.getRight() == null)
                root.setRight(nodeModel);
             else
                addChild(root.getRight(), nodeModel);
        }

        root.balance = root.getDepth(root.getLeft(), 0, 0) - root.getDepth(root.getRight(), 0, 0);
        if (unbalancedNode == null && (root.balance == 2 || root.balance == -2)) unbalancedNode = root;

    }

    public void deleteChild(NodeModel root, int value){
        if (root == null)  return;

        if (root.getRight() != null && root.getRight().getValue() == value) {
            root.setRight(null);
        }
        else if (root.getLeft() != null && root.getLeft().getValue() == value) {
            root.setLeft(null);
        }
        else if (value < root.getValue())
            deleteChild(root.getLeft(), value);
        else deleteChild(root.getRight(), value);

    }

    public static String printTree(NodeModel root){

        if (root == null) return " null ";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(printTree(root.left));
        stringBuilder.append(" value: " + root.getValue() + " balance: " + root.balance + " ");
        stringBuilder.append(printTree(root.right));

        return stringBuilder.toString();

    }

    public void printConsoleTree(NodeModel root, int depth){

        if (root == null){
            for (int i = 0; i < depth-1; i++){
                System.out.print('\t');
            }
            System.out.println("\t #");
            return;
        }

        printConsoleTree(root.getRight(), depth+1);

        for (int i = 0; i < depth; i++){
            System.out.print('\t');
        }
        System.out.println(root + "---");

        printConsoleTree(root.getLeft(), depth+1);

    }

    public int getDepth(NodeModel root, int max, int curr){

        if (root == null) return curr;

        if (++curr > max) max = curr;

        return Math.max(getDepth(root.getLeft(), max, curr), getDepth(root.getRight(), max, curr));
    }

    public void setLeft(NodeModel nodeModel){
        this.left = nodeModel;
        if (nodeModel != null) nodeModel.setParent(this);
    }
    public void setRight(NodeModel nodeModel){
        this.right = nodeModel;
        if (nodeModel != null) nodeModel.setParent(this);
    }

    public int setBalance(NodeModel root){

        return 0;
    }

    private int findReplacement(NodeModel root){

        if (root.getLeft() == null && root.getRight() == null) return root.getValue();
        return 0;

    }


    public int getDepth(){
        return getDepth(this, 0, 0);
    }
    @Override
    public String toString() {
        return value+"";// + "(" + balance + ")";
    }
}
