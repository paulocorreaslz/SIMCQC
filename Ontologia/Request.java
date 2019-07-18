package Ontologia;

import jade.content.AgentAction;
import jade.core.AID;
/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */
public class Request implements AgentAction {
	private AID name;
	private String type;
	private Sample sample;
	
	public AID getName() {
		return name;
	}
	public void setName(AID name) {
		this.name = name;
	}
	public Sample getSample() {
		return sample;
	}
	public void setSample(Sample sample) {
		this.sample = sample;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
