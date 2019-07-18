package Ontologia;


import jade.content.Predicate;
import jade.core.AID;
/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */
public class Requisition implements Predicate {
	private AID name;
	private String type;
	
	public Requisition() {
	}

	public AID getName() {
		return name;
	}

	public void setName(AID name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

		
}
