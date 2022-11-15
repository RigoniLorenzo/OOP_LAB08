package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final static int PROPORTION = 2;
    private final JFrame frame = new JFrame();

    public SimpleGUI(final Controller ctrl){
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField txtStringField = new JTextField();
        panel.add(txtStringField, BorderLayout.NORTH);
        final JTextArea txtStringArea = new JTextArea();
        txtStringArea.setEditable(false);
        panel.add(txtStringArea, BorderLayout.CENTER);
        final JPanel downPanel = new JPanel();
        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.LINE_AXIS));
        panel.add(downPanel, BorderLayout.SOUTH);
        final JButton btnPrint = new JButton("Print"), btnShow = new JButton("Show history");
        downPanel.add(btnPrint, BorderLayout.LINE_END);
        downPanel.add(btnShow, BorderLayout.LINE_END);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                ctrl.setNxtToPrint(txtStringField.getText());
                ctrl.printCurrentString();
            }
        });
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder strBuild = new StringBuilder();
                final List<String> historyString = ctrl.getPrintedString();
                for (String s : historyString) {
                    strBuild.append(s);
                    strBuild.append('\n');
                }
                if(!historyString.isEmpty()) {
                    strBuild.deleteCharAt(strBuild.length() - 1);
                }
                txtStringArea.setText(strBuild.toString());
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    public static void main(final String... args){
        final SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }
}
