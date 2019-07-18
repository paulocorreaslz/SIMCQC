package Agentes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/*
 * Created by Paulo Jose Melo Gomes Correa @2009
 * Universidade Federal do Maranhao - UFMA
 * pauloyaco@gmail.com
 */

public class GerarRelatorio {
	public static void gerarRelatorio(JRDataSource source) throws JRException {

		Map map = new HashMap();
		JasperPrint impressao = JasperFillManager.fillReport("Relatorio_amostras.jasper", map, source);
		JasperViewer viewer = new JasperViewer(impressao, false);		
		viewer.setVisible(true);			                 

	}
	public GerarRelatorio(List lista,String tec){
		try {
			GerarRelatorio.gerarRelatorio(RequestJRDataSourceFactory
					.createDatasource(lista, tec));
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

		try {
			GerarRelatorio.gerarRelatorio(RequestJRDataSourceFactory
					.createDatasource(new ArrayList(), "teste"));
		} catch (JRException e) {
			e.printStackTrace();
		}

	}
}
