package servidorAlertas.sop_corba;


/**
* servidorAlertas/sop_corba/GestionPacientesOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 03:34:21 PM COT
*/

public interface GestionPacientesOperations 
{
  boolean registrarPaciente (servidorAlertas.dto.PacienteDTO objPaciente, cliente.sop_corba.Paciente refCliente, org.omg.CORBA.StringHolder resultado);
  boolean enviarIndicadores (int idPaciente, servidorAlertas.dto.IndicadoresDTO objIndicadores);
  boolean establecerMaxPacientes (int num);
  int getMaxPacientes ();
  int getNumRegistros ();
} // interface GestionPacientesOperations