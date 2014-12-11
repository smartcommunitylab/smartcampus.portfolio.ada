package eu.trentorise.smartcampus.portfolio.ada.controller.rest;

import it.cineca.esse3.unitn.services.ESSE3WS.Esse3Ws;
import it.cineca.esse3.unitn.services.ESSE3WS.Esse3WsService;
import it.cineca.esse3.unitn.services.ESSE3WS.Esse3WsServiceLocator;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.holders.StringHolder;

import org.apache.axis.client.Stub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Esse3Controller {

//	@Value("${user}")
	private String user = "guest";
	
//	@Value("${pass}")
	private String pass = "guest";
	
	private String sharedSid = null;
	private long loginTime = 0;
	
	@RequestMapping(method = RequestMethod.GET, value = "/classi")
	public @ResponseBody
	String getClassi(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
	    Esse3WsService s3 = new Esse3WsServiceLocator();
	    Esse3Ws ws = s3.getESSE3WS();
	    ((Stub)ws).setTimeout(60000);
	    String sid = login(ws);
	    StringHolder ret = new StringHolder();
	    ws.fn_retrieve_xml(sid, "CLASSI", "", ret);
	    String result = ret.value;
	    logout(ws);
	    return result;
		} catch (Exception e) {
			return "";
		}
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/facolta")
	public @ResponseBody
	String getFacolta(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
	    Esse3WsService s3 = new Esse3WsServiceLocator();
	    Esse3Ws ws = s3.getESSE3WS();
	    ((Stub)ws).setTimeout(60000);
	    String sid = login(ws);
	    StringHolder ret = new StringHolder();
	    ws.fn_retrieve_xml(sid, "FACOLTA", "", ret);
	    String result = ret.value;
	    logout(ws);
	    return result;
		} catch (Exception e) {
			return "";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cdsfacolta/{aaoff}/{tipocorso}")
	public @ResponseBody
	String getCDSFacolta(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String aaoff,  @PathVariable String tipocorso) {
		try {
	    Esse3WsService s3 = new Esse3WsServiceLocator();
	    Esse3Ws ws = s3.getESSE3WS();
	    ((Stub)ws).setTimeout(60000);
	    String sid = login(ws);
	    StringHolder ret = new StringHolder();
	    ws.fn_retrieve_xml(sid, "CDS_FACOLTA", "aa_id=" + aaoff + ";tipo_corso=" + tipocorso, ret);
	    String result = ret.value;
	    logout(ws);
	    return result;
		} catch (Exception e) {
			return "";
		}
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/pdsord/{cdsid}/{aaord}")
	public @ResponseBody
	String getPDSOrd(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String cdsid,  @PathVariable String aaord) {
		try {
	    Esse3WsService s3 = new Esse3WsServiceLocator();
	    Esse3Ws ws = s3.getESSE3WS();
	    ((Stub)ws).setTimeout(60000);
	    String sid = login(ws);
	    StringHolder ret = new StringHolder();
	    ws.fn_retrieve_xml(sid, "LISTA_PDSORD", "cds_id=" + cdsid + ";aa_ord_id=" + aaord, ret);
	    String result = ret.value;
	    logout(ws);
	    return result;
		} catch (Exception e) {
			return "";
		}
	}	
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaad/{cdsid}/{aaord}/{aaoff}/{pdsid}")
	public @ResponseBody
	String getAD(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String cdsid,  @PathVariable String aaord, @PathVariable String aaoff,  @PathVariable String pdsid) {
		try {
	    Esse3WsService s3 = new Esse3WsServiceLocator();
	    Esse3Ws ws = s3.getESSE3WS();
	    ((Stub)ws).setTimeout(60000);
	    String sid = login(ws);
	    StringHolder ret = new StringHolder();
	    ws.fn_retrieve_xml(sid, "LISTA_AD_PDSORD", "cds_id=" + cdsid + ";aa_ord_id=" + aaord + ";aa_off_id=" + aaoff + ";pds_id=" + pdsid, ret);
	    String result = ret.value;
	    logout(ws);
	    return result;
		} catch (Exception e) {
			return "";
		}
	}		
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaadlog/{cdsid}/{aaord}/{aaoff}/{pdsid}")
	public @ResponseBody
	String getADLog(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String cdsid,  @PathVariable String aaord, @PathVariable String aaoff,  @PathVariable String pdsid) {
		try {
	    Esse3WsService s3 = new Esse3WsServiceLocator();
	    Esse3Ws ws = s3.getESSE3WS();
	    ((Stub)ws).setTimeout(60000);
	    String sid = login(ws);
	    StringHolder ret = new StringHolder();
	    ws.fn_retrieve_xml(sid, "INFO_LOG_AD_PDSORD", "cds_id=" + cdsid + ";aa_ord_id=" + aaord + ";aa_off_id=" + aaoff + ";pds_id=" + pdsid, ret);
	    String result = ret.value;
	    logout(ws);
	    return result;
		} catch (Exception e) {
			return "";
		}
	}			
	
	
//	@RequestMapping(method = RequestMethod.GET, value = "/orariad/{cdscod}/{aaord}/{aaoff}/{pdsid}/{pdscod}/{adcod}/{domcod}/{fatcod}")
//	public @ResponseBody
//	String getOrariAD(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable String cdscod,  @PathVariable String aaord, @PathVariable String aaoff,  @PathVariable String pdsid,  @PathVariable String pdscod,  @PathVariable String adcod,  @PathVariable String domcod,  @PathVariable String fatcod) {
//		try {
//			Up_ws_esse3 s3 = new Up_ws_esse3Locator();
//			Up_ws_esse3Soap ws = s3.getup_ws_esse3Soap();
//			StringHolder s1 = new StringHolder();
//			StringHolder s2 = new StringHolder();
//			
//			String pdsExtCod = cdscod + "_" + aaord + "_" +  pdsid + "_" + pdscod;
//			String params = String.format("<AD_SET><LANGUAGE>ita</LANGUAGE>	<ELENCO_COMPLETO>1</ELENCO_COMPLETO><AD_CONT><AA_OFF_ID>%s</AA_OFF_ID><AA_ORD_ID>%s</AA_ORD_ID><CDS_EXT_CODE>%s</CDS_EXT_CODE><PDS_EXT_CODE>%s</PDS_EXT_CODE><AD_EXT_CODE>%s</AD_EXT_CODE><DOM_PART_COD>%s</DOM_PART_COD><FAT_PART_COD>%s</FAT_PART_COD></AD_CONT></AD_SET>", aaoff, aaord, cdscod, pdsExtCod, adcod, domcod, fatcod);
//			
//			ws.getData("GET_ORARI", params, s1, s2);
//	    return s2.value;
//		} catch (Exception e) {
//			return "";
//		}
//	}				
	
	private String login(Esse3Ws ws) throws RemoteException {
			if (System.currentTimeMillis() - loginTime > (1000 * 60)) {
				loginTime = System.currentTimeMillis();
				sharedSid = null;
			}
			if (sharedSid == null) {
				 StringHolder sid = new StringHolder();
			    ws.fn_dologin(user, pass, sid);
			    sharedSid = sid.value;
			}
			return sharedSid;
	}
	
	private void logout(Esse3Ws ws) throws RemoteException {
		if (System.currentTimeMillis() - loginTime > (1000 * 60)) {
	    ws.fn_dologout(sharedSid);
		}
	}
	
	
	
}
