package servidorNotificaciones.sop_corba;


/**
* servidorNotificaciones/sop_corba/NotificacionesHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 03:34:21 PM COT
*/

abstract public class NotificacionesHelper
{
  private static String  _id = "IDL:servidorNotificaciones/sop_corba/Notificaciones:1.0";

  public static void insert (org.omg.CORBA.Any a, servidorNotificaciones.sop_corba.Notificaciones that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static servidorNotificaciones.sop_corba.Notificaciones extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (servidorNotificaciones.sop_corba.NotificacionesHelper.id (), "Notificaciones");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static servidorNotificaciones.sop_corba.Notificaciones read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_NotificacionesStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, servidorNotificaciones.sop_corba.Notificaciones value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static servidorNotificaciones.sop_corba.Notificaciones narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof servidorNotificaciones.sop_corba.Notificaciones)
      return (servidorNotificaciones.sop_corba.Notificaciones)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      servidorNotificaciones.sop_corba._NotificacionesStub stub = new servidorNotificaciones.sop_corba._NotificacionesStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static servidorNotificaciones.sop_corba.Notificaciones unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof servidorNotificaciones.sop_corba.Notificaciones)
      return (servidorNotificaciones.sop_corba.Notificaciones)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      servidorNotificaciones.sop_corba._NotificacionesStub stub = new servidorNotificaciones.sop_corba._NotificacionesStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
