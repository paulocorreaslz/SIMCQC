package Agentes;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JTextField;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class InterfaceConfiguration extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabel = null;

	private JLabel jLabelIrox = null;

	private JLabel jLabelDestilador = null;

	private JButton jButtonSalvar = null;

	private JTextField jTextFieldIrox = null;

	private JTextField jTextFieldDestilador = null;

	private JButton jButtonIrox = null;

	private JButton jButtonDestilador = null;

	final JFileChooser fc = new JFileChooser();
	public BufferedWriter bw;  //  @jve:decl-index=0:

	private JLabel jLabelJess = null;

	private JLabel jLabelJessRegras = null;

	private JTextField jTextFieldJess = null;

	private JButton jButtonJess = null;

	private JLabel jLabel1tecnico = null;

	private JTextField jTextFieldtecnico = null;
	/**
	 * This is the default constructor
	 */
	public InterfaceConfiguration() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(454, 260);
		this.setContentPane(getJContentPane());
		this.setTitle("Configurações");
		
        jButtonIrox.addActionListener(this);
        jButtonIrox.setActionCommand("irox");
        
        jButtonDestilador.addActionListener(this);
        jButtonDestilador.setActionCommand("destilador");
        
        jButtonSalvar.addActionListener(this);
        jButtonSalvar.setActionCommand("salvar");
        
        jButtonJess.addActionListener(this);
        jButtonJess.setActionCommand("jess");
       loadMonitores();
	}
	public void loadMonitores(){
	     // Read properties file.   
	       Properties properties = new Properties();   
	       try {   
	         properties.load(new FileInputStream("configuration"));   
	         String irox = properties.getProperty("irox");   
	         System.out.println("Valor do campo iroc: " + irox);   
	         String destilador = properties.getProperty("destilador");
	         System.out.println("Valor do campo destilador: " + destilador);   
	         String jess = properties.getProperty("jess");
	         System.out.println("Valor do campo jess: " + jess);   
	         String tecnico = properties.getProperty("tecnico");
	         System.out.println("Valor do campo tecnico: " + tecnico);
	         if (destilador!= null)
	        	 jTextFieldDestilador.setText(""+destilador.toString());
	         if (irox!=null)
	        	 jTextFieldIrox.setText(""+irox.toString());
	         if (jess!=null)
	        	 jTextFieldJess.setText(""+jess.toString());
	         if (tecnico!=null)
	        	 jTextFieldtecnico.setText(""+tecnico.toString());
	        }catch (IOException e) {   
	           System.out.println("Erro ao ler arquivo." + e.getMessage());   
	       }   
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1tecnico = new JLabel();
			jLabel1tecnico.setBounds(new Rectangle(14, 99, 115, 16));
			jLabel1tecnico.setText("Nome do Tecnico:");
			jLabelJessRegras = new JLabel();
			jLabelJessRegras.setBounds(new Rectangle(13, 152, 156, 16));
			jLabelJessRegras.setText("Arquivo de regras (JESS):");
			jLabelJess = new JLabel();
			jLabelJess.setBounds(new Rectangle(12, 130, 120, 16));
			jLabelJess.setText("Motor de Inferencia:");
			jLabelDestilador = new JLabel();
			jLabelDestilador.setBounds(new Rectangle(20, 59, 117, 16));
			jLabelDestilador.setText("Destilador HERXOG:");
			jLabelIrox = new JLabel();
			jLabelIrox.setBounds(new Rectangle(70, 37, 63, 16));
			jLabelIrox.setText("IROX 2000:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 15, 91, 16));
			jLabel.setText("Equipamentos:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabelIrox, null);
			jPanel.add(jLabelDestilador, null);
			jPanel.add(getJButtonSalvar(), null);
			jPanel.add(getJTextFieldIrox(), null);
			jPanel.add(getJTextFieldDestilador(), null);
			jPanel.add(getJButtonIrox(), null);
			jPanel.add(getJButtonDestilador(), null);
			jPanel.add(jLabelJess, null);
			jPanel.add(jLabelJessRegras, null);
			jPanel.add(getJTextFieldJess(), null);
			jPanel.add(getJButtonJess(), null);
			jPanel.add(jLabel1tecnico, null);
			jPanel.add(getJTextFieldtecnico(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButtonSalvar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSalvar() {
		if (jButtonSalvar == null) {
			jButtonSalvar = new JButton();
			jButtonSalvar.setBounds(new Rectangle(330, 179, 85, 35));
			jButtonSalvar.setText("Salvar");
		}
		return jButtonSalvar;
	}

	/**
	 * This method initializes jTextFieldIrox	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldIrox() {
		if (jTextFieldIrox == null) {
			jTextFieldIrox = new JTextField();
			jTextFieldIrox.setBounds(new Rectangle(142, 36, 208, 20));
		}
		return jTextFieldIrox;
	}

	/**
	 * This method initializes jTextFieldDestilador	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldDestilador() {
		if (jTextFieldDestilador == null) {
			jTextFieldDestilador = new JTextField();
			jTextFieldDestilador.setBounds(new Rectangle(141, 59, 209, 20));
		}
		return jTextFieldDestilador;
	}
	  /**
	 * This method initializes jTextFieldJess	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldJess() {
		if (jTextFieldJess == null) {
			jTextFieldJess = new JTextField();
			jTextFieldJess.setBounds(new Rectangle(173, 151, 178, 20));
		}
		return jTextFieldJess;
	}

	/**
	 * This method initializes jButtonJess	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonJess() {
		if (jButtonJess == null) {
			jButtonJess = new JButton();
			jButtonJess.setBounds(new Rectangle(357, 150, 62, 21));
			jButtonJess.setText("Abrir");
		}
		return jButtonJess;
	}

	/**
	 * This method initializes jTextFieldtecnico	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldtecnico() {
		if (jTextFieldtecnico == null) {
			jTextFieldtecnico = new JTextField();
			jTextFieldtecnico.setBounds(new Rectangle(135, 96, 287, 20));
		}
		return jTextFieldtecnico;
	}

	public static void main(String[] args) {   
		 
		  InterfaceConfiguration sif = new InterfaceConfiguration();
		    sif.setVisible(true);
	  }
	/**
	 * This method initializes jButtonIrox	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonIrox() {
		if (jButtonIrox == null) {
			jButtonIrox = new JButton();
			jButtonIrox.setBounds(new Rectangle(358, 34, 67, 22));
			jButtonIrox.setText("Abrir");
		}
		return jButtonIrox;
	}

	/**
	 * This method initializes jButtonDestilador	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDestilador() {
		if (jButtonDestilador == null) {
			jButtonDestilador = new JButton();
			jButtonDestilador.setBounds(new Rectangle(358, 59, 66, 23));
			jButtonDestilador.setText("Abrir");
		}
		return jButtonDestilador;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String Action;
	    Action = e.getActionCommand ();
	    if (Action.equals ("destilador")) {
	    	// Fill the content
	    	fc.showOpenDialog(jPanel);
	    	jTextFieldDestilador.setText((String)fc.getSelectedFile().getAbsolutePath());
	   
	    } else if (Action.equals("irox")){	
	    	fc.showOpenDialog(jPanel);
	    	jTextFieldIrox.setText((String)fc.getSelectedFile().getAbsolutePath());
	    } else if (Action.equals("jess")){	
	    	fc.showOpenDialog(jPanel);
	    	jTextFieldJess.setText((String)fc.getSelectedFile().getAbsolutePath());
	
	    } else if (Action.equals("salvar")) {
	          if (salvar()) {
	        	  JOptionPane.showMessageDialog(jPanel,"Salvo com sucesso!!");
	          } else {
	        	  JOptionPane.showMessageDialog(jPanel,"Falha ao salvar!");
	          }
	    }
	}	
	
	public boolean salvar() {   	       
		 try{   		      
		    bw = new BufferedWriter(new FileWriter("configuration")); //nome e  extensão do arquivo q vc vai escrever   
		                 	  
		    bw.write("irox="+jTextFieldIrox.getText().replace("\\", "\\\\")+"\n"); // coloca aki o nome do seu text area, aí jah era!!   
		    bw.write("destilador="+jTextFieldDestilador.getText().replace("\\", "\\\\")+"\n"); // coloca aki o nome do seu text area, aí jah era!!   
		    bw.write("jess="+jTextFieldJess.getText().replace("\\", "\\\\")+"\n"); // coloca aki o nome do seu text area, aí jah era!!   
		    bw.write("tecnico="+jTextFieldtecnico.getText().replace("\\", "\\\\")+"\n"); // coloca aki o nome do seu text area, aí jah era!!   
			   
		    bw.close(); // não eskeça de fechar o arquivo
		    return true;
		    }catch(Exception e){
		    return false;
		    }   
		 }  
}  //  @jve:decl-index=0:visual-constraint="10,10"
