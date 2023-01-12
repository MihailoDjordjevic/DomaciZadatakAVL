package view;

import lombok.Getter;
import lombok.Setter;
import model.NodeModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

@Getter
@Setter
public class NodeView<T extends NodeModel> extends JPanel {

    private T nodeModel;

    public NodeView(T nodeModel) {
        setBackground(Color.GRAY);
        setBorder(new LineBorder(Color.BLACK, 2));
        add(new JLabel(nodeModel.toString()));//"" + nodeModel.getValue()));
        this.nodeModel = nodeModel;
    }
}
