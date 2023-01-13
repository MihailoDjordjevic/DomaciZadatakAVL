package main;

import model.NodeModel;

import java.util.Scanner;

public class Main {

    private static NodeModel rootNode;

    public static void main(String[] args) {


     //   NodeModel nodeModel = new NodeModel(6);

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
//
//        System.out.println(NodeModel.printTree(nodeModel));
//
//        System.out.println(nodeModel.getDepth());
//
//        MainView mainView = MainView.getInstance();
//        mainView.setRoot(nodeModel);
//
//        CalculatePosition calculatePosition = new CalculatePosition(nodeModel, mainView);
//        calculatePosition.printNodes();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter root value ");

        int input = 1;

        rootNode = new NodeModel(scanner.nextInt());
        rootNode.printConsoleTree(rootNode, 0);

        while (true){

            System.out.println("Enter a value to insert to a tree, press -1 to delete value, press 0 to exit");

            input = scanner.nextInt();

            if (input == 0) break;
            if (input == -1){
                System.out.println("Enter the value to be deleted");
                input = scanner.nextInt();

                rootNode.deleteChild(rootNode, input);
                rootNode.printConsoleTree(rootNode, 0);
                continue;
            }

            rootNode.addChild(rootNode, new NodeModel(input));

            rootNode.printConsoleTree(rootNode, 0);

            if (NodeModel.unbalancedNode != null){
                balanceTree();
            }

        }

        scanner.close();

    }

    public static void balanceTree(){

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

    public static void rightRotation(NodeModel root){

        NodeModel parent = root.getParent();
        NodeModel left = root.getLeft();

        if (parent == null){
            left.setParent(null);
            rootNode = left;
        } else if (parent.getLeft() == root)
            parent.setLeft(left);
        else parent.setRight(left);

        NodeModel tmp = left.getRight();
        root.setLeft(tmp);
        left.setRight(root);

        NodeModel.unbalancedNode = null;

        System.out.println("-----------------------------------------------------------------------------");
        rootNode.printConsoleTree(rootNode, 0);

     //   MainView.getInstance().getHolderPanel().removeAll();
    //    MainView.getInstance().getHolderPanel().getPoints().clear();

      //  SwingUtilities.updateComponentTreeUI(MainView.getInstance());

     //   MainView.getInstance().redraw();


    }

    public static void leftRotation(NodeModel root){

        NodeModel parent = root.getParent();
        NodeModel right = root.getRight();

        if (parent == null){
            right.setParent(null);
            rootNode = right;
        } else if (parent.getLeft() == root)
            parent.setLeft(right);
        else parent.setRight(right);

        NodeModel tmp = right.getLeft();
        root.setRight(tmp);
        right.setLeft(root);

        NodeModel.unbalancedNode = null;

        System.out.println("-----------------------------------------------------------------------------");
        rootNode.printConsoleTree(rootNode, 0);

     //   MainView.getInstance().getHolderPanel().removeAll();
     //   MainView.getInstance().getHolderPanel().getPoints().clear();

    //    SwingUtilities.updateComponentTreeUI(MainView.getInstance());

    //    MainView.getInstance().redraw();

    }

}
