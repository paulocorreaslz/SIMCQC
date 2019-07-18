package Agentes;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Rectangle;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class Interface extends GuiAgent implements ActionListener {
	JFrame frame = new JFrame("Sistema de Monitoramento e Controle da Qualidade de Combustiveis - SIMCQC");  //  @jve:decl-index=0:visual-constraint="10,17"
//  	Where the GUI is created:
  	JMenuBar menuBar = new JMenuBar();  //  @jve:decl-index=0:visual-constraint="725,69"
     
  	JMenu menu, submenu;
  	JMenuItem menuItem, menuItemAbout,menuItemExport,menuItemConfig, menuItemgas, menuItemdie,menuItembio,menuItemalc,menuItemCreate;
  	JRadioButtonMenuItem rbMenuItem;
  	JCheckBoxMenuItem cbMenuItem;
  	JButton btnStartIrox = new JButton("Iniciar Irox");
  	JButton btnStartDestilador = new JButton("Iniciar Destilador");
    
	public Interface(){ 	
    }
	private static final long serialVersionUID = 5228641897287860364L;
	public void actionPerformed(ActionEvent e)   {
		// TODO Auto-generated method stub
		// determina instancias para criar os agentes em tempo de execução.

    	Profile profile = new ProfileImpl();   
    	Runtime runtime = Runtime.instance();
    	ContainerController container;
    	container = runtime.createAgentContainer(profile);
    	
    	String Action;
	    Action = e.getActionCommand ();
	    if (Action.equals ("irox")) {
	    	btnStartIrox.setEnabled(false);
	    	System.out.println("Interface: Iniciando agente Irox...");
 
	    	AgentController AgenteIrox;
	    
	    	try {	    			
	    			AgenteIrox = container.createNewAgent("AgenteIrox","Agentes.AgenteIrox",  null );
					AgenteIrox.start();
	    	} catch (StaleProxyException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (ControllerException ex) {
				// TODO Auto-generated catch block				
				ex.printStackTrace();
			} 
	    	
	    } else if (Action.equals("destilador")){
	      	btnStartDestilador.setEnabled(false);
	    	System.out.println("Interface: Iniciando agente Destilador...");
 
	    	AgentController AgenteDestilador;
	    
	    	try {	    			
	    		AgenteDestilador = container.createNewAgent("AgenteDestilador","Agentes.AgenteDestilador",  null );
	    		AgenteDestilador.start();
	    	} catch (StaleProxyException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (ControllerException ex) {
				// TODO Auto-generated catch block				
				ex.printStackTrace();
			} 
		} else if (Action.equals ("report"))  {
	    	System.out.println("Interface:Abrindo agente interface report ..");
	    	AgentController AgenteReport = null;
	    	try {
	    			AgenteReport = container.createNewAgent("AgenteInterfaceReport","Agentes.InterfaceReport",  null );
	    			System.out.println(AgenteReport.getState());
	    			AgenteReport.start();;
	    		
    		
	    	} catch (StaleProxyException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			} catch (ControllerException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			}	
			 
	    }  else if (Action.equals ("gasoline"))  {
	    	
	    	System.out.println("Interface:Abrindo agente gasoline ..");
	    	AgentController AgenteGasoline;
	    	try {
	    			AgenteGasoline = container.createNewAgent("AgenteInterfaceGasoline","Agentes.InterfaceGasoline",  null );
	    			AgenteGasoline.start();
	    	} catch (StaleProxyException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (ControllerException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
	    	
	    }  else if (Action.equals ("alcohol"))  {
	    	System.out.println("Abrindo agente alcohol ..");
	    	AgentController AgenteAlcohol;
	    	try {
	    		AgenteAlcohol = container.createNewAgent("AgenteInterfaceAlcohol","Agentes.InterfaceAlcohol",  null );
	    		AgenteAlcohol.start();
	    	} catch (StaleProxyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	    }  else if (Action.equals ("diesel"))  {
	    	System.out.println("Abrindo agente diesel ..");
	    	AgentController AgenteDiesel;
	    	try {
	    		AgenteDiesel = container.createNewAgent("AgenteInterfaceDiesel","Agentes.InterfaceDiesel",  null );
	    		AgenteDiesel.start();
	    	} catch (StaleProxyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 	
	    }  else if (Action.equals ("biodiesel"))  {
	    	System.out.println("Abrindo agente biodiesel ..");
	    	
	    } else if (Action.equals ("export"))  {
	    	System.out.println("Interface: Abrindo agente  interface export..");
	    	System.out.println("Interface:Abrindo agente conversor ..");
	    	AgentController AgenteConversor;
	    	try {
	    		AgenteConversor = container.createNewAgent("AgenteConversor","Agentes.AgenteConversor",  null );
	    		AgenteConversor.start();
	    		
	    	} catch (StaleProxyException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (ControllerException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
	    }   else if (Action.equals ("config"))  {
	    	System.out.println("Interface: Abrindo configuracoes..");
	        InterfaceConfiguration sif = new InterfaceConfiguration();
			sif.setVisible(true);
	    }   else if (Action.equals ("sobre"))  {
	    	System.out.println("Interface: Abrindo informacoes sobre o sistema..");
	        InterfaceAbout sif = new InterfaceAbout();
			sif.setVisible(true);	
	    } else {
	    
	    }
	}
	public static void addComponentsToPane(Container pane) {
        pane.setLayout(null);
    }
	

	protected void onGuiEvent(GuiEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();  
		
//    	Build the first menu.

    	frame.setSize(new Dimension(376, 225));
    	frame.setSize(new Dimension(624, 413));
    	frame.setSize(new Dimension(662, 361));
    	frame.setSize(new Dimension(613, 361));
    	frame.setSize(new Dimension(245, 188));
    	menu = new JMenu("Amostras");
    	menu.setMnemonic(KeyEvent.VK_A);
    	menu.setBounds(new Rectangle(409, 42, 81, 21));
    	menu.getAccessibleContext().setAccessibleDescription(
    	        "The only menu in this program that has menu items");
    	menuBar.add(menu);

//    	a group of JMenuItems
    	menuItemgas = new JMenuItem("Gasolina",
    			new ImageIcon("imagens/icon_gc.jpg"));
    	menuItemgas.getAccessibleContext().setAccessibleDescription(
    	        "This doesn't really do anything");
    	menu.add(menuItemgas);
    	
    	menuItemgas.addActionListener(this);
    	menuItemgas.setActionCommand("gasoline");

    	menuItemalc = new JMenuItem("Alcool",
    	                         new ImageIcon("imagens/icon_ah.jpg"));
    	menu.add(menuItemalc);
    	menuItemalc.addActionListener(this);
    	menuItemalc.setActionCommand("alcohol");
    	
    	menuItemdie = new JMenuItem("Diesel",
                new ImageIcon("imagens/icon_od.jpg"));
    	menu.add(menuItemdie);
    	menuItemdie.addActionListener(this);
    	menuItemdie.setActionCommand("diesel");
    	
    	menuItembio = new JMenuItem("Biodiesel",
                new ImageIcon("imagens/icon_bd.jpg"));
    	menu.add(menuItembio);
    	menuItembio.addActionListener(this);
    	menuItembio.setActionCommand("biodiesel");


//    	Build second menu in the menu bar.
    	menu = new JMenu("Relatorio");
    	menu.setMnemonic(KeyEvent.VK_R);
    	menu.setBounds(new Rectangle(490, 36, 75, 21));
    	menuBar.add(menu);
    	
    	menuItemCreate = new JMenuItem("Imprimir Relatorio",
                new ImageIcon("imagens/report_icon.jpg"));
 
    	//submenu.add(menuItem);
    	menu.add(menuItemCreate);
    	menuItemCreate.addActionListener(this);
    	menuItemCreate.setActionCommand("report");

      	menu = new JMenu("Exportar");
    	menu.setMnemonic(KeyEvent.VK_E);
    	menu.setBounds(new Rectangle(468, 5, 73, 21));
    	menuBar.add(menu);
    	
    	menuItemExport = new JMenuItem("Exportar XML",
                new ImageIcon("imagens/icon_export.jpg"));
 
    	//submenu.add(menuItem);
    	menu.add(menuItemExport);
    	menuItemExport.addActionListener(this);
    	menuItemExport.setActionCommand("export");
    	// configuracoes
    	menu = new JMenu("Configurações");
    	menu.setMnemonic(KeyEvent.VK_C);
    	menuBar.add(menu);
    	
    	menuItemConfig = new JMenuItem("Configurar",
                new ImageIcon("imagens/config_icon.jpg"));
 
    	//submenu.add(menuItem);
    	menu.add(menuItemConfig);
    	menuItemConfig.addActionListener(this);
    	menuItemConfig.setActionCommand("config");
    	// insere menu about
    	menu = new JMenu("Sobre..");
    	menu.setMnemonic(KeyEvent.VK_S);
    	menuBar.add(menu);
    	
    	menuItemAbout = new JMenuItem("Informações",
                new ImageIcon("imagens/icon_about.jpg"));
 
    	//submenu.add(menuItem);
    	menu.add(menuItemAbout);
    	menuItemAbout.addActionListener(this);
    	menuItemAbout.setActionCommand("sobre");
    	
    	Container pane = frame.getContentPane();
    	frame.setContentPane(pane);
    	frame.setContentPane(pane);
    	frame.setContentPane(pane);
    	frame.setContentPane(pane);
    	
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar);
        //Size and display the window.
        
    	frame.setSize(new Dimension(640, 480));
        Insets insets = frame.getInsets();
        
        frame.setSize(640 + insets.left + insets.right,
                      480 + insets.top + insets.bottom);
        frame.setVisible(true);
        frame.setContentPane(pane);
        pane.setLayout(null);
         	  
    	frame.setJMenuBar(menuBar);
       	pane.add(btnStartIrox, null);
    	btnStartIrox.setBounds(new Rectangle(7, 9, 134, 42));
    	btnStartIrox.setText("Iniciar IROX");
    	
       	pane.add(btnStartDestilador, null);
       	btnStartDestilador.setBounds(new Rectangle(7, 9, 134, 42));
       	btnStartDestilador.setText("Iniciar DESTILADOR");
    	
    	frame.setSize(new Dimension(680, 480));
    	frame.setContentPane(pane);

         JLabel logos = new JLabel(new ImageIcon("imagens/logos_all.jpg"));
         JLabel logo_simcqc = new JLabel(new ImageIcon("imagens/logo_simcqc.jpg"));
         pane.setLayout(null);
         pane.add(logo_simcqc);
         pane.add(logos);
         pane.add(btnStartIrox);
         pane.add(btnStartDestilador);
         
         btnStartIrox.addActionListener(this);
         btnStartIrox.setActionCommand("irox");
          
         btnStartDestilador.addActionListener(this);
         btnStartDestilador.setActionCommand("destilador");
      
         Dimension size = btnStartIrox.getPreferredSize();
         btnStartIrox.setBounds(15 + insets.left, 5 + insets.top,
                  size.width + 50, size.height + 20);
          
         size = btnStartDestilador.getPreferredSize();
         btnStartDestilador.setBounds(15 + insets.left, 60 + insets.top,
                  size.width + 50, size.height + 20);
         
          size = logos.getPreferredSize();
          logos.setBounds(315 + insets.left, 140 + insets.top,
                       size.width + 50, size.height + 20);
         
          size = logo_simcqc.getPreferredSize();
          logo_simcqc.setBounds(300 + insets.left, 0 + insets.top,
                       size.width + 50, size.height + 20);
      
	}
}
