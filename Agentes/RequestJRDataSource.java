package Agentes;
import java.util.Iterator;
import java.util.Vector;

import Ontologia.Gasoline;
import Ontologia.Report;
import Ontologia.Request;
import Ontologia.Sample;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Unifersidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class RequestJRDataSource implements JRDataSource {

		private Iterator iterator;

		private Report cursor;
		private String tecnico;
		
		public RequestJRDataSource(Vector amostra, String tec) {
			super();
			iterator = amostra.iterator();
			tecnico = tec;
		}

		public boolean next() throws JRException {
			boolean retorno = iterator.hasNext();
			if(retorno){
				cursor= (Report) iterator.next();
			}
			return retorno;
		}

		public Object getFieldValue(JRField campo) throws JRException {
			Report amostra = cursor;
				if (campo.getName().equals("tecnico")){
					return tecnico;
				}
			if (amostra.getSample() instanceof Gasoline) {
				if (campo.getName().equals("nome")) {
					Gasoline gas = (Gasoline) amostra.getSample();
					return gas.getNameSample();
				}
				if (campo.getName().equals("tecnica")) {
					Gasoline gas = (Gasoline) amostra.getSample();
					return gas.getTypeSample();
				}
				if (campo.getName().equals("data")) {
					Gasoline gas = (Gasoline) amostra.getSample();
					return gas.getDateSample();
				}
				if (campo.getName().equals("diagnostico")) {
					Gasoline gas = (Gasoline) amostra.getSample();
					return amostra.getDiagnosis().replace("null", "");
				}
			}
			return null;
		}

	}
