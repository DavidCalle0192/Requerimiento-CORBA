package servidorAlertas.dto;


/**
* servidorAlertas/dto/AlertaDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 05:46:11 PM COT
*/

public final class AlertaDTO implements org.omg.CORBA.portable.IDLEntity
{
  public servidorAlertas.dto.IndicadoresDTO indicadores = null;
  public String fecha = null;
  public String hora = null;
  public int puntuacion = (int)0;

  public AlertaDTO ()
  {
  } // ctor

  public AlertaDTO (servidorAlertas.dto.IndicadoresDTO _indicadores, String _fecha, String _hora, int _puntuacion)
  {
    indicadores = _indicadores;
    fecha = _fecha;
    hora = _hora;
    puntuacion = _puntuacion;
  } // ctor

} // class AlertaDTO
