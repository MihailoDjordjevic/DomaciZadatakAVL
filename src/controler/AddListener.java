package controler;

import main.Main;
import model.NodeModel;
import org.w3c.dom.Node;
import view.MainView;
import view.NodeView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        MainView mainView = MainView.getInstance();

        Integer number = null;
        try {
            number = Integer.valueOf(mainView.getTextField().getText());
        } catch (NumberFormatException e1) {
            return;
        }

        NodeView<NodeModel> nodeView = new NodeView<>(new NodeModel(number));

        mainView.getHolderPanel().removeAll();
        mainView.getHolderPanel().getPoints().clear();

        SwingUtilities.updateComponentTreeUI(mainView);

        if (mainView.getRoot() == null) mainView.setRoot(nodeView.getNodeModel());
        else mainView.getRoot().addChild(mainView.getRoot(), nodeView.getNodeModel());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mainView.redraw();

                if (NodeModel.unbalancedNode != null){
                    balanceTree();
                }
            }
        });
        t.start();

        System.out.println(NodeModel.printTree(mainView.getRoot()));
        return;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void balanceTree(){

        if (NodeModel.unbalancedNode.getBalance() == 2){
            if (NodeModel.unbalancedNode.getLeft() != null && NodeModel.unbalancedNode.getLeft().getBalance() == 1){
                rightRotation(NodeModel.unbalancedNode);
            } else if (NodeModel.unbalancedNode.getLeft() != null && NodeModel.unbalancedNode.getLeft().getBalance() == -1){
                NodeModel tmp = NodeModel.unbalancedNode;
                leftRotation(NodeModel.unbalancedNode.getLeft());
                rightRotation(tmp);
            }
        } else if (NodeModel.unbalancedNode.getBalance() == -2){
            if (NodeModel.unbalancedNode.getRight() != null && NodeModel.unbalancedNode.getRight().getBalance() == -1){
                leftRotation(NodeModel.unbalancedNode);
            } else if (NodeModel.unbalancedNode.getRight() != null && NodeModel.unbalancedNode.getRight().getBalance() == 1){
                NodeModel tmp = NodeModel.unbalancedNode;
                rightRotation(NodeModel.unbalancedNode.getRight());
                leftRotation(tmp);
            }
        }

    }

    public void rightRotation(NodeModel root){

        NodeModel parent = root.getParent();
        NodeModel left = root.getLeft();

        if (parent == null){
            left.setParent(null);
            MainView.getInstance().setRoot(left);
        } else if (parent.getLeft() == root)
            parent.setLeft(left);
        else parent.setRight(left);

        NodeModel tmp = left.getRight();
        root.setLeft(tmp);
        left.setRight(root);

        NodeModel.unbalancedNode = null;

        MainView.getInstance().getHolderPanel().removeAll();
        MainView.getInstance().getHolderPanel().getPoints().clear();

        SwingUtilities.updateComponentTreeUI(MainView.getInstance());

        MainView.getInstance().redraw();
    }

    public void leftRotation(NodeModel root){

        NodeModel parent = root.getParent();
        NodeModel right = root.getRight();

        if (parent == null){
            right.setParent(null);
            MainView.getInstance().setRoot(right);
        } else if (parent.getLeft() == root)
            parent.setLeft(right);
        else parent.setRight(right);

        NodeModel tmp = right.getLeft();
        root.setRight(tmp);
        right.setLeft(root);

        NodeModel.unbalancedNode = null;

        MainView.getInstance().getHolderPanel().removeAll();
        MainView.getInstance().getHolderPanel().getPoints().clear();

        SwingUtilities.updateComponentTreeUI(MainView.getInstance());

        MainView.getInstance().redraw();

    }
}
