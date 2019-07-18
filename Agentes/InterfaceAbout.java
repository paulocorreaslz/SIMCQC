package Agentes;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
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
import java.awt.GridBagLayout;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class InterfaceAbout extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabelLogo = null;

	private JLabel jLabelFrabricante = null;

	private JLabel jLabelDestilador = null;

	private JButton jButtonSalvar = null;

	private JTextField jTextFieldIrox = null;

	private JTextField jTextFieldDestilador = null;

	private JButton jButtonIrox = null;

	private JButton jButtonDestilador = null;

	private JLabel jLabelVersao = null;

	private JLabel jLabelSistemaNome = null;

	private JTextField jTextFieldJess = null;

	private JButton jButtonJess = null;

	private JLabel jLabelHomepage = null;

	private JLabel jLabeldesc2 = null;

	private JLabel jLabeldesc3 = null;

	private JLabel jLabelPIB = null;

	private JLabel jLabelLSI = null;

	private JLabel jLabelLAPQAP = null;

	private JLabel jLabellapqap2 = null;

	private JLabel jLabelAutor1 = null;

	private JLabel jLabelAutor12 = null;

	/**
	 * This is the default constructor
	 */
	public InterfaceAbout() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(392, 496);
		this.setContentPane(getJContentPane());
		this.setTitle("Informações");
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
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabelAutor12 = new JLabel();
			jLabelAutor12.setBounds(new Rectangle(17, 271, 312, 16));
			jLabelAutor12.setText("Paulo José Melo Gomes Corrêa");
			jLabelAutor1 = new JLabel();
			jLabelAutor1.setBounds(new Rectangle(16, 253, 42, 16));
			jLabelAutor1.setText("Autor:");
			jLabellapqap2 = new JLabel();
			jLabellapqap2.setBounds(new Rectangle(19, 389, 169, 16));
			jLabellapqap2.setText("e Biocombustiveis - LAPQAP");
			jLabelLAPQAP = new JLabel();
			jLabelLAPQAP.setBounds(new Rectangle(19, 370, 386, 16));
			jLabelLAPQAP.setText("Lab. de Analises e Pesquisa em Quimica Analitica de Petroleo");
			jLabelLSI = new JLabel();
			jLabelLSI.setBounds(new Rectangle(18, 349, 346, 16));
			jLabelLSI.setText("Laboratorio de Sistemas Inteligentes - LSI");
			jLabelPIB = new JLabel();
			jLabelPIB.setBounds(new Rectangle(18, 327, 358, 16));
			jLabelPIB.setText("Laboratorio de Processamento da Informação Biologica - PIB");
			jLabeldesc3 = new JLabel();
			jLabeldesc3.setBounds(new Rectangle(16, 228, 310, 16));
			jLabeldesc3.setText("Ontologia e Motor de Inferencia (JESS).");
			jLabeldesc2 = new JLabel();
			jLabeldesc2.setBounds(new Rectangle(16, 206, 330, 16));
			jLabeldesc2.setText("de Decisões, utilizando tecnologias: Agentes Inteligentes, ");
			jLabelHomepage = new JLabel();
			jLabelHomepage.setBounds(new Rectangle(20, 437, 185, 16));
			jLabelHomepage.setText("Home Page: http://www.ufma.br");
			jLabelSistemaNome = new JLabel();
			jLabelSistemaNome.setBounds(new Rectangle(15, 164, 54, 16));
			jLabelSistemaNome.setText("SIMCQC:");
			jLabelVersao = new JLabel();
			jLabelVersao.setBounds(new Rectangle(20, 417, 74, 16));
			jLabelVersao.setText("Versão: 1.0");
			jLabelDestilador = new JLabel();
			jLabelDestilador.setBounds(new Rectangle(15, 185, 313, 16));
			jLabelDestilador.setText("Sistema Multiagente para suporte no Apoio a Tomada");
			jLabelFrabricante = new JLabel();
			jLabelFrabricante.setBounds(new Rectangle(18, 306, 88, 16));
			jLabelFrabricante.setText("Laboratorios:");
			jLabelLogo = new JLabel(new ImageIcon("imagens/logo_simcqc.jpg"));
			jLabelLogo.setBounds(new Rectangle(30, 16, 309, 138));
			
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabelLogo, null);
			jPanel.add(jLabelFrabricante, null);
			jPanel.add(jLabelDestilador, null);
			jPanel.add(jLabelVersao, null);
			jPanel.add(jLabelSistemaNome, null);
			jPanel.add(jLabelHomepage, null);
			jPanel.add(jLabeldesc2, null);
			jPanel.add(jLabeldesc3, null);
			jPanel.add(jLabelPIB, null);
			jPanel.add(jLabelLSI, null);
			jPanel.add(jLabelLAPQAP, null);
			jPanel.add(jLabellapqap2, null);
			jPanel.add(jLabelAutor1, null);
			jPanel.add(jLabelAutor12, null);
	
		}
		return jPanel;
	}




	public static void main(String[] args) {   
		 
		  InterfaceAbout sif = new InterfaceAbout();
		    sif.setVisible(true);
	  }

}  //  @jve:decl-index=0:visual-constraint="372,9"
