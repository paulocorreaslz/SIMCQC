package Agentes;

import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.awt.Container;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Agentes.InterfaceGasoline.HandleSamplesBahaviour;
import Agentes.InterfaceGasoline.RequestSamplesBehaviour;
import Ontologia.FuelOntology;
import Ontologia.Gasoline;
import Ontologia.Information;
import Ontologia.Report;
import Ontologia.Requisition;
import Ontologia.Sample;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.plaf.FileChooserUI;

import java.io.*;
import java.util.*;

import javax.swing.JList;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class InterfaceReport extends GuiAgent implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5228641897287860364L;
	private ContentManager manager  = (ContentManager) getContentManager();
	    // This agent "speaks" the SL language
	private Codec      codec    = new SLCodec();
	    // This agent "knows" the Music-Shop ontology
	private Ontology   ontology = FuelOntology.getInstance();
	
	
   
    //private JComboBox jcAmostras = new JComboBox();
   // private JButton btnRequestSamples = new JButton("Recuperar Amostras");
	private JLabel jLabelSamples = null;
	private JButton jButtonCreateReport = null;
	private int lineNumber = 0;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JButton jButtonSalvar = null;
	
	public BufferedWriter bw;   
	final JFileChooser fc = new JFileChooser();
	private List ListSamplesGasoline = new ArrayList();  //  @jve:decl-index=0:
	private List ListSamplesAlcool = new ArrayList();;
	private List ListSamplesDiesel = new ArrayList();;
	private List ListSamplesBiodiesel = new ArrayList();;
	
	JFrame frame = new JFrame("Relatorio");
	private JComboBox jComboBoxGasoline = null;
	private JLabel jLabelGasolina = null;
	private JLabel jLabelAlcool = null;
	private JComboBox jComboBoxAlcool = null;
	private JComboBox jComboBoxDiesel = null;
	private JLabel jLabelDiesel = null;
	private JLabel jLabelBiodiesel = null;
	private JComboBox jComboBoxBioDiesel = null;
	private JButton jButtonIReport = null;
	public boolean salvar(File file) {   
	       
	 try{   
	      
	    bw = new BufferedWriter(new FileWriter(file)); //nome e  extensão do arquivo q vc vai escrever   
	                 	  
	    bw.write(jTextArea.getText()); // coloca aki o nome do seu text area, aí jah era!!   
	    bw.close(); // não eskeça de fechar o arquivo
	    return true;
	    }catch(Exception e){
	    return false;
	    }   
	 }       

	public static void addComponentsToPane(Container pane) {
        pane.setLayout(null);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
  
    }

    /**
	 * This method initializes jButtonCreateReport	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCreateReport() {
		if (jButtonCreateReport == null) {
			jButtonCreateReport = new JButton();
			jButtonCreateReport.setBounds(new Rectangle(467, 352, 149, 39));
			jButtonCreateReport.setText("Capturar Amostras" +
					"");
		}
		return jButtonCreateReport;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(10, 106, 592, 232));
			jScrollPane.setViewportView(getJTextArea());
			jScrollPane.setVerticalScrollBarPolicy(jScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

	/**
	 * This method initializes jButtonSalvar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSalvar() {
		if (jButtonSalvar == null) {
			jButtonSalvar = new JButton();
			jButtonSalvar.setBounds(new Rectangle(312, 352, 140, 39));
			jButtonSalvar.setText("Salvar Relatorio");
		}
		return jButtonSalvar;
	}

	/**
	 * This method initializes jComboBoxGasoline	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxGasoline() {
		if (jComboBoxGasoline == null) {
			jComboBoxGasoline = new JComboBox();
			jComboBoxGasoline.setBounds(new Rectangle(19, 68, 134, 25));
		}
		return jComboBoxGasoline;
	}

	/**
	 * This method initializes jComboBoxAlcool	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxAlcool() {
		if (jComboBoxAlcool == null) {
			jComboBoxAlcool = new JComboBox();
			jComboBoxAlcool.setBounds(new Rectangle(170, 68, 136, 25));
		}
		return jComboBoxAlcool;
	}

	/**
	 * This method initializes jComboBoxDiesel	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxDiesel() {
		if (jComboBoxDiesel == null) {
			jComboBoxDiesel = new JComboBox();
			jComboBoxDiesel.setBounds(new Rectangle(321, 68, 120, 25));
		}
		return jComboBoxDiesel;
	}

	/**
	 * This method initializes jComboBoxBioDiesel	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxBioDiesel() {
		if (jComboBoxBioDiesel == null) {
			jComboBoxBioDiesel = new JComboBox();
			jComboBoxBioDiesel.setBounds(new Rectangle(458, 68, 115, 25));
		}
		return jComboBoxBioDiesel;
	}

	/**
	 * This method initializes jButtonIReport	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonIReport() {
		if (jButtonIReport == null) {
			jButtonIReport = new JButton();
			jButtonIReport.setBounds(new Rectangle(168, 352, 131, 39));
			jButtonIReport.setText("Gerar Relatorio");
		}
		return jButtonIReport;
	}

	/**
	 * This method initializes txtReport	
	 * 	
	 * @return javax.swing.JTextArea	
	 */


	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        
    }

	protected void onGuiEvent(GuiEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	  
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		
			manager.registerLanguage(codec);
			manager.registerOntology(ontology);

	       // jcAmostras.setBounds(new Rectangle(126, 6, 114, 25));
	        //btnRequestSamples.setBounds(new Rectangle(273, 129, 153, 26));
	        jLabelBiodiesel = new JLabel();
	        jLabelBiodiesel.setBounds(new Rectangle(458, 48, 104, 16));
	        jLabelBiodiesel.setText("Biodiesel");
	        jLabelDiesel = new JLabel();
	        jLabelDiesel.setBounds(new Rectangle(321, 48, 75, 16));
	        jLabelDiesel.setText("Diesel");
	        jLabelAlcool = new JLabel();
	        jLabelAlcool.setBounds(new Rectangle(176, 48, 51, 16));
	        jLabelAlcool.setText("Alcool");
	        jLabelGasolina = new JLabel();
	        jLabelGasolina.setBounds(new Rectangle(19, 48, 110, 16));
	        jLabelGasolina.setText("Gasolina");
	        jLabelSamples = new JLabel();
	        jLabelSamples.setBounds(new Rectangle(10, 7, 63, 16));
	        jLabelSamples.setText("Relatorio");
	        
	        frame.setDefaultCloseOperation(this.frame.DISPOSE_ON_CLOSE);
	        
	        frame.setName("Relatorio");
	        	
	        frame.setSize(new Dimension(648, 440));
	        //Set up the content pane.
	     
	        Container pane = frame.getContentPane();
	        Insets insets = pane.getInsets();
	        //Size and display the window.
	      
	        frame.setSize(640 + insets.left + insets.right,
	                      480 + insets.top + insets.bottom);
	        frame.setVisible(true);
	        frame.setContentPane(pane);
	        pane.setLayout(null);
	      //  pane.add(btnRequestSamples, null);
	       // pane.add(jcAmostras, null);
	        pane.add(jLabelSamples, null);
	        pane.add(getJButtonCreateReport(), null);
	        pane.add(getJScrollPane(), null);
	        pane.add(getJButtonSalvar(), null);
	        pane.add(getJComboBoxGasoline(), null);
	        pane.add(jLabelGasolina, null);
	        pane.add(jLabelAlcool, null);
	        pane.add(getJComboBoxAlcool(), null);
	        pane.add(getJComboBoxDiesel(), null);
	        pane.add(jLabelDiesel, null);
	        pane.add(jLabelBiodiesel, null);
	        pane.add(getJComboBoxBioDiesel(), null);
	        pane.add(getJButtonIReport(), null);
	     
	        jButtonSalvar.addActionListener(this);
	        jButtonSalvar.setActionCommand("salvar");
	        
	        jButtonCreateReport.addActionListener(this);
	        jButtonCreateReport.setActionCommand("request");
	   
	        jButtonIReport.addActionListener(this);
	        jButtonIReport.setActionCommand("ireport");
	   
	       // jButtonCreateReport.setEnabled(true);
	        addBehaviour(new HandleAnalisedSamplesBahaviour(this));

	}
	
