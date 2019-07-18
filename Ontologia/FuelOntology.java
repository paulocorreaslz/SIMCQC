/**
 * ***************************************************************
 * JADE - Java Agent DEvelopment Framework is a framework to develop
 * multi-agent systems in compliance with the FIPA specifications.
 * Copyright (C) 2000 CSELT S.p.A.
 * 
 * GNU Lesser General Public License
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation,
 * version 2.1 of the License.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 * **************************************************************
 */
package Ontologia;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;
import jade.content.schema.TermSchema;


/**
 * Ontology containing music related concepts.
 * @author Giovanni Caire - TILAB
 */
public class FuelOntology extends Ontology {
	// NAME
  public static final String ONTOLOGY_NAME = "FuelOntology";
  
  public static final String SAMPLE = "SAMPLE";
  public static final String SAMPLE_SERIALID = "serialID";
  public static final String SAMPLE_NAME = "nameSample";
  public static final String SAMPLE_DATE = "dateSample";
  
  
  public static final String INFORMATION = "INFORMATION";
  public static final String INFORMATION_NAME = "name";
  public static final String INFORMATION_STATUS = "status";
  public static final String INFORMATION_SAMPLE = "sample";
  
  public static final String GASOLINE = "GASOLINE";
  public static final String GASOLINE_TYPESAMPLE = "typeSample";
  public static final String GASOLINE_PROPERTIES = "properties";

  public static final String ALCOHOL = "ALCOHOL";
  public static final String ALCOHOL_TYPESAMPLE = "typeSample";
  public static final String ALCOHOL_PROPERTIES = "properties";
  
  public static final String DIESEL = "DIESEL";
  public static final String DIESEL_TYPESAMPLE = "typeSample";
  public static final String DIESEL_PROPERTIES = "properties";
  
  public static final String BIODIESEL = "BIODIESEL";
  public static final String BIODIESEL_TYPESAMPLE = "typeSample";
  public static final String BIODIESEL_PROPERTIES = "properties";
  
  public static final String PROPERTY = "PROPERTY";
  public static final String PROPERTY_NAME = "name";
  public static final String PROPERTY_VALUE = "value";

  public static final String REQUISITION = "REQUISITION";
  public static final String REQUISITION_NAME = "name";
  public static final String REQUISITION_TYPE = "type";
  
  
  public static final String REPORT = "REPORT";
  public static final String REPORT_NAME = "name";
  public static final String REPORT_DIAGNOSIS = "diagnosis";
  public static final String REPORT_SAMPLE = "sample";
  
  public static final String REQUEST = "REQUEST";
  public static final String REQUEST_NAME = "name";
  public static final String REQUEST_TYPE = "type";
  public static final String REQUEST_SAMPLE = "sample";
  
  public static final String EXPORT = "EXPORT";
  public static final String EXPORT_NAME = "name";
  public static final String EXPORT_TYPE = "type";
  public static final String EXPORT_SAMPLE = "sample";  
  // The singleton instance of this ontology
	private static Ontology theInstance = new FuelOntology(BasicOntology.getInstance());
	
	public static Ontology getInstance() {
		return theInstance;
	}
	
