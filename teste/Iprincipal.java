package teste;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;

public class Iprincipal extends JFrame {
  /**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(370, 186));
			
	}

public static void main(String[] args) {
    new Iprincipal();
  }

  public Iprincipal() {
    super("Multiple Document Interface");
		initialize();
  //  WindowUtilities.setNativeLookAndFeel();
   // addWindowListener(new ExitListener());
    Container content = getContentPane();
    content.setBackground(Color.white);
    JDesktopPane desktop = new JDesktopPane();
    desktop.setBackground(Color.white);
    content.add(desktop, BorderLayout.CENTER);
    setSize(450, 400);
    for(int i=0; i<5; i++) {
      JInternalFrame frame
        = new JInternalFrame(("Internal Frame " + i),
                             true, true, true, true);
      frame.setLocation(i*50+10, i*50+10);
      frame.setSize(200, 150);
      frame.setBackground(Color.white);
      desktop.add(frame);
      frame.moveToFront();
      frame.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
    setVisible(true);
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
