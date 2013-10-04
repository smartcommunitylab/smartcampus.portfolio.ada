/*******************************************************************************
 * Copyright 2012-2013 Trento RISE
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/
package eu.trentorise.smartcampus.portfolio.ada.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import smartcampus.ada.ANAGRAFICAESTESA;
import smartcampus.ada.ArrayOfANAGRAFICAESTESA;
import smartcampus.ada.ArrayOfLIBRETTOSTUDENTE;
import smartcampus.ada.ArrayOfPERPersona;
import smartcampus.ada.ArrayOfULTIMACARRIERASTUDENTE;
import smartcampus.ada.LIBRETTOSTUDENTE;
import smartcampus.ada.PERPersona;
import smartcampus.ada.ULTIMACARRIERASTUDENTE;

@Controller
public class ADAController {

	private Logger log = Logger.getLogger(this.getClass());

	public ADAController() {
	}

	@RequestMapping(method = RequestMethod.GET, value = "/anagraficaestesa/{idAda}")
	public @ResponseBody
	String getAnagraficaEstesa(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String idAda) {
		try {
			Map<String, Object> map = new TreeMap<String, Object>();

			ObjectMapper mapper = new ObjectMapper();

			ArrayOfANAGRAFICAESTESA anagraficaEstesa = ADAConnector.anagraficaEstesa(idAda);

			for (ANAGRAFICAESTESA ae : anagraficaEstesa.getANAGRAFICAESTESA()) {
				map.put("nation", notNull(ae.getDESCRIZIONECITTADINANZAIT().getValue()));
				map.put("phone", notNull(ae.getRESIDENZATELEFONO().getValue()));
				map.put("mobile", notNull(ae.getCELLULARE().getValue()));
				map.put("idGiada", notNull(ae.getIDGIADAPERSONA().getValue()));
				String address = notNull(ae.getRESIDENZAVIA().getValue()) + " " + notNull(ae.getRESIDENZANUMEROCIVICO().getValue()) + " - " + notNull(ae.getRESIDENZACOMUNEDESCRIZIONEIT().getValue()) + ", " + notNull(ae.getRESIDENZANAZIONEDESCRIZIONEIT().getValue());
				map.put("address", address);
			}

			ArrayOfULTIMACARRIERASTUDENTE carriera = ADAConnector.ultimaCarrieraStudente(idAda);
			for (ULTIMACARRIERASTUDENTE ucs : carriera.getULTIMACARRIERASTUDENTE()) {
				map.put("enrollmentYear", notNull(ucs.getANNOISCRIZIONE().getValue()));
				map.put("academicYear", notNull(ucs.getANNOCORSO().getValue()));
				map.put("supplementaryYears", ""); // missing
				map.put("cfu", notNull(ucs.getCREDITI().getValue()));
				map.put("cfuTotal", notNull(ucs.getCREDITIINPIANO().getValue()));
				map.put("marksNumber", notNull(ucs.getNUMEROESAMISUPERATI().getValue()));
				map.put("marksAverage", notNull(ucs.getMEDIAARITMETICA().getValue()));
				map.put("marksWeightedAverage", notNull(ucs.getMEDIAPONDERATA().getValue()));
				map.put("cds", notNull(ucs.getDESCRIZIONECDS().getValue()));
			}
			
			ArrayOfPERPersona anagrafica = ADAConnector.anagrafica(idAda);
			
      for (PERPersona persona : anagrafica.getPERPersona()) {
      	map.put("fiscalCode", persona.getCODFISPER().getValue());
      	map.put("name", persona.getNOME().getValue());
      	map.put("surname", persona.getCOGNOME().getValue());
      	map.put("gender", persona.getSESSOPER().getValue());
      	
      	XMLGregorianCalendar birth = persona.getDATANASCITAPER().getValue();
      	String date = birth.getDay() + "/" + birth.getMonth() + "/" + birth.getYear();
      	
      	map.put("dateOfBirth", date);
      }
      
			return mapper.writeValueAsString(map);

		} catch (Exception e) {
			// response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			log.error("Error retrieving student data from ADA ws for " + idAda);
		}
		return "";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/esami/{idAda}")
	public @ResponseBody
	String getEsami(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String idAda) {
		try {
			ObjectMapper mapper = new ObjectMapper();

			List<Map<String, Object>> exams = new ArrayList<Map<String, Object>>();

			ArrayOfLIBRETTOSTUDENTE libretto = ADAConnector.librettoStudente(idAda);
			for (LIBRETTOSTUDENTE ls : libretto.getLIBRETTOSTUDENTE()) {
				XMLGregorianCalendar cal = ls.getDATASUPERAMENTO().getValue();

				Map<String, Object> map = new TreeMap<String, Object>();

				if (cal == null) {
					map.put("date", Long.MIN_VALUE);
				} else {
					map.put("date", ls.getDATASUPERAMENTO().getValue().toGregorianCalendar().getTimeInMillis());
				}

				map.put("id", ls.getIDESSE3ATTIVITADIDATTICA().getValue());
				map.put("cod", ls.getCODESSE3ATTIVITADIDATTICA().getValue());
				map.put("name", ls.getDESCRIZIONEATTIVITADIDATTICA().getValue());
				String voto = "";
				if ("V".equals(ls.getMODALITAVALUTAZIONE().getValue())) {
					voto = ls.getVOTO().getValue().toString();
				} else if ("AP".equals(notNull(ls.getTIPOGIUDIZIO().getValue()))) {
					voto = "Approvato";
				}
				map.put("result", voto);
				map.put("lode", (ls.getLODE().getValue() == 0) ? false : true);
				map.put("weight", ls.getPESO().getValue());
//				map.put("nomedia", ls.getNOMEDIA().getValue());
//				map.put("stato", ls.getSTATO().getValue());

				exams.add(map);
			}

			return mapper.writeValueAsString(exams);

		} catch (Exception e) {
			// response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			log.error("Error retrieving exams from ADA ws for " + idAda);
		}
		return "";
	}

	private String notNull(Object s) {
		return (s == null) ? "" : s.toString();
	}

}