class RequestAnalisedSamplesBehaviour extends OneShotBehaviour {
		
    	private Sample it = null;
    	
			public RequestAnalisedSamplesBehaviour(Agent a) { 
				super(a);
				this.it = it;
			}
	
			public void action() {
			   	System.out.println("\nInterface Relatorio: enviando requisicao de amostras para gestor..");
				ACLMessage ms = new ACLMessage(ACLMessage.REQUEST);
				AID receiver = new AID("AgenteCognitivo",AID.ISLOCALNAME); // Send the message to myself
		       				
				ms.setSender(getAID());
				ms.addReceiver(receiver);
				ms.setLanguage(codec.getName());
				ms.setOntology(ontology.getName());
			
				Requisition req = new Requisition();
				req.setName(getAID());
				req.setType("RELATORIO");
				
				try {
					manager.fillContent(ms, req);
				} catch (CodecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				send(ms);
			}
    }
    
class HandleAnalisedSamplesBahaviour extends CyclicBehaviour {
	
	public HandleAnalisedSamplesBahaviour(Agent a) { 
		super(a); 
	}

	public void action() {
		ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		if (msg != null) {
			System.out.println("\nINTERFACE Relatorio: Informação recebida.");
			System.out.println(msg);
				try {
					ContentElement conteudo = manager.extractContent(msg);
					
					if (conteudo instanceof Report) {
						Report informante = (Report) conteudo;	
						if (informante.getSample() instanceof Gasoline) {
							Gasoline item = (Gasoline) informante.getSample();
							System.out.println("Gasolina Amostra is: "+item.toString());
							getJTextArea().setText(getJTextArea().getText()+"\nNome da Amostra: "+item.getNameSample()+"\nData da Analise: "+item.getDateSample()+"\nDiagnostico:\n"+informante.getDiagnosis().replace("\\", "")+"\nPropriedades Analisadas: " + item.toString()+"\n");
							getJComboBoxGasoline().addItem(item.getNameSample());
							ListSamplesGasoline.add(informante);
							lineNumber++;
						}
					}
					
				} catch (UngroundedException ue) {
		    		// The message content includes variables --> It must be an abs descriptor 
		    	}	
		    	catch(Exception e) { 
		    		e.printStackTrace(); 
		    	}
		} else {
			block();
		}
	}
	
} 
		

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String Action;
	    Action = e.getActionCommand ();
	    if (Action.equals ("request")) {
	    	// Fill the content
			addBehaviour(new RequestAnalisedSamplesBehaviour(this));
			this.jButtonCreateReport.setEnabled(true);
	    } else if (Action.equals ("ireport")){
	    	
	        Properties properties = new Properties();   
	        try {   
	          properties.load(new FileInputStream("configuration"));   
	          String tecnico = properties.getProperty("tecnico");   
	          System.out.println("Valor do campo: " + tecnico);   
	          GerarRelatorio gerar = new GerarRelatorio(ListSamplesGasoline,tecnico);		    	
	        } catch (IOException ess) {   
	            System.out.println("Erro ao capturar nome do tecnico." + ess.getMessage());
	           
	        }   
	    	
	    } else if (Action.equals ("salvar")){
	    	
	    	   fc.showSaveDialog(this.frame);
	          	            // Get the selected file
	           File file = fc.getSelectedFile();
	          if (salvar(file)) {
	        	  JOptionPane.showMessageDialog(frame,"Salvo com sucesso!!");
	          } else {
	          	  JOptionPane.showMessageDialog(frame,"O arquivo não foi salvo!");
	          }
	    }
	}

	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		this.doActivate();
	}	
}
