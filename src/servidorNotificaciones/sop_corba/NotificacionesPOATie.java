package servidorNotificaciones.sop_corba;


/**
* servidorNotificaciones/sop_corba/NotificacionesPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 05:46:11 PM COT
*/

public class NotificacionesPOATie extends NotificacionesPOA
{

  // Constructors

  public NotificacionesPOATie ( servidorNotificaciones.sop_corba.NotificacionesOperations delegate ) {
      this._impl = delegate;
  }
  public NotificacionesPOATie ( servidorNotificaciones.sop_corba.NotificacionesOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public servidorNotificaciones.sop_corba.NotificacionesOperations _delegate() {
      return this._impl;
  }
  public void _delegate (servidorNotificaciones.sop_corba.NotificacionesOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public void enviarAlerta (servidorAlertas.dto.HistorialAlertasDTO objHistorial)
  {
    _impl.enviarAlerta(objHistorial);
  } // enviarAlerta

  private servidorNotificaciones.sop_corba.NotificacionesOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class NotificacionesPOATie
