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

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mainView.redraw();
            }
        });
        t.start();
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
