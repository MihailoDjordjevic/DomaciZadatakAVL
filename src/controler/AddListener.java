package controler;

import model.NodeModel;
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
}
