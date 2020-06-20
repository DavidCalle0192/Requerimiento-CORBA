package servidorAlertas.sop_corba;


/**
* servidorAlertas/sop_corba/GestionPacientesPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 05:13:41 PM COT
*/

public abstract class GestionPacientesPOA extends org.omg.PortableServer.Servant
 implements servidorAlertas.sop_corba.GestionPacientesOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registrarPaciente", new java.lang.Integer (0));
    _methods.put ("actualizarPaciente", new java.lang.Integer (1));
    _methods.put ("buscarPaciente", new java.lang.Integer (2));
    _methods.put ("enviarIndicadores", new java.lang.Integer (3));
    _methods.put ("establecerMaxPacientes", new java.lang.Integer (4));
    _methods.put ("getMaxPacientes", new java.lang.Integer (5));
    _methods.put ("getNumRegistros", new java.lang.Integer (6));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // servidorAlertas/sop_corba/GestionPacientes/registrarPaciente
       {
         servidorAlertas.dto.PacienteDTO objPaciente = servidorAlertas.dto.PacienteDTOHelper.read (in);
         cliente.sop_corba.Paciente refCliente = cliente.sop_corba.PacienteHelper.read (in);
         org.omg.CORBA.StringHolder resultado = new org.omg.CORBA.StringHolder ();
         boolean $result = false;
         $result = this.registrarPaciente (objPaciente, refCliente, resultado);
         out = $rh.createReply();
         out.write_boolean ($result);
         out.write_string (resultado.value);
         break;
       }

       case 1:  // servidorAlertas/sop_corba/GestionPacientes/actualizarPaciente
       {
         servidorAlertas.dto.PacienteDTO objPaciente = servidorAlertas.dto.PacienteDTOHelper.read (in);
         boolean $result = false;
         $result = this.actualizarPaciente (objPaciente);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // servidorAlertas/sop_corba/GestionPacientes/buscarPaciente
       {
         int idPaciente = in.read_long ();
         servidorAlertas.dto.PacienteDTO $result = null;
         $result = this.buscarPaciente (idPaciente);
         out = $rh.createReply();
         servidorAlertas.dto.PacienteDTOHelper.write (out, $result);
         break;
       }

       case 3:  // servidorAlertas/sop_corba/GestionPacientes/enviarIndicadores
       {
         int idPaciente = in.read_long ();
         servidorAlertas.dto.IndicadoresDTO objIndicadores = servidorAlertas.dto.IndicadoresDTOHelper.read (in);
         boolean $result = false;
         $result = this.enviarIndicadores (idPaciente, objIndicadores);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 4:  // servidorAlertas/sop_corba/GestionPacientes/establecerMaxPacientes
       {
         int num = in.read_long ();
         boolean $result = false;
         $result = this.establecerMaxPacientes (num);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 5:  // servidorAlertas/sop_corba/GestionPacientes/getMaxPacientes
       {
         int $result = (int)0;
         $result = this.getMaxPacientes ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 6:  // servidorAlertas/sop_corba/GestionPacientes/getNumRegistros
       {
         int $result = (int)0;
         $result = this.getNumRegistros ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:servidorAlertas/sop_corba/GestionPacientes:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public GestionPacientes _this() 
  {
    return GestionPacientesHelper.narrow(
    super._this_object());
  }

  public GestionPacientes _this(org.omg.CORBA.ORB orb) 
  {
    return GestionPacientesHelper.narrow(
    super._this_object(orb));
  }


} // class GestionPacientesPOA
