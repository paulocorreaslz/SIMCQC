package Ontologia;


import jade.content.Predicate;
import jade.core.AID;
/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */
public class Report implements Predicate {
	private AID name;
	private String diagnosis;
	private Sample sample;
	
	public Sample getSample() {
		return sample;
	}

	public void setSample(Sample sample) {
		this.sample = sample;
	}

	public Report() {
	}

	public AID getName() {
		return name;
	}

	public void setName(AID name) {
		this.name = name;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
		
}
