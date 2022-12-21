package view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class HolderFrame extends JPanel {

    private ArrayList<Point> points;

    public HolderFrame(LayoutManager layout) {
        super(layout);
        points = new ArrayList<>();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 2; i < points.size(); i += 2){


            g.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);

        }

    }
}
