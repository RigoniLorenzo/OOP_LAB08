package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame();
    
    public SimpleGUIWithFileChooser(final Controller ctrl) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea txtArea = new JTextArea();
        final JButton save = new JButton("Save");
        canvas.add(txtArea, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        /*
         * Handlers
         */
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    ctrl.saveString(txtArea.getText());
                } catch (IOException IOE) {
                    JOptionPane.showMessageDialog(null, IOE.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BorderLayout());
        final JTextField txtPath = new JTextField(ctrl.getPath());
        final JButton btnBrowse = new JButton("Browse...");
        canvas2.add(txtPath, BorderLayout.CENTER);
        txtPath.setEditable(false);
        canvas2.add(btnBrowse, BorderLayout.LINE_END);
        canvas.add(canvas2, BorderLayout.NORTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Handlers
         */
        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser jFC = new JFileChooser("Choose the file");
                jFC.setSelectedFile(ctrl.getCurrentFile());
                switch (jFC.showSaveDialog(frame)) {
                    case JFileChooser.APPROVE_OPTION:
                        ctrl.setCurrentFile(jFC.getSelectedFile());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, jFC.showSaveDialog(btnBrowse), "Error!", JOptionPane.ERROR_MESSAGE);
                }
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
        frame.pack();
    }

    public static void main(final String... a){
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }
}
