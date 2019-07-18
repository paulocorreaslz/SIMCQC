
package Agentes;

//import examples.jess.BasicJessBehaviour;
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import jess.Context;
import jess.Funcall;
import jess.Jesp;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Userfunction;
import jess.Value;
import jess.ValueVector;
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

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class AgenteGestor extends Agent {
	  private ContentManager manager  = (ContentManager) getContentManager();
	    // This agent "speaks" the SL language
	    private Codec      codec    = new SLCodec();
	    // This agent "knows" the Music-Shop ontology
	    private Ontology   ontology = FuelOntology.getInstance();
	    private List ListSamples = new ArrayList();
	    private List ListSamplesGasolineAnalized = new ArrayList();
	    private String ROM, MON,ETA, BEN, SAT, AROM,OLEF;
	    private String AEACTEMP, AEACCAMADA, MASSA20G, MASSA20KG;
	    private int totalfactssample;
  /** 
   * adds the JessBehaviour and that's all.
   */
  protected void setup() {
		manager.registerLanguage(codec);
		manager.registerOntology(ontology);

		// Adiciona comportamento para recuperar dados
		
    // add the behaviour
    // 1 is the number of steps that must be executed at each run of
    // the Jess engine before giving back the control to the Java code
		addBehaviour(new BasicJessBehaviour(this,"examples/jess/JadeAgent.clp",1)); 
 
		//addBehaviour(new HandleInformSamples(this));
		addBehaviour(new HandleRequestBehaviour(this));
  }
  
  private Report createSampleReport(ACLMessage m) {
		ACLMessage men = m;
		ContentElement conteudo;
		Report rell = new Report();
		try {
			conteudo = manager.extractContent(men);
			if (conteudo instanceof Information) {
				Information informante = (Information) conteudo;	
				if (informante.getSample() instanceof Gasoline) {
					rell.setName(getAID());
					rell.setSample((Gasoline)informante.getSample());
					return rell;
				}
			}	
			
		} catch (UngroundedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;	
	}

	public ACLMessage insertSamplesList(ACLMessage m){
		ACLMessage men = m;
		ContentElement conteudo;
		try {
			conteudo = manager.extractContent(men);
			if (conteudo instanceof Information) {
				Information informante = (Information) conteudo;
				ListSamples.add(informante);
			}	
		} catch (UngroundedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return men;
	}
  protected void SendAgentInterface(Sample item){
  	System.out.println("\nEnviando para o Agente Interface..");
		ACLMessage ms = new ACLMessage(ACLMessage.INFORM);
		AID receiver = new AID("AgenteInterfaceGasoline",AID.ISLOCALNAME); // Send the message to myself
     				
		ms.setSender(getAID());
		ms.addReceiver(receiver);
		ms.setLanguage(codec.getName());
		ms.setOntology(ontology.getName());

		// Fill the content
		Information informante = new Information();
		informante.setName(getAID());
		informante.setStatus("Gestor");
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
		System.out.println("\nEnviado para o Agente Interface..");
  }

	public class BasicJessBehaviour extends CyclicBehaviour {
	    // class variables
	    Rete jess; // holds the pointer to jess
	    Agent myAgent; // holds the pointer to this agent
	    int m_maxJessPasses = 0; // holds the maximum number of Jess passes for each run
	    int executedPasses = -1; // to count the number of Jess passes in the previous run
	    Hashtable AIDCache; // holds a local cache to map agent names to AID
		private ContentManager manager  = (ContentManager) getContentManager();
		    // This agent "speaks" the SL language
		private Codec      codec    = new SLCodec();
		    // This agent "knows" the Music-Shop ontology
		private Ontology   ontology = FuelOntology.getInstance();
		//private Report relatorio = null;
	    /**
	     * Creates a <code>BasicJessBehaviour</code> instance
	     *
	     * @param agent the agent that adds the behaviour
	     * @param jessFile the name of the Jess file to be executed
	     */
	    public BasicJessBehaviour(Agent agent, String jessFile) {
	        myAgent = agent;
	        AIDCache = new Hashtable();
	    	manager.registerLanguage(codec);
			manager.registerOntology(ontology);

	        // See info about the Display classes in Section 5 of Jess 4.1b6 Readme.htm
	        //NullDisplay nd = new NullDisplay();
	        // Create a Jess engine
	        jess = new Rete();

	        // The jess.MiscFunctions is no more used since JESS 6.0 (see e-mail of Csaba Tenkes
	        //try {
	        //jess.addUserpackage((Userpackage)Class.forName("jess.MiscFunctions").newInstance());
	        // } catch (Throwable t) { System.out.println(t); }
	        try {
	            // First I define the ACLMessage template
	            jess.executeCommand(ACLJessTemplate());

	            // Then I define the myagent template
	            jess.executeCommand("(deftemplate MyAgent (slot name))");

	            // Then I add the send function
	            jess.addUserfunction(new JessSend(myAgent, this));

	            // Then I assert the fact (Myagent (name <my-name>))
	            jess.executeCommand(
	               "(deffacts MyAgent  (MyAgent (name " +
	                myAgent.getName() + ")))");

	            
	            // Open the file test.clp
	            FileReader fr = new FileReader(jessFile);

	            // Create a parser for the file, telling it where to take input
	            // from and which engine to send the results to
	            Jesp j = new Jesp(fr, jess);

	            // parse and execute one construct, without printing a prompt
	            j.parse(false);
	        } catch (JessException re) {
	            System.out.println(re);
	        } catch (FileNotFoundException e) {
	            System.out.println(e);
	        }
	    }

	    /**
	     * Creates a <code>BasicJessBehaviour</code> instance that limits
	     * the reasoning time of Jess before looking again for arrival of messages.
	     *
	     * @param agent the agent that adds the behaviour
	     * @param jessFile the name of the Jess file to be executed
	     * @param maxJessPasses the maximum number of passes that every run of Jess
	     * can execute before giving again the control to this behaviour;
	     * put <code>0</code> if you do not ever want to stop Jess.
	     */
	    public BasicJessBehaviour(Agent agent, String jessFile, int maxJessPasses) {
	        this(agent, jessFile);
	        m_maxJessPasses = maxJessPasses;
	    }

	    /**
	     * executes the behaviour
	     */
	    public void action() {
	    	//doWait(5000);
	    	String tecnica = "";
	        ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM)); // to keep the ACLMessage
	        Report relato = new Report();
	        if (msg != null) {
	        	System.out.println("\nGestor: recebido ACL Messagem INFORM..");
	        	// wait a message
	        	relato = createSampleReport(msg);
		        if (executedPasses < m_maxJessPasses) {
		            System.out.println(myAgent.getName() +
		                " Aguardando mensagem exclusiva...");
		            msg = myAgent.blockingReceive();
	
		            // assert the fact message in Jess
		            msg = insertSamplesList(msg);		            
		           // makeassert(PropertyAssert(msg));
		            totalfactssample = PropertyAssert(msg);
		            tecnica = GetSampleType(msg);
		            //makeassert(ACL2JessString(msg));
		        	System.out.println("\nGESTOR: Amostra: ["+relato.getSample().getNameSample()+"] fatos ["+totalfactssample+"]");
			          
		        } else {
		            System.out.println(myAgent.getName() +
		                " Checando mensagens pendentes...");
		            msg = myAgent.receive();
	
		            if (msg != null) {
		                // assert the fact message in Jess
		            	 
		            	 msg = insertSamplesList(msg);
		            	 //makeassert(PropertyAssert(msg));
		            	 totalfactssample = PropertyAssert(msg);
		            	 tecnica = GetSampleType(msg);
		            	 //makeassert(ACL2JessString(msg));
		            	 System.out.println("\nGESTOR: Amostra: ["+relato.getSample().getNameSample()+"] fatos ["+totalfactssample+"]");
		            }
	        }

	        // run jess
	        try {
	            if (m_maxJessPasses > 0) {
	                executedPasses = jess.run(m_maxJessPasses);
	          
	                System.out.println("Jess executou: " + executedPasses +
	                    " Procedimentos..");
	            } else {
	                jess.run();
	                System.out.println("GESTOR: Alternativo..");
	            }
	            System.out.println("\nGESTOR: incluindo diagnostico..");
	            if (relato.getSample() instanceof Gasoline) {
	            	Gasoline ga = (Gasoline)relato.getSample();	            
	            
		            if (ga.getTypeSample().equals("Composicao")) {
			            ETA =  ""+jess.fetch("ETAResultado");
		                BEN =  ""+jess.fetch("BENResultado");
		                AROM = ""+jess.fetch("AROMResultado");
		                OLEF = ""+jess.fetch("OLEFResultado");
		                SAT =  ""+jess.fetch("SATResultado");
		                MON =  ""+jess.fetch("MONResultado");
		                ROM =  ""+jess.fetch("ROMResultado");
		                System.out.println("ETAResultado: "+ ETA); 
		                System.out.println("BENResultado: "+ BEN); 	                
		                System.out.println("AROMResultado: "+ AROM); 
		                System.out.println("OLEFResultado: "+ OLEF);
		                System.out.println("SATResultado: "+ SAT); 
		                System.out.println("MONResultado: "+ MON);	                
		                System.out.println("ROMResultado: "+ ROM);
		           		relato.setDiagnosis(""+ETA+"\n"+BEN+"\n"+AROM+"\n"+OLEF+"\n"+SAT+"\n"+MON+"\n"+ROM);
		           		System.out.println("\nGESTOR: diagnostico Composicao incluido..");
		            } 
		            if (ga.getTypeSample().equals("Complementares")){
		            	AEACTEMP =  ""+jess.fetch("AEACTEMPResultado");
	                	AEACCAMADA = ""+jess.fetch("AEACCAMADAResultado");
	                	MASSA20G = ""+jess.fetch("MASSAESPECIFICA20GResultado");
	                	MASSA20KG = ""+jess.fetch("MASSAESPECIFICA20KGResultado");
	                	System.out.println("AEACTEMPResultado: "+ AEACTEMP);
	                	System.out.println("AEACCAMADAResultado: "+ AEACCAMADA);
	                	System.out.println("MASSA20GResultado: "+ MASSA20G);
	                	System.out.println("MASSA20KGResultado: "+ MASSA20KG);
	                 	relato.setDiagnosis(""+AEACTEMP+"\n"+AEACCAMADA+"\n"+MASSA20G+"\n"+MASSA20KG);
		            	System.out.println("\nGESTOR: diagnostico DadosComplementares incluido..");			      	
		            }
		            if (ga.getTypeSample().equals("Destilacao")){
		            
		            	System.out.println("\nGESTOR: diagnostico Destilador incluido..");			      	
		            }
	            }
	           	System.out.println("GESTOR: incluida amostra ["+relato.toString()+"] na lista de amostras analisadas.");	                
               	ListSamplesGasolineAnalized.add(relato);
                
	        } catch (JessException re) {
	            re.printStackTrace(System.err);
	        }
	        }
	    }

		private boolean isEmpty(String string) {
	        return (string == null) || string.equals("");
	    }

	    /**
	      replace a char in a String with a String
	      It is used to convert all the quotation marks in backslash quote
	      before asserting the content of a message in Jess.
	      @return the new String
	    */
	    private String stringReplace(String str, char oldChar, String s) {
	        int len = str.length();
	        int i = 0;
	        int j = 0;
	        int k = 0;
	        char[] val = new char[len];
	        str.getChars(0, len, val, 0); // put chars into val

	        char[] buf = new char[len * s.length()];

	        while (i < len) {
	            if (val[i] == oldChar) {
	                s.getChars(0, s.length(), buf, j);
	                j += s.length();
	            } else {
	                buf[j] = val[i];
	                j++;
	            }

	            i++;
	        }

	        return new String(buf, 0, j);
	    }

	    /**
	      * makeasserts a fact representing an ACLMessage in Jess. It is called after the arrival of a message.
	      */
	    private void makeassert(String fact) {
	        try {
	            jess.executeCommand(fact);
	        } catch (JessException re) {
	            re.printStackTrace(System.err);
	        }
	    }

	    /**
	     * Remove the first and the last character of the string
	     * (if it is a quotation mark) and convert all backslash quote in quote
	     * It is used to convert a Jess content into an ACL message content.
	     */
	    private String unquote(String str) {
	        String t1 = str.trim();

	        if (t1.startsWith("\"")) {
	            t1 = t1.substring(1);
	        }

	        if (t1.endsWith("\"")) {
	            t1 = t1.substring(0, t1.length() - 1);
	        }

	        int len = t1.length();
	        int i = 0;
	        int j = 0;
	        int k = 0;
	        char[] val = new char[len];
	        t1.getChars(0, len, val, 0); // put chars into val

	        char[] buf = new char[len];

	        boolean maybe = false;

	        while (i < len) {
	            if (maybe) {
	                if (val[i] == '\"') {
	                    j--;
	                }

	                buf[j] = val[i];
	                maybe = false;
	                i++;
	                j++;
	            } else {
	                if (val[i] == '\\') {
	                    maybe = true;
	                }

	                buf[j] = val[i];
	                i++;
	                j++;
	            }
	        }

	        return new String(buf, 0, j);
	    }

	    /**
	     * Insert the first and the last character of the string as a quotation mark
	     * Replace all the quote characters into backslash quote.
	     * It is used to convert an ACL message content into a Jess content.
	     */
	    private String quote(java.lang.String str) {
	        //replace all chars " in \ "
	        return "\"" + stringReplace(str, '"', "\\\"") + "\"";
	    }

	    /**
	     * This method searches in the local cache for the full AID of the passed agentName.
	     * If not found it creates a new AID where only the guid is set.
	    **/
	    public AID getAIDFromCache(String agentName) {
	        AID result;
	        result = (AID) AIDCache.get(agentName);

	        if (result == null) {
	            result = new AID(agentName);
	        }

	        return result;
	    }

	    /**
	     * This method searches in the local cache for the full AID of the passed list of agent names.
	     * @param context represents the Rete engine context needed to resolve the value of JESS variables
	     * @param list is a ValueVector of agent names
	     * @return a List of AID
	     */
	    public List getAIDListFromCache(Context context, ValueVector list) {
	        ArrayList l = new ArrayList();

	        for (int i = 0; i < list.size(); i++) {
	            try {
	                l.add(getAIDFromCache(list.get(i).stringValue(context)));
	            } catch (JessException je) {
	            }
	        }

	        return l;
	    }

	    /**
	     * put a new AID in the local cache.
	     * If one exists already with the same agentName, it is overwritten
	     */
	    public void putAIDInCache(AID aid) {
	        AIDCache.put(aid.getName(), aid);
	    }

	    /** @return a String with the deftemplate command to be executed in Jess **/
	    public String ACLJessTemplate() {
	        return "(deftemplate ACLMessage (slot communicative-act) (slot sender) (multislot receiver) (slot reply-with) (slot in-reply-to) (slot envelope) (slot conversation-id) (slot protocol) (slot language) (slot ontology) (slot content) (slot encoding) (multislot reply-to) (slot reply-by))";
	    }

	    /**
	     * @return the ACLMessage representing the passed Jess Fact. This message
	     * will be then sent by the caller.
	     */
	    public ACLMessage JessFact2ACL(Context context, jess.ValueVector vv)
	        throws jess.JessException {
	        // System.err.println("JessFact2ACL "+vv.toString());
	        int perf = ACLMessage.getInteger(vv.get(0).stringValue(context));
	        ACLMessage msg = new ACLMessage(perf);
	        System.out.println("******** Sender ********* " + vv.get(1).toString());

	        if (vv.get(1).stringValue(context) != "nil") {
	            msg.setSender(getAIDFromCache(vv.get(1).stringValue(context)));
	        }

	        if (vv.get(2).toString() != "nil") {
	            List l = getAIDListFromCache(context, vv.get(2).listValue(context));

	            for (int i = 0; i < l.size(); i++)
	                msg.addReceiver((AID) l.get(i));
	        }

	        if (vv.get(3).stringValue(context) != "nil") {
	            msg.setReplyWith(vv.get(3).stringValue(context));
	        }

	        if (vv.get(4).stringValue(context) != "nil") {
	            msg.setInReplyTo(vv.get(4).stringValue(context));
	        }

	        //if (vv.get(5).stringValue(context) != "nil")
	        //  msg.setEnvelope(vv.get(5).stringValue(context));
	        if (vv.get(6).stringValue(context) != "nil") {
	            msg.setConversationId(vv.get(6).stringValue(context));
	        }

	        if (vv.get(7).stringValue(context) != "nil") {
	            msg.setProtocol(vv.get(7).stringValue(context));
	        }

	        if (vv.get(8).stringValue(context) != "nil") {
	            msg.setLanguage(vv.get(8).stringValue(context));
	        }

	        if (vv.get(9).stringValue(context) != "nil") {
	            msg.setOntology(vv.get(9).stringValue(context));
	        }

	        if (vv.get(10).stringValue(context) != "nil") {
	            //FIXME undo replace chars of JessBehaviour.java. Needs to be done better
	            msg.setContent(unquote(vv.get(10).stringValue(context)));
	        }

	        if (vv.get(11).stringValue(context) != "nil") {
	            msg.setEncoding(vv.get(11).stringValue(context));
	        }

	        //System.err.println("JessFact2ACL type is "+vv.get(15).type());
	        if (vv.get(12).toString() != "nil") {
	            List l = getAIDListFromCache(context, vv.get(12).listValue(context));

	            for (int i = 0; i < l.size(); i++)
	                msg.addReplyTo((AID) l.get(i));
	        }

	        if (vv.get(13).stringValue(context) != "nil") {
	            try {
	                msg.setReplyByDate(new Date(Long.parseLong(vv.get(13)
	                                                             .stringValue(context))));
	            } catch (Exception e) { /* do not care */
	            }
	        }

	        return msg;
	    }

	    /**
	     * @return the String representing the facts (even more than one fact is
	     * allowed, but this method just returns one fact)
	     * to be asserted in Jess as a consequence of the receipt of
	     * the passed ACL Message.
	     * The messate content is quoted before asserting the Jess Fact.
	     * It is unquoted by the JessFact2ACL function.
	     */
	    public String ACL2JessString(ACLMessage msg) {
	        String fact;

	        if (msg == null) {
	            return "";
	        }

	        // I create a string that asserts the template fact
	        fact = "(assert (ACLMessage (communicative-act " +
	            ACLMessage.getPerformative(msg.getPerformative());

	        if (msg.getSender() != null) {
	            fact = fact + ") (sender " + msg.getSender().getName();
	            putAIDInCache(msg.getSender());
	        }

	        Iterator i = msg.getAllReceiver();

	        if (i.hasNext()) {
	            fact = fact + ") (receiver ";

	            while (i.hasNext()) {
	                AID aid = (AID) i.next();
	                putAIDInCache(aid);
	                fact = fact + aid.getName();
	            }
	        }

	        if (!isEmpty(msg.getReplyWith())) {
	            fact = fact + ") (reply-with " + msg.getReplyWith();
	        }

	        if (!isEmpty(msg.getInReplyTo())) {
	            fact = fact + ") (in-reply-to " + msg.getInReplyTo();
	        }

	        //if (!isEmpty(msg.getEnvelope()))     fact=fact+") (envelope " + msg.getEnvelope();
	        if (!isEmpty(msg.getConversationId())) {
	            fact = fact + ") (conversation-id " + msg.getConversationId();
	        }

	        if (!isEmpty(msg.getProtocol())) {
	            fact = fact + ") (protocol " + msg.getProtocol();
	        }

	        if (!isEmpty(msg.getLanguage())) {
	            fact = fact + ") (language " + msg.getLanguage();
	        }

	        if (!isEmpty(msg.getOntology())) {
	            fact = fact + ") (ontology " + msg.getOntology();
	        }

	        if (msg.getContent() != null) {
	            fact = fact + ") (content " + quote(msg.getContent());
	        }

	        if (!isEmpty(msg.getEncoding())) {
	            fact = fact + ") (encoding " + msg.getEncoding();
	        }

	        i = msg.getAllReplyTo();

	        if (i.hasNext()) {
	            fact = fact + ") (reply-to ";

	            while (i.hasNext()) {
	                AID aid = (AID) i.next();
	                putAIDInCache(aid);
	                fact = fact + aid.getName();
	            }
	        }

	        if (msg.getReplyByDate() != null) {
	            fact = fact + ") (reply-by " + msg.getReplyByDate().getTime();
	        }

	        fact = fact + ")))";

	        return fact;
	    }

	    public int PropertyAssert(ACLMessage msg) {
	        String fact = null;
	        int i = 0;
	        if (msg == null) {
	            return 0;
	        }
	     //   ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
			if (msg != null) {
				System.out.println("\nGestor: Informação recebida:");
				System.out.println("\nGestor Messagem: "+msg);
    		
			ContentElement conteudo;
			
			try {
				conteudo = manager.extractContent(msg);
				if (conteudo instanceof Information) {
					
					Information info = (Information) conteudo;
					AID owner = info.getName();
					System.out.println("Dono is: "+owner + "\n Tipo: "+info.getSample());
			
					if (info.getSample() instanceof Gasoline) {
						Gasoline item = (Gasoline) info.getSample();
						
						if (item.getProperties().size() > 0) { 
							Iterator it = item.getProperties().iterator();
							
							while (it.hasNext()) {
								Property t = (Property) it.next();
								//fact = fact + ("(assert (Property (name \""+ t.getName() + "\") (value \""+ t.getValue()+"\")))");
								fact = ("(assert (Property (name \""+ t.getName() + "\") (value \""+ t.getValue()+"\")))");
								System.out.println("\nGESTOR: fato incluido: ["+fact+"]");
								makeassert(fact);
								try {
									jess.run();
								} catch (JessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								i++;
							}
						}
					}
				}
				} catch (UngroundedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CodecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
	        }

	        return i;
	    }
	    
	    public String GetSampleType(ACLMessage msg) {
	        String tecnica = "";
	        int i = 0;
	        if (msg == null) {
	            return "nenhuma";
	        }
	     //   ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
			if (msg != null) {
				System.out.println("\nGestor: Informação recebida:");
				System.out.println("\nGestor Messagem: "+msg);
    		
			ContentElement conteudo;
			
			try {
				conteudo = manager.extractContent(msg);
				if (conteudo instanceof Information) {
					
					Information info = (Information) conteudo;
					AID owner = info.getName();
					System.out.println("Dono is: "+owner + "\n Tipo: "+info.getSample());
			
					if (info.getSample() instanceof Gasoline) {
						Gasoline item = (Gasoline) info.getSample();
						tecnica = item.getNameSample();
					}
				}
				} catch (UngroundedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CodecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
	        }

	        return tecnica;
	    }
	    /**
	     * This class implements the Jess userfunction to send ACLMessages
	     * directly from Jess.
	     * It can be used by Jess by using the name <code>send</code>.
	     */
	    public class JessSend implements Userfunction {
	        // data
	        Agent my_agent;
	        BasicJessBehaviour bjb;

	        public JessSend(Agent a, BasicJessBehaviour b) {
	            my_agent = a;
	            bjb = b;
	        }

	        // The name method returns the name by which the function appears in Jess
	        public String getName() {
	            return ("send");
	        }

	        //Called when (send ...) is encountered
	        public Value call(ValueVector vv, Context context)
	            throws JessException {
	            //for (int i=0; i<vv.size(); i++) {
	            //  System.out.println(" parameter " + i + "=" + vv.get(i).toString() +
	            //   " type=" + vv.get(i).type());
	            //  }
	            //////////////////////////////////
	            // Case where JESS calls (send ?m)
	            if (vv.get(1).type() == RU.VARIABLE) {
	                // Uncomment for JESS 5.0 vv =  context.getEngine().findFactByID(vv.get(1).factIDValue(context));
	                vv = context.getEngine().findFactByID(vv.get(1)
	                                                        .factValue(context)
	                                                        .getFactId()); //JESS6.0
	            }
	            //////////////////////////////////
	            // Case where JESS calls (send (assert (ACLMessage ...)))
	            else if (vv.get(1).type() == RU.FUNCALL) {
	                Funcall fc = vv.get(1).funcallValue(context);
	                vv = fc.get(1).factValue(context);
	            }

	            ACLMessage msg = bjb.JessFact2ACL(context, vv);
	            my_agent.send(msg);

	            return Funcall.TRUE;
	        }
	    } // end JessSend class
	} // end JessBehaviour
	class HandleRequestBehaviour extends CyclicBehaviour {
    	
		public HandleRequestBehaviour(Agent a) { 
			super(a); 
		}

		public void action() {
			ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
			if (msg != null) {
	    		try {
						System.out.println("\nGESTOR: Recebendo REQUEST.");
						ContentElement conteudo = manager.extractContent(msg);
								
								if (conteudo instanceof Requisition) {
								
									Requisition info = (Requisition) conteudo;
									AID owner = info.getName();
									System.out.println("Owner is: "+ owner + "\n Tipo:");
							
									if (info.getType().equals("GASOLINA")) {	
										System.out.println("GESTOR: Tipo Gasolina Amostra");
										addBehaviour(new InformAnalisedSamplesInterfaceGasolineBehaviour(ListSamplesGasolineAnalized));
									} else if (info.getType().equals("RELATORIO")) {	
										System.out.println("GESTOR: Tipo relatorio");
										addBehaviour(new InformAnalisedSamplesGasolineBehaviour(ListSamplesGasolineAnalized));
									} else if (info.getType().equals("CONVERSOR")) {	
										System.out.println("GESTOR: Tipo CONVERSOR");
										addBehaviour(new InformAnalisedSamplesConversorBehaviour(ListSamplesGasolineAnalized));
									
									} else {
										
									}
								}
								
	
	    		} catch(Exception e) { 
	    			e.printStackTrace(); 
	    		}
	    	} else {
	    		block();
	    	}
		}

	}
class InformSamplesGasolineBehaviour extends OneShotBehaviour {
		
    	private List lista = null;
    	
			public InformSamplesGasolineBehaviour(List li) { 
				//super(a);
				this.lista = li;
			}
	
			public void action() {
				
				List listaAmostra = new ArrayList();
				listaAmostra = lista;
				
				//Sample amostra = new Sample();
				 Gasoline gas = new Gasoline();	
				
				Iterator it = listaAmostra.iterator();
				int i = 0;
				while (it.hasNext()) {
					Report inf = (Report) it.next();
					gas = (Gasoline) inf.getSample();
					sendMessage(gas);
					i++;
				}
				
			}
		
			public void sendMessage(Sample item){
				doWait(1000);
				System.out.println("\nGestor: enviando amostras para interface gasoline..");
				ACLMessage ms = new ACLMessage(ACLMessage.INFORM);
				AID receiver = new AID("AgenteInterfaceGasoline",AID.ISLOCALNAME); // Send the message to myself
		       				
				ms.setSender(getAID());
				ms.addReceiver(receiver);
				ms.setLanguage(codec.getName());
				ms.setOntology(ontology.getName());

			
				// Fill the content
				Report informante = new Report();
				informante.setName(getAID());
				//informante.setStatus("Gestor");
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
				doWait(1000);
			}
    }

class InformAnalisedSamplesGasolineBehaviour extends OneShotBehaviour {
	
	private List lista = null;
	
		public InformAnalisedSamplesGasolineBehaviour(List li) { 
			//super(a);
			this.lista = li;
		}

		public void action() {
			
			List listaAmostra = new ArrayList();
			listaAmostra = lista;
			
			//Sample amostra = new Sample();
			Gasoline gas = new Gasoline();	
			
			Iterator it = listaAmostra.iterator();
			int i = 0;
			while (it.hasNext()) {
				Report inf = (Report) it.next();
				gas = (Gasoline) inf.getSample();
				sendMessage(gas, inf.getDiagnosis());
				i++;
			}
			
		}
	
		public void sendMessage(Sample item, String diag){
			doWait(1000);
			System.out.println("\nGestor: enviando amostras para interface Report..");
			ACLMessage ms = new ACLMessage(ACLMessage.INFORM);
			AID receiver = new AID("AgenteInterfaceReport",AID.ISLOCALNAME); // Send the message to myself
	       				
			ms.setSender(getAID());
			ms.addReceiver(receiver);
			ms.setLanguage(codec.getName());
			ms.setOntology(ontology.getName());

		
			// Fill the content
			Report informante = new Report();
			informante.setName(getAID());
			informante.setDiagnosis(diag);
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
			doWait(1000);
		}
} 
class InformAnalisedSamplesConversorBehaviour extends OneShotBehaviour {
	
	private List lista = null;
	
		public InformAnalisedSamplesConversorBehaviour(List li) { 
			//super(a);
			this.lista = li;
		}

		public void action() {
			
			List listaAmostra = new ArrayList();
			listaAmostra = lista;
			
			//Sample amostra = new Sample();
			Gasoline gas = new Gasoline();	
			
			Iterator it = listaAmostra.iterator();
			int i = 0;
			while (it.hasNext()) {
				Report inf = (Report) it.next();
				gas = (Gasoline) inf.getSample();
				sendMessage(gas, inf.getDiagnosis());
				i++;
			}
			
		}
	
		public void sendMessage(Sample item, String diag){
			doWait(1000);
			System.out.println("\nGestor: enviando amostras para interface Report..");
			ACLMessage ms = new ACLMessage(ACLMessage.INFORM);
			AID receiver = new AID("AgenteConversor",AID.ISLOCALNAME); // Send the message to myself
	       				
			ms.setSender(getAID());
			ms.addReceiver(receiver);
			ms.setLanguage(codec.getName());
			ms.setOntology(ontology.getName());

		
			// Fill the content
			Report informante = new Report();
			informante.setName(getAID());
			informante.setDiagnosis(diag);
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
			doWait(1000);
		}
} 
class InformAnalisedSamplesInterfaceGasolineBehaviour extends OneShotBehaviour {
	
	private List lista = null;
	
		public InformAnalisedSamplesInterfaceGasolineBehaviour(List li) { 
			//super(a);
			this.lista = li;
		}

		public void action() {
			
			List listaAmostra = new ArrayList();
			listaAmostra = lista;
			
			//Sample amostra = new Sample();
			Gasoline gas = new Gasoline();	
			
			Iterator it = listaAmostra.iterator();
			int i = 0;
			while (it.hasNext()) {
				Report inf = (Report) it.next();
				gas = (Gasoline) inf.getSample();
				sendMessage(gas, inf.getDiagnosis());
				i++;
			}
			
		}
	
		public void sendMessage(Sample item, String diag){
			doWait(1000);
			System.out.println("\nGestor: enviando amostras para interface Report..");
			ACLMessage ms = new ACLMessage(ACLMessage.INFORM);
			AID receiver = new AID("AgenteInterfaceGasoline",AID.ISLOCALNAME); // Send the message to myself
	       				
			ms.setSender(getAID());
			ms.addReceiver(receiver);
			ms.setLanguage(codec.getName());
			ms.setOntology(ontology.getName());

		
			// Fill the content
			Report informante = new Report();
			informante.setName(getAID());
			informante.setDiagnosis(diag);
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
			doWait(1000);
		}
}  
}

