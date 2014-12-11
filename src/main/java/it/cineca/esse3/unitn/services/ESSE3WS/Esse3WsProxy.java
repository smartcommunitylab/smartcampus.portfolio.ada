package it.cineca.esse3.unitn.services.ESSE3WS;

public class Esse3WsProxy implements it.cineca.esse3.unitn.services.ESSE3WS.Esse3Ws {
  private String _endpoint = null;
  private it.cineca.esse3.unitn.services.ESSE3WS.Esse3Ws esse3Ws = null;
  
  public Esse3WsProxy() {
    _initEsse3WsProxy();
  }
  
  public Esse3WsProxy(String endpoint) {
    _endpoint = endpoint;
    _initEsse3WsProxy();
  }
  
  private void _initEsse3WsProxy() {
    try {
      esse3Ws = (new it.cineca.esse3.unitn.services.ESSE3WS.Esse3WsServiceLocator()).getESSE3WS();
      if (esse3Ws != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)esse3Ws)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)esse3Ws)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (esse3Ws != null)
      ((javax.xml.rpc.Stub)esse3Ws)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public it.cineca.esse3.unitn.services.ESSE3WS.Esse3Ws getEsse3Ws() {
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws;
  }
  
  public int fn_dologin(java.lang.String username, java.lang.String password, javax.xml.rpc.holders.StringHolder sid) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_dologin(username, password, sid);
  }
  
  public int fn_dologout(java.lang.String sid) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_dologout(sid);
  }
  
  public int fn_retrieve_xml(java.lang.String sid, java.lang.String retrieve, java.lang.String params, javax.xml.rpc.holders.StringHolder xml) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_retrieve_xml(sid, retrieve, params, xml);
  }
  
  public int fn_retrieve_xml_l(java.lang.String username, java.lang.String password, java.lang.String retrieve, java.lang.String params, javax.xml.rpc.holders.StringHolder xml) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_retrieve_xml_l(username, password, retrieve, params, xml);
  }
  
  public int fn_retrieve_xml_p(java.lang.String retrieve, java.lang.String params, javax.xml.rpc.holders.StringHolder xml) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_retrieve_xml_p(retrieve, params, xml);
  }
  
  public int fn_retrieve_xml_x(java.lang.String sid, java.lang.String xmlIn, javax.xml.rpc.holders.StringHolder xmlOut) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_retrieve_xml_x(sid, xmlIn, xmlOut);
  }
  
  public int fn_retrieve_xml_lx(java.lang.String username, java.lang.String password, java.lang.String xmlIn, javax.xml.rpc.holders.StringHolder xmlOut) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_retrieve_xml_lx(username, password, xmlIn, xmlOut);
  }
  
  public int fn_retrieve_xml_px(java.lang.String xmlIn, javax.xml.rpc.holders.StringHolder xmlOut) throws java.rmi.RemoteException{
    if (esse3Ws == null)
      _initEsse3WsProxy();
    return esse3Ws.fn_retrieve_xml_px(xmlIn, xmlOut);
  }
  
  
}