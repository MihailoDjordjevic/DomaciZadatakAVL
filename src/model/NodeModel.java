package model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class NodeModel {

    private int value;

    private NodeModel left;
    private NodeModel right;

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

    }

    public static String printTree(NodeModel root){

        if (root == null) return " null ";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(printTree(root.left));
        stringBuilder.append(" " + root.getValue() + " ");
        stringBuilder.append(printTree(root.right));

        return stringBuilder.toString();

    }

    public int getDepth(NodeModel root, int max, int curr){

        if (root == null) return curr;

        if (++curr > max) max = curr;

        return Math.max(getDepth(root.getLeft(), max, curr), getDepth(root.getRight(), max, curr));
    }

    public int getDepth(){
        return getDepth(this, 0, 0);
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
