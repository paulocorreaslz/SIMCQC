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

import Agentes.InterfaceGasoline.HandleSamplesBahaviour;
import Agentes.InterfaceGasoline.RequestSamplesBehaviour;
import Ontologia.Alcohol;
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
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class InterfaceAlcohol extends GuiAgent implements ActionListener {
	
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
	private JLabel jLabelTeorAEAC = null;
	private JLabel jLabelTemp = null;
	private JTextField txtAEACTemp = null;
	private JLabel jLabelAEACaumento = null;
	private JButton jButtonSendSample = null;
	private JLabel jLabelTeorGas = null;
	private JLabel jLabelPh = null;
	private JLabel jLabelCondEle = null;
	private JTextField txtTeorAlcoolINPM = null;
	private JLabel jLabelTeorGas1 = null;
	private JTextField txtTeorGasA = null;
	private JLabel jLabelTeorGas11 = null;
	private JTextField txtTeorGasML = null;
	private JTextField txtPotHidro = null;
	private JTextField txtCondEle = null;
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
			txtMassa20gcm.setBounds(new Rectangle(204, 78, 132, 20));
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
			jRadioButtonHV.setBounds(new Rectangle(206, 52, 50, 21));
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
			jRadioButtonDD.setBounds(new Rectangle(264, 52, 64, 21));
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
			txtMassa20kgm.setBounds(new Rectangle(205, 125, 130, 20));
		}
		return txtMassa20kgm;
	}

	/**
	 * This method initializes txtAEACTemp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtAEACTemp() {
		if (txtAEACTemp == null) {
			txtAEACTemp = new JTextField();
			txtAEACTemp.setBounds(new Rectangle(430, 53, 120, 20));
		}
		return txtAEACTemp;
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
	 * This method initializes txtTeorAlcoolINPM	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtTeorAlcoolINPM() {
		if (txtTeorAlcoolINPM == null) {
			txtTeorAlcoolINPM = new JTextField();
			txtTeorAlcoolINPM.setBounds(new Rectangle(434, 101, 115, 20));
		}
		return txtTeorAlcoolINPM;
	}

	/**
	 * This method initializes txtTeorGasA	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtTeorGasA() {
		if (txtTeorGasA == null) {
			txtTeorGasA = new JTextField();
			txtTeorGasA.setBounds(new Rectangle(21, 204, 118, 20));
		}
		return txtTeorGasA;
	}

	/**
	 * This method initializes txtTeorGasML	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtTeorGasML() {
		if (txtTeorGasML == null) {
			txtTeorGasML = new JTextField();
			txtTeorGasML.setBounds(new Rectangle(19, 248, 120, 20));
		}
		return txtTeorGasML;
	}

	/**
	 * This method initializes txtPotHidro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPotHidro() {
		if (txtPotHidro == null) {
			txtPotHidro = new JTextField();
			txtPotHidro.setBounds(new Rectangle(210, 186, 128, 20));
		}
		return txtPotHidro;
	}

	/**
	 * This method initializes txtCondEle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCondEle() {
		if (txtCondEle == null) {
			txtCondEle = new JTextField();
			txtCondEle.setBounds(new Rectangle(441, 183, 113, 20));
		}
		return txtCondEle;
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
	        jLabelTeorGas11 = new JLabel();
	        jLabelTeorGas11.setBounds(new Rectangle(14, 227, 128, 16));
	        jLabelTeorGas11.setText("Teor (mL/L) V=2x a+1");
	        jLabelTeorGas1 = new JLabel();
	        
	        jLabelTeorGas1.setBounds(new Rectangle(4, 183, 193, 16));
	        jLabelTeorGas1.setText("a = aumento camada aquosa (ml)");
	        jLabelCondEle = new JLabel();
	        jLabelCondEle.setBounds(new Rectangle(434, 161, 129, 16));
	        jLabelCondEle.setText("Condutividade Eletrica");
	        jLabelPh = new JLabel();
	        jLabelPh.setBounds(new Rectangle(203, 161, 169, 16));
	        jLabelPh.setText("Potencial Hidrogeiônico (pH)");
	        jLabelTeorGas = new JLabel();
	        jLabelTeorGas.setBounds(new Rectangle(23, 161, 100, 16));
	        jLabelTeorGas.setText("Teor de Gasolina");
	        jLabelAEACaumento = new JLabel();
	        jLabelAEACaumento.setBounds(new Rectangle(457, 79, 66, 16));
	        jLabelAEACaumento.setText("TA - ºINPM");
	        jLabelTemp = new JLabel();
	        jLabelTemp.setBounds(new Rectangle(461, 31, 54, 16));
	        jLabelTemp.setText("Temp ºC");
	        jLabelTeorAEAC = new JLabel();
	        jLabelTeorAEAC.setBounds(new Rectangle(447, 10, 93, 16));
	        jLabelTeorAEAC.setText("Teor de Alcool ");
	        jLabelMEsp20kgm = new JLabel();
	        jLabelMEsp20kgm.setBounds(new Rectangle(194, 105, 158, 16));
	        jLabelMEsp20kgm.setText("M. Especifica a 20ºC Kg/m³");
	        jLabelMass20gcm = new JLabel();
	        jLabelMass20gcm.setBounds(new Rectangle(193, 31, 158, 16));
	        jLabelMass20gcm.setText("M. Especifica a 20ºC g/cm³");
	        jLabelcor = new JLabel();
	        jLabelcor.setBounds(new Rectangle(44, 90, 38, 16));
	        jLabelcor.setText("Cor:");
	        jLabelMassEsp = new JLabel();
	        jLabelMassEsp.setBounds(new Rectangle(210, 10, 114, 16));
	        jLabelMassEsp.setText("Massa Especifica");
	        jLabelAspect = new JLabel();
	        jLabelAspect.setText("Aspecto e Cor");
	        jLabelAspect.setBounds(new Rectangle(29, 68, 94, 16));
	        jLabelSamples = new JLabel();
	        jLabelSamples.setBounds(new Rectangle(6, 8, 109, 16));
	        jLabelSamples.setText("Nome da Amostra:");
	        JFrame frame = new JFrame("Alcool");
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
	        pane.add(jLabelTeorAEAC, null);
	        pane.add(jLabelTemp, null);
	        pane.add(getTxtAEACTemp(), null);
	        pane.add(jLabelAEACaumento, null);
	        pane.add(getJButtonSendSample(), null);
	        pane.add(jLabelTeorGas, null);
	        pane.add(jLabelPh, null);
	        pane.add(jLabelCondEle, null);
	        pane.add(getTxtTeorAlcoolINPM(), null);
	        pane.add(jLabelTeorGas1, null);
	        pane.add(getTxtTeorGasA(), null);
	        pane.add(jLabelTeorGas11, null);
	        pane.add(getTxtTeorGasML(), null);
	        pane.add(getTxtPotHidro(), null);
	        pane.add(getTxtCondEle(), null);
	        
                  
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
				informante.setStatus("InterfaceGasoline");
				informante.setSample(it);
				
				Requisition req = new Requisition();
				req.setName(getAID());
				req.setType("ALCOHOL");
				
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
			System.out.println("\nINTERFACE ALCOHOL: Informação recebida.");
			System.out.println(msg);
				try {
					ContentElement conteudo = manager.extractContent(msg);
					
					if (conteudo instanceof Information) {
						Information informante = (Information) conteudo;	
						if (informante.getSample() instanceof Alcohol) {
							Alcohol item = (Alcohol) informante.getSample();
							System.out.println("Alcohol Amostra is: "+item.toString());
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
				
				System.out.println("\nINTERFACE ALCOHOL: Enviando para o Agente Gestor..");
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
				informante.setStatus("interfacegasoline");
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
				System.out.println("\nINTERFACE ALCOHOL: Enviado para o Agente Gestor..");
				doWait(5000);	
			}
    }
}

