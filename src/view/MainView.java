package view;

import controler.AddListener;
import controler.CalculatePosition;
import lombok.Getter;
import lombok.Setter;
import model.NodeModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseListener;

@Getter
@Setter
public class MainView extends JFrame {

    private HolderFrame holderPanel;
    private JScrollPane scrollHolder;
    private JButton add;
    private JTextField textField;
    private NodeModel root = null;
    private JSlider speedSlider;

    private static MainView mainView;

    public MainView(String title) {
        super(title);
        setLocation(100, 20);
        setSize(new Dimension(1400, 800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        init();
        initListeners();

        setVisible(true);
        System.out.println("set");
    }

    private void init(){

        holderPanel = new HolderFrame(null);
        holderPanel.setPreferredSize(new Dimension(1200,800));
        holderPanel.setBackground(Color.PINK);

        scrollHolder = new JScrollPane(holderPanel);
        scrollHolder.setPreferredSize(new Dimension(1400, 900));
        scrollHolder.setAutoscrolls(true);

        add(BorderLayout.CENTER, scrollHolder);

        add = new JButton("Add");
        add.setSize(30,30);

        textField = new JTextField();
        textField.setSize(120, 30);
        textField.setEditable(true);

        speedSlider = new JSlider(0,1500);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setPreferredSize(new Dimension(300, 50));
        buttons.add(add);
        buttons.add(textField);
        buttons.add(speedSlider);
        add(BorderLayout.NORTH, buttons);

    }

    private void initListeners(){
        add.addMouseListener(new AddListener());
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                CalculatePosition.dTime = speedSlider.getValue();
            }
        });
    }

    public static MainView getInstance(){

        if (mainView == null){
            mainView = new MainView("Tree viewer");
            return mainView;
        }
        else return mainView;
    }

    public void redraw(){
        MainView mainView = MainView.getInstance();


        CalculatePosition calculatePosition = new CalculatePosition(mainView.getRoot(), mainView);
        calculatePosition.printNodes();
    }

}
