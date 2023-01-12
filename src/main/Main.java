package main;

import controler.CalculatePosition;
import model.NodeModel;
import view.MainView;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        NodeModel nodeModel = new NodeModel(6);

//        for (int i = 0; i < 7; i++){
//
//            int delta = 256;
//            int count = (int) Math.pow(2, i+1);
//
//            for (int j = 1; j <= count; j++){
//                nodeModel.addChild(nodeModel, new NodeModel(   delta/(count) + delta/(count)*(j-1)*2      ));
//            }
//        }

//        NodeModel nodeModel = new NodeModel(500);
//
//        int[] arr = new int[128];
//
//        for(int i = 0; i < 128; i++)
//            arr[i] = new Random().nextInt(1000);
//
//       // Arrays.sort(arr);
//
//        for (int i = 0; i < 50; i++){
//            NodeModel nodeModel1 = new NodeModel(arr[i]);
//            nodeModel.addChild(nodeModel, nodeModel1);
//        }

        System.out.println(NodeModel.printTree(nodeModel));

        System.out.println(nodeModel.getDepth());

        MainView mainView = MainView.getInstance();
        mainView.setRoot(nodeModel);

        CalculatePosition calculatePosition = new CalculatePosition(nodeModel, mainView);
        calculatePosition.printNodes();

    }

}
