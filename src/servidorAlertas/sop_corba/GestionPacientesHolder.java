package servidorAlertas.sop_corba;

/**
* servidorAlertas/sop_corba/GestionPacientesHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* domingo 21 de junio de 2020 06:38:06 PM COT
*/

public final class GestionPacientesHolder implements org.omg.CORBA.portable.Streamable
{
  public servidorAlertas.sop_corba.GestionPacientes value = null;

  public GestionPacientesHolder ()
  {
  }

  public GestionPacientesHolder (servidorAlertas.sop_corba.GestionPacientes initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servidorAlertas.sop_corba.GestionPacientesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servidorAlertas.sop_corba.GestionPacientesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servidorAlertas.sop_corba.GestionPacientesHelper.type ();
  }

}
