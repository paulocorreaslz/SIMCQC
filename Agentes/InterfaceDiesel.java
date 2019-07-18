package Agentes;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Agentes.InterfaceAlcohol.HandleSamplesBahaviour;
import Agentes.InterfaceAlcohol.RequestSamplesBehaviour;
import Ontologia.Diesel;
import Ontologia.FuelOntology;
import Ontologia.Gasoline;
import Ontologia.Information;
import Ontologia.Requisition;
import Ontologia.Sample;

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

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class InterfaceDiesel extends GuiAgent implements ActionListener {

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
	private JComboBox jComboBoxSamples = null;
	private JLabel jLabelAspect = null;
	private JButton jButtonGetSamples = null;
	private JTextField jTextFieldAspectColor = null;
	private JLabel jLabelMassEsp = null;
	private JLabel jLabelcor = null;
	private JComboBox boxAspectoCor = null;
	private JLabel jLabelMass20gcm = null;
	private JTextField txtMassa20gcm = null;
	private JRadioButton jRadioButtonHV = null;
	private JRadioButton jRadioButtonDD = null;
	private JLabel jLabelMEsp20kgm = null;
	private JTextField txtMassa20kgm = null;
	private JButton jButtonSendSample = null;
	private JLabel lblDestilacaoHerzog = null;
	private JLabel lblIndCetano = null;
	private JLabel lblTeorEnxofre = null;
	private JLabel lblPontoFugor = null;
	private JLabel lblMassEsp15 = null;
	private JTextField txtMassa10gcm = null;
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
	 * This method initializes jComboBoxSamples	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxSamples() {
		if (jComboBoxSamples == null) {
			jComboBoxSamples = new JComboBox();
			jComboBoxSamples.setBounds(new Rectangle(9, 29, 137, 25));
		}
		return jComboBoxSamples;
	}

	/**
	 * This method initializes jButtonGetSamples	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonGetSamples() {
		if (jButtonGetSamples == null) {
			jButtonGetSamples = new JButton();
			jButtonGetSamples.setBounds(new Rectangle(480, 346, 127, 37));
			jButtonGetSamples.setText("Get Samples");
		}
		return jButtonGetSamples;
	}

	/**
	 * This method initializes jTextFieldAspectColor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldAspectColor() {
		if (jTextFieldAspectColor == null) {
			jTextFieldAspectColor = new JTextField();
			jTextFieldAspectColor.setBounds(new Rectangle(7, 110, 108, 20));
		}
		return jTextFieldAspectColor;
	}

	/**
	 * This method initializes boxAspectoCor	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getBoxAspectoCor() {
		if (boxAspectoCor == null) {
			boxAspectoCor = new JComboBox();
			boxAspectoCor.setBounds(new Rectangle(10, 110, 136, 25));
		}
		return boxAspectoCor;
	}

	/**
	 * This method initializes txtMassa20gcm	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtMassa20gcm() {
		if (txtMassa20gcm == null) {
			txtMassa20gcm = new JTextField();
			txtMassa20gcm.setBounds(new Rectangle(210, 69, 132, 20));
		}
		return txtMassa20gcm;
	}

	/**
	 * This method initializes jRadioButtonHV	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButtonHV() {
		if (jRadioButtonHV == null) {
			jRadioButtonHV = new JRadioButton();
			jRadioButtonHV.setBounds(new Rectangle(215, 50, 50, 21));
			jRadioButtonHV.setText("HV");
		}
		return jRadioButtonHV;
	}

	/**
	 * This method initializes jRadioButtonDD	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButtonDD() {
		if (jRadioButtonDD == null) {
			jRadioButtonDD = new JRadioButton();
			jRadioButtonDD.setBounds(new Rectangle(273, 50, 64, 21));
			jRadioButtonDD.setText("DD");
		}
		return jRadioButtonDD;
	}

	/**
	 * This method initializes txtMassa20kgm	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtMassa20kgm() {
		if (txtMassa20kgm == null) {
			txtMassa20kgm = new JTextField();
			txtMassa20kgm.setBounds(new Rectangle(214, 113, 130, 20));
		}
		return txtMassa20kgm;
	}

	/**
	 * This method initializes jButtonSendSample	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSendSample() {
		if (jButtonSendSample == null) {
			jButtonSendSample = new JButton();
			jButtonSendSample.setBounds(new Rectangle(481, 300, 126, 39));
			jButtonSendSample.setText("Send Sample");
		}
		return jButtonSendSample;
	}

	/**
	 * This method initializes txtMassa10gcm	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtMassa10gcm() {
		if (txtMassa10gcm == null) {
			txtMassa10gcm = new JTextField();
			txtMassa10gcm.setBounds(new Rectangle(216, 153, 130, 20));
		}
		return txtMassa10gcm;
	}

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
	        lblMassEsp15 = new JLabel();
	        lblMassEsp15.setBounds(new Rectangle(203, 134, 166, 16));
	        lblMassEsp15.setText("M. Especifica a 15º C g/cm³");
	        lblPontoFugor = new JLabel();
	        lblPontoFugor.setBounds(new Rectangle(453, 160, 90, 16));
	        lblPontoFugor.setText("Ponto de Fugor");
	        lblTeorEnxofre = new JLabel();
	        lblTeorEnxofre.setBounds(new Rectangle(231, 187, 94, 16));
	        lblTeorEnxofre.setText("Teor de Enxofre");
	        lblIndCetano = new JLabel();
	        lblIndCetano.setBounds(new Rectangle(16, 161, 103, 16));
	        lblIndCetano.setText("Indice de Cetano");
	        lblDestilacaoHerzog = new JLabel();
	        lblDestilacaoHerzog.setBounds(new Rectangle(442, 13, 118, 16));
	        lblDestilacaoHerzog.setText("Destilação HERZOG");
	        jLabelMEsp20kgm = new JLabel();
	        jLabelMEsp20kgm.setBounds(new Rectangle(203, 92, 158, 16));
	        jLabelMEsp20kgm.setText("M. Especifica a 20ºC Kg/m³");
	        jLabelMass20gcm = new JLabel();
	        jLabelMass20gcm.setBounds(new Rectangle(202, 29, 158, 16));
	        jLabelMass20gcm.setText("M. Especifica a 20ºC g/cm³");
	        jLabelcor = new JLabel();
	        jLabelcor.setBounds(new Rectangle(44, 90, 38, 16));
	        jLabelcor.setText("Cor:");
	        jLabelMassEsp = new JLabel();
	        jLabelMassEsp.setBounds(new Rectangle(219, 8, 114, 16));
	        jLabelMassEsp.setText("Massa Especifica");
	        jLabelAspect = new JLabel();
	        jLabelAspect.setText("Aspecto e Cor");
	        jLabelAspect.setBounds(new Rectangle(29, 68, 94, 16));
	        jLabelSamples = new JLabel();
	        jLabelSamples.setBounds(new Rectangle(6, 8, 109, 16));
	        jLabelSamples.setText("Nome da Amostra:");
	        JFrame frame = new JFrame("Diesel");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
	        pane.add(getJComboBoxSamples(), null);
	        pane.add(getJButtonGetSamples(), null);
	        pane.add(jLabelAspect, null);
	        pane.add(jLabelMassEsp, null);
	        pane.add(jLabelcor, null);
	        pane.add(getBoxAspectoCor(), null);
	        pane.add(jLabelMass20gcm, null);
	        pane.add(getTxtMassa20gcm(), null);
	        pane.add(getJRadioButtonHV(), null);
	        pane.add(getJRadioButtonDD(), null);
	        pane.add(jLabelMEsp20kgm, null);
	        pane.add(getTxtMassa20kgm(), null);
	        pane.add(getJButtonSendSample(), null);
	        pane.add(lblDestilacaoHerzog, null);
	        pane.add(lblIndCetano, null);
	        pane.add(lblTeorEnxofre, null);
	        pane.add(lblPontoFugor, null);
	        pane.add(lblMassEsp15, null);
	        pane.add(getTxtMassa10gcm(), null);
	        
                  
	        jButtonGetSamples.addActionListener(this);
	        jButtonGetSamples.setActionCommand("request");
	    	
	        jButtonSendSample.addActionListener(this);
	        jButtonSendSample.setActionCommand("send");
	   
	        
	        jButtonSendSample.setEnabled(false);
	        addBehaviour(new HandleSamplesBahaviour(this));
	        getBoxAspectoCor().addItem("Limpido s/ impurezas");
	        getBoxAspectoCor().addItem("Limpido c/ impurezas");
	        getBoxAspectoCor().addItem("Turvo s/ impurezas");
	        getBoxAspectoCor().addItem("Turvo c/ impurezas");
	        getBoxAspectoCor().addItem("Azulado");
	}
	
class RequestSamplesBehaviour extends OneShotBehaviour {
		
    	private Sample it = null;
    	
			public RequestSamplesBehaviour(Agent a) { 
				super(a);
				this.it = it;
			}
	
			public void action() {
			   	System.out.println("\nInterface Gasoline: enviando requisicao de amostras para gestor..");
				ACLMessage ms = new ACLMessage(ACLMessage.REQUEST);
				AID receiver = new AID("AgenteGestor",AID.ISLOCALNAME); // Send the message to myself
		       				
				ms.setSender(getAID());
				ms.addReceiver(receiver);
				ms.setLanguage(codec.getName());
				ms.setOntology(ontology.getName());

			
				// Fill the content
				Information informante = new Information();
				informante.setName(getAID());
				informante.setStatus("InterfaceDiesel");
				informante.setSample(it);
				
				Requisition req = new Requisition();
				req.setName(getAID());
				req.setType("DIESEL");
				
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
    
class HandleSamplesBahaviour extends CyclicBehaviour {
	
	public HandleSamplesBahaviour(Agent a) { 
		super(a); 
	}

	public void action() {
		ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		if (msg != null) {
			System.out.println("\nINTERFACE DIESEL: Informação recebida.");
			System.out.println(msg);
				try {
					ContentElement conteudo = manager.extractContent(msg);
					
					if (conteudo instanceof Information) {
						Information informante = (Information) conteudo;	
						if (informante.getSample() instanceof Diesel) {
							Diesel item = (Diesel) informante.getSample();
							System.out.println("Diesel Amostra is: "+item.toString());
							jComboBoxSamples.addItem(item.getNameSample());
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
			addBehaviour(new RequestSamplesBehaviour(this));
			this.jButtonGetSamples.setEnabled(false);
			this.jButtonSendSample.setEnabled(true);
	    } else if (Action.equals ("send")){
	    	//addBehaviour(new SendInformGestorBehaviour(this));
			//this.jButtonGetSamples.setEnabled(true);
			this.jButtonSendSample.setEnabled(false);
			this.doDelete();
	    }
	}
	class SendInformGestorBehaviour extends OneShotBehaviour {
		
    	private Sample item = null;
    	
			public SendInformGestorBehaviour(Agent a) { 
				super(a);
				//this.item = it;
			}
	
			public void action() {
				
				System.out.println("\nINTERFACE DIESEL: Enviando para o Agente Gestor..");
				doWait(5000);
				ACLMessage ms = new ACLMessage(ACLMessage.INFORM);
				AID receiver = new AID("AgenteGestor",AID.ISLOCALNAME); // Send the message to myself
		       				
				ms.setSender(getAID());
				ms.addReceiver(receiver);
				ms.setLanguage(codec.getName());
				ms.setOntology(ontology.getName());

				// Fill the content
				Information informante = new Information();
				informante.setName(getAID());
				informante.setStatus("interfaceDiesel");
				//informante.setSample(item);
				
				try {
					manager.fillContent(ms, informante);
				} catch (CodecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				send(ms);
				System.out.println("\nINTERFACE DIESEL: Enviado para o Agente Gestor..");
				doWait(5000);	
			}
    }
}
