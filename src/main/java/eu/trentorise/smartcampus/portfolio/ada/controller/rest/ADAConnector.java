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

import smartcampus.ada.ADAWsDataService;
import smartcampus.ada.ArrayOfANAGRAFICAESTESA;
import smartcampus.ada.ArrayOfLIBRETTOSTUDENTE;
import smartcampus.ada.ArrayOfPERPersona;
import smartcampus.ada.ArrayOfULTIMACARRIERASTUDENTE;
import smartcampus.ada.IADAWsData20120415;


/**
 * 
 * @author gioadami
 */
public class ADAConnector {

	public static ArrayOfANAGRAFICAESTESA anagraficaEstesa(java.lang.String idAda) {
		ADAWsDataService service = new ADAWsDataService();
		IADAWsData20120415 port = service.getBasicHttpBindingIADAWsData20120415();
		return port.aesAnagraficaEstesa20120415(idAda);
	}

	public static ArrayOfULTIMACARRIERASTUDENTE ultimaCarrieraStudente(java.lang.String idAda) {
		ADAWsDataService service = new ADAWsDataService();
		IADAWsData20120415 port = service.getBasicHttpBindingIADAWsData20120415();
		return port.ucsUltimaCarrieraStudente20120415(idAda);
	}
	
	public static ArrayOfLIBRETTOSTUDENTE librettoStudente(java.lang.String idAda) {
		ADAWsDataService service = new ADAWsDataService();
		IADAWsData20120415 port = service.getBasicHttpBindingIADAWsData20120415();
		return port.lbsLibrettoStudente20120415(idAda);
	}
	
  public static ArrayOfPERPersona anagrafica(String idAda) {
  ADAWsDataService service = new ADAWsDataService();
  IADAWsData20120415 port = service.getBasicHttpBindingIADAWsData20120415();
  return port.perFindIdAda20120415(idAda);
  }

  // PER_find_id_ada_20120415
  // <wsdl:operation name="lbs_Libretto_Studente_20120415

  public static void main(String[] args) {
		ADAWsDataService service = new ADAWsDataService();
		IADAWsData20120415 port = service.getBasicHttpBindingIADAWsData20120415();
		System.err.println(port.aesAnagraficaEstesa20120415("PER0073307")); 

  }
}
