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

public class AgenteIrox extends Agent {

    private ContentManager manager  = (ContentManager) getContentManager();
    private Codec      codec    = new SLCodec();
    private Ontology   ontology = FuelOntology.getInstance();
    
    private String getPathFile(){
        Properties properties = new Properties();   
        try {   
          properties.load(new FileInputStream("configuration"));   
          String irox = properties.getProperty("irox");   
          System.out.println("Irox: Valor do campo: " + irox);   
          return irox;
        } catch (IOException e) {   
            System.out.println("Irox: Erro ao ler arquivo." + e.getMessage());
            return null;
        }   
    }
    private void ReadIroxDataBase(){
    	String caminho = getPathFile();
    	File file = new File(caminho);
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    
	    
	    String irox_data, irox_nome, irox_rhoo, irox_temp; 
    	String nulo, irox_warm, irox_grup, irox_loca,irox_oper;
    	String irox_comm, irox_nsub;
    	
    	String 	irox_meth,
    	irox_Etha ,    	irox_iprp ,    	irox_sba_ ,    	irox_tba_ ,
    	irox_mtbe ,    	irox_etbe ,    	irox_tame ,    	irox_dipe,
    	irox_benz,    	irox_tolu,    	irox_mxy1,    	irox_oxy1,
    	irox_pxy1,    	irox_etyb,    	irox_pryp,    	irox_2et1,
    	irox_3et1,    	irox_4et1,    	irox_mesi,    	irox_pcum,
    	irox_idur,    	irox_naph,    	irox_dmp,    	irox_tmp,
    	irox_ioct,    	irox_basx,    	irox_p965,    	irox_p911,
    	irox_linx,    	irox_dodc,     	irox_hexa ,    	irox_bas1 ,
    	irox_oxyg ,    	irox_arom ,    	irox_olef ,   	irox_satu ,
    	irox_mon_ ,    	irox_ron_ ,    	irox_aki_ ,    	irox_tibp ,
    	irox_T010 ,    	irox_T050 ,    	irox_T090 ,    	irox_TFBP ,
    	irox_DI__ ,    	irox_VP__ ,    	irox_Ole0 ,    	irox_Ole1 ,
    	irox_Ole2 ,    	irox_Maha ,    	irox_MMT_ ,    	irox_FR1_ ,
    	irox_FR2_;
    	
	    String line  = null;
	    
	    try {
	    
	      fis = new FileInputStream(file);

	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);

		  int i = 1;
	      
	      while (dis.available() != 0) {
	    	  	System.out.println("Irox: Interação: " + i);
	    	  	line = dis.readLine();
		    	if (i>1) {  	
			      
		        	StringTokenizer st = new StringTokenizer(line.toString(), ";"); 
   
		        	irox_data = st.nextToken();
		        	irox_nome = st.nextToken();
		        	irox_rhoo = st.nextToken();
		        	irox_temp = st.nextToken();
		        	irox_warm = st.nextToken();
		        	irox_grup = st.nextToken();
		        	irox_loca = st.nextToken();
		        	irox_oper = st.nextToken();
		        	irox_comm = st.nextToken();
		        	irox_nsub = st.nextToken();
		        	irox_meth = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();  
		        	irox_Etha = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_iprp = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_sba_ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_tba_ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_mtbe = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_etbe = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_tame = st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_dipe= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_benz= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_tolu= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_mxy1= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_oxy1= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_pxy1= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_etyb= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_pryp= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_2et1= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_3et1= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_4et1= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_mesi= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_pcum= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_idur= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_naph= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_dmp= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_tmp= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_ioct= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_basx= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_p965= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_p911= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_linx= st.nextToken();
		        	nulo= st.nextToken();
		        	nulo= st.nextToken();
		        	irox_dodc = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_hexa = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_bas1 = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_oxyg = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_arom = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_olef = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_satu = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_mon_ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_ron_ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_aki_ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_tibp = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_T010 = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_T050 = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_T090 = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_TFBP = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_DI__ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_VP__ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_Ole0 = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_Ole1 = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_Ole2 = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_Maha = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_MMT_ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_FR1_ = st.nextToken();
		        	nulo = st.nextToken();
		        	nulo = st.nextToken();
		        	irox_FR2_ = st.nextToken();

		    	    Sample amostra = new Sample();
				    List lista_propriedades = new ArrayList();
				    Property pETA = new Property();				  	  
				    Property pBEN = new Property();
				    Property pAROM = new Property();
				    Property pOLEF = new Property();
				    Property pSAT = new Property();
					   
				    Property pMon = new Property();
				    Property pROM = new Property();
					Gasoline AmostraGasolina = new Gasoline();  
		        	
		        	amostra.setNameSample(irox_nome.replace("", "")); // seta o nome da amostra
		        	amostra.setDateSample(irox_data);
		        	
		        	pETA.setName("ETA");
		        	pETA.setValue((new Double(irox_Etha.replace(",", "."))));
		        	lista_propriedades.add(pETA);
		        	
		        	pBEN.setName("BEN");
		        	pBEN.setValue((new Double(irox_benz.replace(",", "."))));
		        	lista_propriedades.add(pBEN);
		        	
		        	pAROM.setName("AROM");
		        	pAROM.setValue((new Double(irox_arom.replace(",", "."))));
		        	lista_propriedades.add(pAROM);
		        	
		        	pOLEF.setName("OLEF");
					pOLEF.setValue((new Double(irox_olef.replace(",", "."))));
					lista_propriedades.add(pOLEF);
		        	
					pSAT.setName("SAT");
					pSAT.setValue((new Double(irox_satu.replace(",", "."))));
					lista_propriedades.add(pSAT);
					
		      		pMon.setName("MON");
		      		pMon.setValue((new Double(irox_mon_.replace(",", "."))));
					lista_propriedades.add(pMon);						
					
					pROM.setName("ROM");
					pROM.setValue((new Double(irox_ron_.replace(",", "."))));
					lista_propriedades.add(pROM);
											
					AmostraGasolina.setTypeSample("Composicao");
					System.out.println("IROX: Nome da Amostra: "+irox_nome);
					//.substring(1, irox_nome.length()-1)
					AmostraGasolina.setNameSample(irox_nome.replace("", ""));
					AmostraGasolina.setDateSample(irox_data.replace("", ""));
					//irox_nome.substring(1, irox_nome.length()-1)
					AmostraGasolina.setProperties(lista_propriedades);				
					addBehaviour(new InformSupervisorBehaviour(this, AmostraGasolina));
					doWait(2000);
		    	}
		    	i++;
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
			addBehaviour(new HandleConfirmBehaviour(this));      
			ReadIroxDataBase();
			
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
		    		doWait(2000);
						System.out.println("\nAgente Irox: Informando o Supervisor que eu tenho "+it);
						
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
						informante.setStatus("Irox");
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
				System.out.println("\nIROX: Informação confirmada do SUPERVISOR. AGREE");
				System.out.println(msg);
    	} else {
    		block();
    	}
		}
		
}
} 