package cliente;

import cliente.sop_corba.Paciente;
import cliente.sop_corba.PacienteHelper;
import cliente.sop_corba.PacienteImpl;
import cliente.utilidades.UtilidadesConsola;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import servidorAlertas.dto.IndicadoresDTO;
import servidorAlertas.dto.PacienteDTO;
import servidorAlertas.sop_corba.GestionPacientes;
import servidorAlertas.sop_corba.GestionPacientesHelper;

public class ClienteDeObjetos {
    //*** Atributo estático ***

    static GestionPacientes ref;
    public GestionPacientes principal() {
        try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";
            System.out.println("Ingrese la dirección IP donde escucha el n_s");
            vec[1] = UtilidadesConsola.leerCadena();
            vec[2] = "-ORBInitialPort";
            System.out.println("Ingrese el puerto donde escucha el n_s");
            vec[3] = UtilidadesConsola.leerCadena();

            // se crea e inicia el ORB
            ORB orb = ORB.init(vec, null);

            // se obtiene la referencia al name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objAlertas";
            ref = GestionPacientesHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtenido el manejador sobre el servidor de objetos: " + ref);
            
            PacienteImpl clienteCallbackImpl = new PacienteImpl();
            // obtiene la referencia del rootpoa & activa el POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            org.omg.CORBA.Object refCliente = rootpoa.servant_to_reference(clienteCallbackImpl);
            // obtiene la referencia del objeto callback
            Paciente objcllbck = PacienteHelper.narrow(refCliente);
            StringHolder resultado = new StringHolder("");
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
        return ref;
    }
}
