package controler;

import lombok.Getter;
import lombok.Setter;
import model.NodeModel;
import view.MainView;
import view.NodeView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Setter
@Getter
public class CalculatePosition {

    private int segmentHeight;
    private ArrayList<Integer> width = new ArrayList<>();
    int depth;
    int frameWidth = 1400;
    int frameHeight = 800;
    private NodeModel root;
    private MainView mainView;
    public static int dTime = 100;

    public CalculatePosition(NodeModel root, MainView mainView) {
        this.root = root;
        this.mainView = mainView;
        calculateGrid(this.root);
        calculateWidthOfEverySegment();
    }

    public void calculateGrid(NodeModel root){

        depth = root.getDepth();
        segmentHeight = 25;
        frameWidth = (int) Math.max(1200, Math.pow(2, depth-1)*35);
        frameHeight = Math.max(800, 35*depth);
        mainView.getHolderPanel().setPreferredSize(new Dimension(frameWidth, frameHeight));
    }

    private void calculateWidthOfEverySegment(){

        for (int i = 1; i <= depth; i++){

            width.add((int) (frameWidth/Math.pow(2.0, i-1)));

        }


    }

    public void printNodes(Point p, NodeModel nodeModel, int currDepth, int leftBound, int rightBound){

        if (nodeModel == null) return;

        int posX = (rightBound+leftBound)/2 - 14 ;
        int posY = (currDepth-1)*55;
        Point point = new Point(posX+14, posY+12);
        mainView.getHolderPanel().getPoints().add(point);
        mainView.getHolderPanel().getPoints().add(p);

        //pre order
        printNodes(point, nodeModel.getLeft(), currDepth+1, leftBound,  (rightBound+leftBound)/2);
        printNodes(point, nodeModel.getRight(), currDepth+1,  (rightBound+leftBound)/2, rightBound);

        {
            NodeView<NodeModel> nodeView = new NodeView<>(nodeModel);
            mainView.getHolderPanel().add(nodeView);

            mainView.getScrollHolder().repaint(posX - 5, posY - 5, 90, 90);
            SwingUtilities.updateComponentTreeUI(nodeView);

            nodeView.setSize(segmentHeight + 3, segmentHeight);
            nodeView.setLocation(posX, posY);
        }

        try {
            Thread.sleep(dTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //post order
//        printNodes(point, nodeModel.getLeft(), currDepth+1, leftBound,  (rightBound+leftBound)/2);
//        printNodes(point, nodeModel.getRight(), currDepth+1,  (rightBound+leftBound)/2, rightBound);

    }

    public void printNodes(){
        this.printNodes(new Point(0,0), this.root, 1, 0, frameWidth);
    }

}
