package cliente.sop_corba;


/**
* cliente/sop_corba/PacientePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gesPacientes.idl
* s�bado 20 de junio de 2020 05:13:40 PM COT
*/

public abstract class PacientePOA extends org.omg.PortableServer.Servant
 implements cliente.sop_corba.PacienteOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("alertarPaciente", new java.lang.Integer (0));
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
       case 0:  // cliente/sop_corba/Paciente/alertarPaciente
       {
         String mensaje = in.read_string ();
         this.alertarPaciente (mensaje);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:cliente/sop_corba/Paciente:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Paciente _this() 
  {
    return PacienteHelper.narrow(
    super._this_object());
  }

  public Paciente _this(org.omg.CORBA.ORB orb) 
  {
    return PacienteHelper.narrow(
    super._this_object(orb));
  }


} // class PacientePOA
