package teste;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleInternalFrame extends Frame {

  JButton openButton, macButton, javaButton, motifButton, winButton;
  JLayeredPane desktop;
  JInternalFrame internalFrame;

  public SimpleInternalFrame() {
    super("Internal Frame Demo");
    setSize(500,400);
    openButton = new JButton("Open");
    macButton = new JButton("Mac");
    javaButton = new JButton("Metal");
    motifButton = new JButton("Motif");
    winButton = new JButton("Windows");
    Panel p = new Panel();
    p.add(openButton);
    p.add(macButton);
    p.add(javaButton);
    p.add(motifButton);
    p.add(winButton);
    add(p, BorderLayout.SOUTH);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    openButton.addActionListener(new OpenListener());
    //LnFListener lnf = new LnFListener(this);
   // macButton.addActionListener(lnf);
   // javaButton.addActionListener(lnf);
   // motifButton.addActionListener(lnf);
   // winButton.addActionListener(lnf);

    // Set up the layered pane
    desktop = new JDesktopPane();
    desktop.setOpaque(true);
    add(desktop, BorderLayout.CENTER);
  }

  // An inner class to handle presses of the Open button
  class OpenListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if ((internalFrame == null) || (internalFrame.isClosed())) {
        internalFrame = new JInternalFrame("Internal Frame", 
                                           true, true, true, true);
        internalFrame.setBounds(50, 50, 200, 100);
        desktop.add(internalFrame, new Integer(1));
        internalFrame.setVisible(true);
       }
    }
  }

  public static void main(String args[]) {
    SimpleInternalFrame sif = new SimpleInternalFrame();
    sif.setVisible(true);
  }
}
