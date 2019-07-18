package Agentes;

import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.ArrayList;
import jade.util.leap.List;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import Agentes.AgenteIrox.InformSupervisorBehaviour;
import Agentes.AgenteSupervisor.SendInformGestorBehaviour;
import Agentes.AgenteSupervisor.SendReplyIroxBehaviour;
import Ontologia.Alcohol;
import Ontologia.Biodiesel;
import Ontologia.Diesel;
import Ontologia.FuelOntology;
import Ontologia.Gasoline;
import Ontologia.Information;
import Ontologia.Property;
import Ontologia.Sample;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class AgenteDestilador extends Agent {

    private ContentManager manager  = (ContentManager) getContentManager();
    // This agent "speaks" the SL language
    private Codec      codec    = new SLCodec();
    // This agent "knows" the Music-Shop ontology
    private Ontology   ontology = FuelOntology.getInstance();
    
    private String getPathFile(){
        Properties properties = new Properties();   
        try {   
          properties.load(new FileInputStream("configuration"));   
          String destilador = properties.getProperty("destilador");   
          System.out.println("Valor do campo: " + destilador);   
          return destilador;
        } catch (IOException e) {   
            System.out.println("Erro ao ler arquivo." + e.getMessage());
            return null;
        }   
    }
    private void ReadData(){
    	String caminho = getPathFile();
    	File file = new File(caminho);
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    
	    String nulo, nomeamostra = null, nome1 = null, nome2 = null, dataamostra= null;
	    String FBP = null;
	    String BP10= null, BP50= null, BP90= null, PFE= null, residuo= null;
	    String dest_fbp10 = null;
	    
	    String line  = null;
	    
	    try {
	    
	      fis = new FileInputStream(file);

	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);

		  int linhaAmostra = 1;
		  int contAmostras = 1;
	      
	      while (dis.available() != 0) {
	    	  	line = dis.readLine();
	    	  	// pegar o nome da amostra
		    	if (linhaAmostra==63) {  
		    		System.out.println("Destilador: Amostra n: " + contAmostras);			    	  
		        	StringTokenizer st = new StringTokenizer(line.toString(), ";"); 
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	nomeamostra = st.nextToken();	
		        	nomeamostra = nomeamostra.replace(" ", "");
		        	if (nomeamostra.length() > 8) {
		        		nome1 = nomeamostra.substring(0, 6);
		        		nome2 = nomeamostra.substring(6, 9);
		        		nomeamostra = nome1+"-"+nome2;
		        	}
		    	}
		    	// data da amostra
		    	if (linhaAmostra==68){
		    		StringTokenizer st = new StringTokenizer(line.toString(), ";"); 
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	dataamostra = st.nextToken();	
		        	dataamostra = dataamostra.replace(".","/");
		    	}
		    	//BP10
		       	if (linhaAmostra==71){
		    		StringTokenizer st = new StringTokenizer(line.toString(), ";"); 
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	BP10 = st.nextToken();	
		        	
		    	}
		       	//BP50
		       	if (linhaAmostra==75){
		    		StringTokenizer st = new StringTokenizer(line.toString(), ";"); 
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	BP50 = st.nextToken();	
		        	
		    	}
		       	// BP90
		     	if (linhaAmostra==79){
		    		StringTokenizer st = new StringTokenizer(line.toString(), ";"); 
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	BP90 = st.nextToken();			        	
		    	}
		    	// pegar o ponto maximo de ebulição
		    	if (linhaAmostra==170) {
		    		StringTokenizer st = new StringTokenizer(line.toString(), ";"); 
		    		nulo = st.nextToken();
		    		nulo = st.nextToken();
		    		FBP = st.nextToken();
		    		FBP = FBP.replace(" ","");
		    	}
		    	// encerrar a amostra, criar o pacote, enviar
		    	// zerar o marcador de nova amostras em linha
		    	if (linhaAmostra==241){
		    		linhaAmostra = 1;
		    		System.out.println("Destilador:Nome da amostra: "+nomeamostra);		    		
		    		System.out.println("Destilador:FBP da amostra: "+FBP);
		    		if (nomeamostra.substring(0, 2).equals("GC")){
		    			System.out.println("Destilador:Amostra gasolina encontrada..");
		    		   
		    			Sample amostra = new Sample();
					    List lista_propriedades = new ArrayList();
					    Gasoline gas = new Gasoline();
					    
					    Property pBP10 = new Property();				  	  					   
					    pBP10.setName("BP10");
					    pBP10.setValue((new Double(BP10)));
			        	lista_propriedades.add(pBP10);
			       
			        	 Property pBP50 = new Property();				  	  					   
			        	 pBP50.setName("BP50");
			        	 pBP50.setValue((new Double(BP50)));
				         lista_propriedades.add(pBP50);
				       
				         Property pBP90 = new Property();				  	  					   
				         pBP90.setName("BP90");
				         pBP90.setValue((new Double(BP90)));
					     lista_propriedades.add(pBP90);
					       
				         Property pFBP = new Property();				  	  					   
				         pFBP.setName("FBP");
				         if (FBP.length() == 0){
				        	 FBP = "0";
				         }
				         pFBP.setValue((new Double(FBP)));
					     lista_propriedades.add(pFBP);
					       
					     
			        	amostra.setNameSample(nomeamostra);
					    amostra.setDateSample(dataamostra);
					    
						gas.setTypeSample("Destilacao");
						gas.setNameSample(nomeamostra);
						gas.setDateSample(dataamostra);
						gas.setProperties(lista_propriedades);
						
		    			addBehaviour(new InformSupervisorBehaviour(this, gas));
		    			System.out.println("Destilador:Amostra gasolina enviada pro supervisor..\n"+gas.toString());
		    			doWait(2000); 
		    			
		    		} else if(nomeamostra.substring(0, 2).equals("OD")){
		    			System.out.println("Destilador:Amostra Diesel encontrada..");
		    			
		    		}
		    		
		    		contAmostras++;
		    		
		    	} else {
		    		linhaAmostra++;
		    		
		    	}
		    	
		  }
	 
				fis.close();
				bis.close();
				dis.close();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	}
    
    
    protected void setup() {
    	
			manager.registerLanguage(codec);
			manager.registerOntology(ontology);
			// gerenciar informacoes recebidas
			addBehaviour(new HandleConfirmBehaviour(this));      
			ReadData();
			
    }
    
    protected void takeDown() {
    	System.out.println(getName()+" exiting ...");
    }
    
    // enviar informacoes
    class InformSupervisorBehaviour extends OneShotBehaviour {
			private Sample it;
			
			public InformSupervisorBehaviour(Agent a, Sample it) { 
				super(a); 
				this.it = it;
			}
	
			public void action() {
		    	try {
		    	//	doWait(2000);
						System.out.println("\nAgente Destilador: Informando o Supervisor que eu tenho "+it);
						
						// Prepare the message
						ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
						AID receiver = new AID("AgenteSupervisor",AID.ISLOCALNAME); // Send the message to myself
				       				
						msg.setSender(getAID());
						msg.addReceiver(receiver);
						msg.setLanguage(codec.getName());
						msg.setOntology(ontology.getName());
	
						// Fill the content
						Information informante = new Information();
						informante.setName(getAID());
						informante.setStatus("Destilador");
						informante.setSample(it);
						
						manager.fillContent(msg, informante);
						myAgent.send(msg);
						
		    	} 
		    	catch(Exception e) { 
		    		e.printStackTrace(); 
		    	}
			}
    }
    class HandleConfirmBehaviour extends CyclicBehaviour {
		
		public HandleConfirmBehaviour(Agent a) { 
			super(a); 
		}

		public void action() {
			ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.AGREE));
			if (msg != null) {
				System.out.println("\nDESTILADOR: Informação confirmada do SUPERVISOR. AGREE");
				System.out.println(msg);
    		
			} else {
				block();
			}
		}
		
}
} 