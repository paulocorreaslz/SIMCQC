;/*****************************************************************
;JADE - Java Agent DEvelopment Framework is a framework to develop 
;multi-agent systems in ;compliance with the FIPA specifications.
;Copyright (C) 2000 CSELT S.p.A. 
;
;GNU Lesser General Public License
;
;This library is free software; you can redistribute it and/or
;modify it under the terms of the GNU Lesser General Public
;License as published by the Free Software Foundation, 
;version 2.1 of the License. 
;
;This library is distributed in the hope that it will be useful,
;but WITHOUT ANY WARRANTY; without even the implied warranty of
;MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
;Lesser General Public License for more details.
;
;You should have received a copy of the GNU Lesser General Public
;License along with this library; if not, write to the
;Free Software Foundation, Inc., 59 Temple Place - Suite 330,
;Boston, MA  02111-1307, USA.
;*****************************************************************/

;/**
;
;@author Fabio Bellifemine - CSELT S.p.A
;@version $Date: 2000/09/12 13:24:01 $ $Revision: 2.0 $
;*/
;
; Remind that the ACLMessage has been defined with the following template:
; (deftemplate ACLMessage 
;              (slot communicative-act) (slot sender) (multislot receiver) 
;              (slot reply-with) (slot in-reply-to) (slot envelope) 
;              (slot conversation-id) (slot protocol) 
;              (slot language) (slot ontology) (slot content) 
;              (slot encoding) (multislot reply-to) (slot reply-by))
; refer to Fipa2000 (www.fipa.org) for the description of the 
; ACLMessage parameters.
;
; Remind that Jade has also asserted for you the fact 
; (MyAgent (name <agentname)) that is usefull to know the name of your agent
;
; Finally, remind that Jade has built a userfunction called send
; to send messages to other agents. There are two styles to call send:
; ?m <- (assert (ACLMessage (communicative-act inform) (receiver agent)))
; (send ?m)
; or, in alternative
; (send (assert (ACLMessage (communicative-act inform) (receiver agent))))
; The two following rules show the usage of both styles. One of the two
; rules can be used

;(defrule send-a-message
; "When a message is asserted whose sender is this agent, the message is
;  sent and then retracted from the knowledge base."
; (MyAgent (name ?n))
; ?m <- (ACLMessage (sender ?n))
; =>
; (send ?m)
; (retract ?m)
;)
; Definicao do template principal da ontologia para recebimento da informacao
(import Ontologia.*)
(import examples.jess.*)
(defglobal ?*msg* = nil)
(defglobal ?*ok* = nil)
(deftemplate Information (declare (from-class Information)))
(deftemplate Sample (declare (from-class Sample)))
(deftemplate Gasoline (declare (from-class Gasoline)))
(deftemplate Diesel (declare (from-class Diesel)))
(deftemplate Biodiesel (declare (from-class Biodiesel)))
(deftemplate Alcohol (declare (from-class Alcohol)))
(deftemplate Property (declare (from-class Property)))

(watch facts)
(watch all)

(defrule ACLmessage
   "When a 'cfp' message arrives from an agent ?s, this rule asserts a 
  'propose' message to the same sender and retract the just arrived message"
  ?m <- (ACLMessage (content ?c) )
  (assert (Information)
 (retract ?m)
)
    
(defrule information
    "regra para capturar a informacao e o combustivel"
	?p <- (Information (Sample ?c))
    =>
   (assert (Gasoline))
)

(defrule gasoline
        "regra gasolina"
        ?p <- (Gasoline (properties ?c))
        =>
        (assert (Propery ?p))
)
    
(defrule ETA
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "ETA" && value <> nil}(name ?n) (value ?v) )
    =>
    (store ETAResultado "Propriedade ETANOL.Em conformidade com valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
    ;(send ?*msg*)
)
(defrule BEN
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "BEN" && value > 1}(name ?n) (value ?v) )
    =>
    (store BENResultado "Propriedade BENZENO maior que 1%. Possibilidade de contaminação.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule BEN2
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "BEN" && value <= 1}(name ?n) (value ?v) )
    =>
    (store BENResultado "Propriedade BENZENO  menor ou igual 1%. Em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule AROM
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "AROM" && value > 45}(name ?n) (value ?v) )
    =>
    (store AROMResultado "Propriedade AROMATICOS maior que 45%. Possibilidade de contaminação.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule AROM2
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "AROM" && value <= 45}(name ?n) (value ?v) )
    =>
    (store AROMResultado "Propriedade AROMATICOS  menor ou igual 45%.  Em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule OLEF
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "OLEF" && value > 30}(name ?n) (value ?v) )
    =>
    (store OLEFResultado "Propriedade OLEFINAS maior que 30%. Possibilidade de contaminação.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
    ;(send ?*msg*)
)
(defrule OLEF2
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "OLEF" && value <= 30}(name ?n) (value ?v) )
    =>
    (store OLEFResultado "Propriedade OLEFINAS  menor ou igual a 30%. Em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
    ;(send ?*msg*)
)
(defrule SAT
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "SAT" && value <> nil}(name ?n) (value ?v) )
    =>
    (store SATResultado "Propriedade SATURADAS. Em conformidade com valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
    ;(send ?*msg*)
)
(defrule ROM
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "ROM" && value <> nil}(name ?n) (value ?v) )
    =>
    (store ROMResultado "Propriedade RON em conformidade com valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule MON
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "MON" && value < 82}(name ?n) (value ?v) )
    =>
    (store MONResultado "Propriedade MON menor que 82%.Possivel contaminação por ALCOOL.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule MON2
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "MON" && value >= 82}(name ?n) (value ?v) )
    =>
    (store MONResultado "Propriedade MON maior ou igual a 82%.Em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule AEACTEMP
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "AEAC-Temp" && value <> nil}(name ?n) (value ?v) )
    =>
    (store AEACTEMPResultado "Propriedade Temperatura AEAC em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule AEACCAMADAAQUOSA
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "AEAC-Temp" && value <> nil}(name ?n) (value ?v) )
    =>
    (store AEACCAMADAResultado "Propriedade Camada Aquosa AEAC em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule MASSAESPECIFICA20G
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "MASSAESPECIFICA20G" && value <> nil}(name ?n) (value ?v) )
    =>
    (store MASSAESPECIFICA20GResultado "Propriedade Massa Especifica a 20g por cm em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)
(defrule MASSAESPECIFICA20KG
    "regra para verificar as propriedades do tipo de combustivel"
	?p <- (Property {name == "MASSAESPECIFICA20KG" && value <> nil}(name ?n) (value ?v) )
    =>
    (store MASSAESPECIFICA20KGResultado "Propriedade Massa Especifica a 20kg por m em conformidade com os valores de referencia.")
    (printout t "propriedade nome: " ?n " valor " ?v crlf)
)



(facts)

(reset) 

(run)  
; if you put run here, Jess is run before waiting for a message arrival,
; if you do not put (run here, the agent waits before for the arrival of the 
; first message and then runs Jess.








