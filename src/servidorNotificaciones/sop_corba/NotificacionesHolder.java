package servidorNotificaciones.sop_corba;

/**
* servidorNotificaciones/sop_corba/NotificacionesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 03:34:21 PM COT
*/

public final class NotificacionesHolder implements org.omg.CORBA.portable.Streamable
{
  public servidorNotificaciones.sop_corba.Notificaciones value = null;

  public NotificacionesHolder ()
  {
  }

  public NotificacionesHolder (servidorNotificaciones.sop_corba.Notificaciones initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servidorNotificaciones.sop_corba.NotificacionesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servidorNotificaciones.sop_corba.NotificacionesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servidorNotificaciones.sop_corba.NotificacionesHelper.type ();
  }

}