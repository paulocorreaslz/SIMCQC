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
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import Ontologia.Alcohol;
import Ontologia.Biodiesel;
import Ontologia.Diesel;
import Ontologia.FuelOntology;
import Ontologia.Gasoline;
import Ontologia.Information;
import Ontologia.Sample;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class AgenteSupervisor extends Agent {

	  private ContentManager manager  = (ContentManager) getContentManager();
	    // This agent "speaks" the SL language
	    private Codec      codec    = new SLCodec();
	    // This agent "knows" the Music-Shop ontology
	    private Ontology   ontology = FuelOntology.getInstance();


		protected void setup() {
				manager.registerLanguage(codec);
				manager.registerOntology(ontology);
		
				// Adiciona comportamento para recuperar dados
				addBehaviour(new HandleInformBehaviour(this));
				
	    }
		// Classe interna para recuperar dados - Comportamento ciclico
		class HandleInformBehaviour extends CyclicBehaviour {
			
				public HandleInformBehaviour(Agent a) { 
					super(a); 
					//Agent My = a;
				}
		
				public void action() {
					ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
					if (msg != null) {
						System.out.println("\nSupervisor: Informação recebida. Mensagem :");
						System.out.println("Sup Mensagem:"+msg);
		    		try {
							ContentElement conteudo = manager.extractContent(msg);
							
							if (conteudo instanceof Information) {
							
								Information info = (Information) conteudo;
								AID owner = info.getName();
								System.out.println("Owner is: "+owner + "\n Tipo: "+info.getSample());
						
								if (info.getSample() instanceof Gasoline) {
									
									Gasoline item = (Gasoline) info.getSample();
									System.out.println("Gasolina Amostra is: "+item.toString());
									if (item.getTypeSample().equals("Composicao")) {
										addBehaviour(new SendReplyIroxBehaviour(this.myAgent,item));
									} else if (item.getTypeSample().equals("Destilador")) {
									
									}
									addBehaviour(new SendInformGestorBehaviour(this.myAgent,item));
								} else if (info.getSample() instanceof Diesel) {
								
									Diesel item = (Diesel) info.getSample();
									System.out.println("Diesel Amostra is: "+item.toString());
									addBehaviour(new SendInformGestorBehaviour(this.myAgent,item));
									
								} else if (info.getSample() instanceof Alcohol) {
									
									Alcohol item = (Alcohol) info.getSample();
									System.out.println("Alcool Amostra is: "+item.toString());
									addBehaviour(new SendInformGestorBehaviour(this.myAgent,item));
									
								} else if (info.getSample() instanceof Biodiesel) {
								
									Biodiesel item = (Biodiesel) info.getSample();
									System.out.println("Biodiesel Amostra is: "+item.toString());
									addBehaviour(new SendInformGestorBehaviour(this.myAgent,item));
										
								} else {
									System.out.println("conteudo "+conteudo);
									
								}
								
								
						} else {
		    				System.out.println("Supervisor: predicate desconhecido "+conteudo.getClass().getName());
		    			}
		    		}
		    		catch (UngroundedException ue) {
		    			// The message content includes variables --> It must be an abs descriptor 
		    		}	
		    		catch(Exception e) { 
		    			e.printStackTrace(); 
		    		}
		    	}
		    	else {
		    		block();
		    	}
				}
				
		}
		
		 class SendInformGestorBehaviour extends OneShotBehaviour {
				private Sample it;
				
				public SendInformGestorBehaviour(Agent a, Sample it) { 
					super(a); 
					this.it = it;
				}
		
				public void action() {
			    	try {
			    			doWait(5000);
							System.out.println("\nAgente Super: Informando o Gestor: "+it);
							
							// Prepare the message
							ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
							AID receiver = new AID("AgenteCognitivo",AID.ISLOCALNAME); // Send the message to myself
					       				
							msg.setSender(getAID());
							msg.addReceiver(receiver);
							msg.setLanguage(codec.getName());
							msg.setOntology(ontology.getName());
		
							// Fill the content
							Information informante = new Information();
							informante.setName(getAID());
							informante.setStatus("Supervisor");
							informante.setSample(it);
							
							manager.fillContent(msg, informante);
							send(msg);
							doWait(5000);
			    	} 
			    	catch(Exception e) { 
			    		e.printStackTrace(); 
			    	}
				}
	    }
		 class SendReplyIroxBehaviour extends OneShotBehaviour {
				private Sample it;
				
				public SendReplyIroxBehaviour(Agent a, Sample it) { 
					super(a); 
					this.it = it;
				}
		
				public void action() {
			    	try {
							System.out.println("\nAgente Super: Informando o Irox a Confirmacao AGREE: "+it);
							
							// Prepare the message
							ACLMessage msg = new ACLMessage(ACLMessage.AGREE);
							AID receiver = new AID("AgenteIrox",AID.ISLOCALNAME); // Send the message to myself
					       				
							msg.setSender(getAID());
							msg.addReceiver(receiver);
							msg.setLanguage(codec.getName());
							msg.setOntology(ontology.getName());
		
							// Fill the content
							Information informante = new Information();
							informante.setName(getAID());
							informante.setStatus("Supervisor");
							informante.setSample(it);
							
							manager.fillContent(msg, informante);
							send(msg);
							
			    	} 
			    	catch(Exception e) { 
			    		e.printStackTrace(); 
			    	}
				}
	    }
}