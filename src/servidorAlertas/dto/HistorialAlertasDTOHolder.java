package servidorAlertas.dto;

/**
* servidorAlertas/dto/HistorialAlertasDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 05:13:41 PM COT
*/

public final class HistorialAlertasDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public servidorAlertas.dto.HistorialAlertasDTO value = null;

  public HistorialAlertasDTOHolder ()
  {
  }

  public HistorialAlertasDTOHolder (servidorAlertas.dto.HistorialAlertasDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servidorAlertas.dto.HistorialAlertasDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servidorAlertas.dto.HistorialAlertasDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servidorAlertas.dto.HistorialAlertasDTOHelper.type ();
  }

}
