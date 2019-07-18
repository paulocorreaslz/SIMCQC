package teste;

/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


/*
 * AbsoluteLayoutDemo.java requires no other files.
 */

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
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

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
import javax.swing.JRadioButton;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;
import java.awt.BorderLayout;

public class AgenteInterface extends GuiAgent implements ActionListener {
	
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
	}
	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="52,-7"
	private JPanel jContentPane = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItemAmostras = null;
	private JMenuItem jMenuItemRelatorio = null;
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
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */


	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(576, 397));
			jFrame.setContentPane(getJContentPane());
		}
		return jFrame;
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
			jContentPane.add(getJMenu(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.add(getJMenuItemAmostras());
			jMenu.add(getJMenuItemRelatorio());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItemAmostras	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemAmostras() {
		if (jMenuItemAmostras == null) {
			jMenuItemAmostras = new JMenuItem("Amostras");
		}
		return jMenuItemAmostras;
	}

	/**
	 * This method initializes jMenuItemRelatorio	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemRelatorio() {
		if (jMenuItemRelatorio == null) {
			jMenuItemRelatorio = new JMenuItem("Relatorio");
		}
		return jMenuItemRelatorio;
	}

	/**
	 * This method initializes jButtonGetSamples	
	 * 	
	 * @return javax.swing.JButton	
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
									//if (jComboBoxSamples.getItemAt(i) == item.getNameSample()) {
									//	existe = true;
									///	break;
									//}
								//}
								if (existe){
								//	jComboBoxSamples.addItem(item.getNameSample());
								}
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
							//jComboBoxSamples.addItem(item.getNameSample());
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
		//	this.jButtonGetSamples.setEnabled(true);
		//	this.jButtonSendSample.setEnabled(true);
	    } else if (Action.equals ("send")){
	    	//addBehaviour(new SendInformGestorBehaviour(this));
			//this.jButtonGetSamples.setEnabled(true);
			//this.jButtonSendSample.setEnabled(true);
	    }
	}
	class SendInformGestorBehaviour extends OneShotBehaviour {
		
    	private Sample item = null;
    	
			public SendInformGestorBehaviour(Agent a) { 
				super(a);
				//this.item = it;
			}
	
			public void action() {
				
				System.out.println("\nINTERFACE GASOLINE: Enviando para o Agente Gestor..");
				doWait(5000);
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
				System.out.println("\nINTERFACE GASOLINE: Enviado para o Agente Gestor..");
				doWait(5000);	
			}
    }
}
