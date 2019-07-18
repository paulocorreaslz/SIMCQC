package Ontologia;

import jade.util.leap.Iterator;
import jade.util.leap.List;

public class Diesel extends Sample {
	
	private String typeSample = null;
	private List properties = null;
	
	public List getProperties() {
		return properties;
	}
	public void setProperties(List properties) {
		this.properties = properties;
	}
	public String getTypeSample() {
		return typeSample;
	}
	public void setTypeSample(String typeSample) {
		this.typeSample = typeSample;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer(" Tipo Amostra: " + getTypeSample());
		if (properties != null) {
			Iterator it = properties.iterator();
			int i = 0;
			while (it.hasNext()) {
				sb.append(" ");
				Property t = (Property) it.next();
				sb.append(" Propriedades: "+i+": "+t.toString());
				i++;
			}
		}
		return "Amostra Gasoline: " + sb.toString();
  }
}
