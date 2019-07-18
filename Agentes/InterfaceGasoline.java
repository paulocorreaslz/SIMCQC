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
import jade.util.leap.ArrayList;
import jade.util.leap.List;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Agentes.AgenteIrox.InformSupervisorBehaviour;
import Ontologia.Alcohol;
import Ontologia.Biodiesel;
import Ontologia.Diesel;
import Ontologia.FuelOntology;
import Ontologia.Gasoline;
import Ontologia.Information;
import Ontologia.Property;
import Ontologia.Report;
import Ontologia.Requisition;
import Ontologia.Sample;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.util.Date;

import javax.swing.JRadioButton;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class InterfaceGasoline extends GuiAgent implements ActionListener {
	
    /**
	 */
	private static final long serialVersionUID = 5228641897287860364L;
	private ContentManager manager  = (ContentManager) getContentManager();
	    // This agent "speaks" the SL language
	private Codec      codec    = new SLCodec();
	    // This agent "knows" the Music-Shop ontology
	private Ontology   ontology = FuelOntology.getInstance();
	
	
   
    protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		this.doActivate();
	}
	//private JComboBox jcAmostras = new JComboBox();
   // private JButton btnRequestSamples = new JButton("Recuperar Amostras");
	private JLabel jLabelSamples = null;
	private JComboBox jComboBoxSamples = null;
	private JLabel jLabelAspect = null;
	private JButton jButtonGetSamples = null;
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
	private JTextField txtAEACa = null;
	private JButton jButtonSendSample = null;
	JFrame frame = new JFrame("Gasolina");
	public static void addComponentsToPane(Container pane) {
        pane.setLayout(null);
    }

	private void SendComplementaryData(){
		
	    Sample amostra = new Sample();
	    List lista_propriedades = new ArrayList();
	    Property pAEACTemp = new Property();				  	  
	    Property pAEACa = new Property();
	    Property pMassEsp20g = new Property();
	    Property pMassEsp20kg = new Property();

	    Gasoline gas = new Gasoline();  
		
		Date data = new Date();

		amostra.setNameSample(jComboBoxSamples.getSelectedItem().toString()); // seta o nome da amostra
    	amostra.setDateSample(""+data.getDay()+"/"+data.getMonth()+"/"+data.getYear());
    	
    	pAEACTemp.setName("AEAC-Temp");
    	pAEACTemp.setValue((new Double(getTxtAEACTemp().getText())));
    	lista_propriedades.add(pAEACTemp);
    	
    	pAEACa.setName("AEAC-CAMADAAQUOSA");
    	pAEACa.setValue((new Double(getTxtAEACa().getText())));
    	lista_propriedades.add(pAEACa);
    	
    	pMassEsp20g.setName("MASSAESPECIFICA20G");
    	pMassEsp20g.setValue((new Double(getTxtMassa20gcm().getText())));
    	lista_propriedades.add(pMassEsp20g);
    	
    	pMassEsp20kg.setName("MASSAESPECIFICA20KG");
    	pMassEsp20kg.setValue((new Double(getTxtMassa20kgm().getText())));
    	lista_propriedades.add(pMassEsp20kg);
    	
    	
		gas.setTypeSample("Complementares");
		System.out.println("Nome da Amostra: "+jComboBoxSamples.getSelectedItem().toString());
		//.substring(1, irox_nome.length()-1)
		
		gas.setNameSample(jComboBoxSamples.getSelectedItem().toString());
		gas.setDateSample(""+data.getDay()+"/"+data.getMonth()+"/"+data.getYear());
		
		gas.setProperties(lista_propriedades);				
		addBehaviour(new SendInformGestorBehaviour(this,gas));
	
		
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
			jButtonGetSamples.setBounds(new Rectangle(460, 346, 147, 37));
			jButtonGetSamples.setText("Capturar Amostras");
		}
		return jButtonGetSamples;
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
			txtMassa20gcm.setBounds(new Rectangle(212, 76, 132, 20));
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
			jRadioButtonHV.setBounds(new Rectangle(214, 50, 50, 21));
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
			jRadioButtonDD.setBounds(new Rectangle(272, 50, 64, 21));
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
			txtMassa20kgm.setBounds(new Rectangle(213, 123, 130, 20));
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
			txtAEACTemp.setBounds(new Rectangle(429, 53, 120, 20));
		}
		return txtAEACTemp;
	}

	/**
	 * This method initializes txtAEACa	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtAEACa() {
		if (txtAEACa == null) {
			txtAEACa = new JTextField();
			txtAEACa.setBounds(new Rectangle(432, 104, 116, 20));
		}
		return txtAEACa;
	}

	/**
	 * This method initializes jButtonSendSample	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSendSample() {
		if (jButtonSendSample == null) {
			jButtonSendSample = new JButton();
			jButtonSendSample.setBounds(new Rectangle(460, 300, 147, 39));
			jButtonSendSample.setText("Enviar Amostra");
		}
		return jButtonSendSample;
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
	        jLabelAEACaumento = new JLabel();
	        jLabelAEACaumento.setBounds(new Rectangle(396, 80, 196, 16));
	        jLabelAEACaumento.setText("a = aumento camada aquosa (ml)");
	        jLabelTemp = new JLabel();
	        jLabelTemp.setBounds(new Rectangle(460, 31, 54, 16));
	        jLabelTemp.setText("Temp ºC");
	        jLabelTeorAEAC = new JLabel();
	        jLabelTeorAEAC.setBounds(new Rectangle(406, 9, 162, 16));
	        jLabelTeorAEAC.setText("Teor de Alcool Etílico AEAC");
	        jLabelMEsp20kgm = new JLabel();
	        jLabelMEsp20kgm.setBounds(new Rectangle(202, 103, 158, 16));
	        jLabelMEsp20kgm.setText("M. Especifica a 20ºC Kg/m³");
	        jLabelMass20gcm = new JLabel();
	        jLabelMass20gcm.setBounds(new Rectangle(201, 29, 158, 16));
	        jLabelMass20gcm.setText("M. Especifica a 20ºC g/cm³");
	        jLabelcor = new JLabel();
	        jLabelcor.setBounds(new Rectangle(44, 90, 38, 16));
	        jLabelcor.setText("Cor:");
	        jLabelMassEsp = new JLabel();
	        jLabelMassEsp.setBounds(new Rectangle(218, 8, 114, 16));
	        jLabelMassEsp.setText("Massa Especifica");
	        jLabelAspect = new JLabel();
	        jLabelAspect.setText("Aspecto e Cor");
	        jLabelAspect.setBounds(new Rectangle(29, 68, 94, 16));
	        jLabelSamples = new JLabel();
	        jLabelSamples.setBounds(new Rectangle(6, 8, 109, 16));
	        jLabelSamples.setText("Nome da Amostra:");
	        
	        frame.setDefaultCloseOperation(this.frame.DISPOSE_ON_CLOSE);

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
	        pane.add(getTxtAEACa(), null);
	        pane.add(getJButtonSendSample(), null);
	        
                  
	        jButtonGetSamples.addActionListener(this);
	        jButtonGetSamples.setActionCommand("request");
	    	
	        jButtonSendSample.addActionListener(this);
	        jButtonSendSample.setActionCommand("send");
	   
	        
	        jButtonSendSample.setEnabled(false);
	        //addBehaviour(new HandleSamplesBahaviour(this));
	        
	        addBehaviour(new HandleAnalisedSamplesBahaviour(this));
	        getBoxAspectoCor().addItem("Limpido s/ impurezas");
	        getBoxAspectoCor().addItem("Limpido c/ impurezas");
	        getBoxAspectoCor().addItem("Turvo s/ impurezas");
	        getBoxAspectoCor().addItem("Turvo c/ impurezas");
	        getBoxAspectoCor().addItem("Azulado");
	}
	class HandleAnalisedSamplesBahaviour extends CyclicBehaviour {
		
		public HandleAnalisedSamplesBahaviour(Agent a) { 
			super(a); 
		}

		public void action() {
			ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
			if (msg != null) {
				System.out.println("\nINTERFACE Gasoline: Informação recebida.");
				System.out.println(msg);
					try {
						ContentElement conteudo = manager.extractContent(msg);
						
						if (conteudo instanceof Report) {
							Report informante = (Report) conteudo;	
							if (informante.getSample() instanceof Gasoline) {
								Gasoline item = (Gasoline) informante.getSample();
								System.out.println("Gasolina Amostra is: "+item.toString());
								boolean existe = false;
								
								//for (int i=0; i < getJComboBoxSamples().getItemCount(); i++){
								//	if (jComboBoxSamples.getItemAt(i) == item.getNameSample()) {
								//		existe = true;
								//		break;
								//	}
								//}
								//if (existe){
									jComboBoxSamples.addItem(item.getNameSample());
								//}
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
class RequestSamplesBehaviour extends OneShotBehaviour {
		
    	private Sample it = null;
    	
			public RequestSamplesBehaviour(Agent a) { 
				super(a);
				this.it = it;
			}
	
			public void action() {
			   	System.out.println("\nInterface Gasoline: enviando requisicao de amostras para gestor..");
				ACLMessage ms = new ACLMessage(ACLMessage.REQUEST);
				AID receiver = new AID("AgenteCognitivo",AID.ISLOCALNAME); // Send the message to myself
		       				
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
				req.setType("GASOLINA");
				
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
			System.out.println("\nINTERFACE GASOLINE: Informação recebida.");
			System.out.println(msg);
				try {
					ContentElement conteudo = manager.extractContent(msg);
					
					if (conteudo instanceof Information) {
						Information informante = (Information) conteudo;	
						if (informante.getSample() instanceof Gasoline) {
							Gasoline item = (Gasoline) informante.getSample();
							System.out.println("Gasolina Amostra is: "+item.toString());
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
			this.jButtonGetSamples.setEnabled(true);
			this.jButtonSendSample.setEnabled(true);
	    } else if (Action.equals ("send")){
	    	//addBehaviour(new SendInformGestorBehaviour(this));
			//this.jButtonGetSamples.setEnabled(true);
			this.jButtonSendSample.setEnabled(true);
			SendComplementaryData();
	    }
	}
	class SendInformGestorBehaviour extends OneShotBehaviour {
		
    	private Sample item = null;
    	
			public SendInformGestorBehaviour(Agent a, Sample it) { 
				super(a);
				this.item = it;
			}
	
			public void action() {
				
				System.out.println("\nINTERFACE GASOLINE: Enviando amostra manual para o Agente Gestor..");
				//doWait(5000);
				ACLMessage ms = new ACLMessage(ACLMessage.INFORM);
				AID receiver = new AID("AgenteCognitivo",AID.ISLOCALNAME); // Send the message to myself
		       				
				ms.setSender(getAID());
				ms.addReceiver(receiver);
				ms.setLanguage(codec.getName());
				ms.setOntology(ontology.getName());

				// Fill the content
				Information informante = new Information();
				informante.setName(getAID());
				informante.setStatus("interfacegasoline");
				informante.setSample(item);
				
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
				System.out.println("\nINTERFACE GASOLINE: Amostra manual enviada para o Agente Gestor..");
				//doWait(5000);	
			}
    }

}