  /**
   * Constructor
   */
  private FuelOntology(Ontology base) {
  	super(ONTOLOGY_NAME, base);

    try {
    	add(new ConceptSchema(SAMPLE), Sample.class);
    	add(new PredicateSchema(INFORMATION), Information.class);
    	add(new ConceptSchema(GASOLINE), Gasoline.class);
    	add(new ConceptSchema(DIESEL), Diesel.class);
    	add(new ConceptSchema(ALCOHOL), Alcohol.class);
    	add(new ConceptSchema(BIODIESEL), Biodiesel.class);
    	add(new ConceptSchema(PROPERTY), Property.class);
        add(new PredicateSchema(REQUISITION), Requisition.class);
        add(new PredicateSchema(REPORT), Report.class);
        add(new AgentActionSchema(REQUEST), Request.class);	
        add(new AgentActionSchema(EXPORT), Export.class);
        
       	ConceptSchema cs = (ConceptSchema) getSchema(SAMPLE);
    	cs.add(SAMPLE_SERIALID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL); 
    	cs.add(SAMPLE_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	cs.add(SAMPLE_DATE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    	
    	
    	cs = (ConceptSchema) getSchema(GASOLINE);
    	cs.addSuperSchema((ConceptSchema) getSchema(SAMPLE));
    	cs.add(GASOLINE_TYPESAMPLE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	cs.add(GASOLINE_PROPERTIES, (ConceptSchema) getSchema(PROPERTY), 1, ObjectSchema.UNLIMITED);
    	
    	cs = (ConceptSchema) getSchema(ALCOHOL);
    	cs.addSuperSchema((ConceptSchema) getSchema(SAMPLE));
    	cs.add(ALCOHOL_TYPESAMPLE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	cs.add(ALCOHOL_PROPERTIES, (ConceptSchema) getSchema(PROPERTY), 1, ObjectSchema.UNLIMITED);
    		
    	cs = (ConceptSchema) getSchema(DIESEL);
    	cs.addSuperSchema((ConceptSchema) getSchema(SAMPLE));
    	cs.add(DIESEL_TYPESAMPLE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	cs.add(DIESEL_PROPERTIES, (ConceptSchema) getSchema(PROPERTY), 1, ObjectSchema.UNLIMITED);
    		
    	cs = (ConceptSchema) getSchema(BIODIESEL);
    	cs.addSuperSchema((ConceptSchema) getSchema(SAMPLE));
    	cs.add(BIODIESEL_TYPESAMPLE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	cs.add(BIODIESEL_PROPERTIES, (ConceptSchema) getSchema(PROPERTY), 1, ObjectSchema.UNLIMITED);
    		
    	PredicateSchema ps = (PredicateSchema) getSchema(INFORMATION);
    	ps.add(INFORMATION_NAME, (ConceptSchema) getSchema(BasicOntology.AID));
    	ps.add(INFORMATION_STATUS, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	ps.add(INFORMATION_SAMPLE, (ConceptSchema) getSchema(SAMPLE));
    	
    	ps = (PredicateSchema) getSchema(REQUISITION);
   	 	ps.add(REQUISITION_NAME, (ConceptSchema) getSchema(BasicOntology.AID));
   	 	ps.add(REQUISITION_TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
   	 	
   	 	ps = (PredicateSchema) getSchema(REPORT);
	 	ps.add(REPORT_NAME, (ConceptSchema) getSchema(BasicOntology.AID));
	 	ps.add(REPORT_DIAGNOSIS, (PrimitiveSchema) getSchema(BasicOntology.STRING));
	 	ps.add(REPORT_SAMPLE, (ConceptSchema) getSchema(SAMPLE));
    	
	 	
    	cs = (ConceptSchema) getSchema(PROPERTY);
    	cs.add(PROPERTY_NAME, (TermSchema) getSchema(BasicOntology.STRING));
    	cs.add(PROPERTY_VALUE, (TermSchema) getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    	
    	
    	AgentActionSchema as = (AgentActionSchema) getSchema(REQUEST);
    	as.add(REQUEST_NAME, (ConceptSchema) getSchema(BasicOntology.AID));
    	as.add(REQUEST_TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	as.add(REQUEST_SAMPLE, (ConceptSchema) getSchema(SAMPLE)); 
    
    	as = (AgentActionSchema) getSchema(EXPORT);
    	as.add(EXPORT_NAME, (ConceptSchema) getSchema(BasicOntology.AID));
    	as.add(EXPORT_TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
    	as.add(EXPORT_SAMPLE, (ConceptSchema) getSchema(SAMPLE)); 
    
    } 
    catch (OntologyException oe) {
    	oe.printStackTrace();
    } 
	}

}
