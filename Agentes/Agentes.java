package Agentes;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class Agentes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 	Profile profile = new ProfileImpl();                 
    	Runtime runtime = Runtime.instance();
    	ContainerController container;
    	container = runtime.createMainContainer(profile); 
    	AgentController AgenteSupervisor, AgenteGestor, AgenteInterface;
    	try {
			AgenteSupervisor = container.createNewAgent("AgenteSupervisor","Agentes.AgenteSupervisor",  null );
			AgenteGestor = container.createNewAgent("AgenteCognitivo","Agentes.AgenteGestor",  null );
			AgenteInterface = container.createNewAgent("AgenteInterface","Agentes.Interface",  null );
			AgenteSupervisor.start();
	    	AgenteGestor.start();
	    	AgenteInterface.start();
    	} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}

}
