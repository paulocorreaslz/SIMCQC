package Agentes;


import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import Ontologia.Report;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class RequestJRDataSourceFactory {

		private static JRDataSource data;
		private static List listalocal = null;
		public static JRDataSource createDatasource(List lista, String tecnico) {
			if (data == null) {
				Vector amostras=new Vector();
				listalocal = lista;
				Iterator it = (Iterator) lista.iterator();
				while (it.hasNext()) {
					Report sam = (Report) it.next();
					amostras.add(sam);
				}
				
				data = new RequestJRDataSource(amostras,tecnico);
			}
			return data;
		}
	}
