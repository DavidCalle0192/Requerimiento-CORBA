package servidorAlertas.sop_corba;

import cliente.sop_corba.Paciente;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import org.omg.CORBA.StringHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import servidorAlertas.dao.PacienteDAO;
import servidorAlertas.dto.AlertaDTO;
import servidorAlertas.dto.HistorialAlertasDTO;
import servidorAlertas.dto.IndicadoresDTO;
import servidorAlertas.dto.PacienteDTO;
import servidorAlertas.vista.VistaLogAlertas;
import servidorNotificaciones.sop_corba.Notificaciones;
import servidorNotificaciones.sop_corba.NotificacionesHelper;

/**
 *
 * @author JhonMZ
 */
public class GestionPacienteImpl implements GestionPacientesOperations {

    private VistaLogAlertas guiAlertas;
    private Notificaciones objRefRemotaNotificaciones;
    private Hashtable<Integer, Pair<PacienteDTO,Paciente>> objRegistros;
    private int MAX_PACIENTES = 1;
    PacienteDAO objPacienteDAO;
    
    public GestionPacienteImpl() {
        objRegistros = new Hashtable<>();
        guiAlertas = new VistaLogAlertas();
        guiAlertas.setVisible(true);
        objPacienteDAO = new PacienteDAO();
    }
    
    @Override
    public boolean registrarPaciente(PacienteDTO objPaciente, Paciente refCliente, StringHolder resultado) {
        log("\nEjecutando registrarPaciente...");
        boolean res = false;
        if(objRegistros.size() < MAX_PACIENTES){
            if(!pacienteActivo(objPaciente)){
                if(!pacienteRegistrado(objPaciente)){
                    res = objPacienteDAO.registrarPaciente(objPaciente);
                    refCliente.alertarPaciente("Hola");
                }else{
                    resultado.value = "Paciente_registrado";
                    log("El paciente con id "+objPaciente.id+" ya esta registrado se procedera actualizar sus datos");
                    //TODO actualizar
                }
            }else{
                resultado.value = "Paciente_activo";
                log("El paciente con id "+objPaciente.id+" ya esta siendo monitorizado");
            }
        }else{
            resultado.value = "Limite_superado";
            log("No se pueden almacenar mas pacientes.");
        }
        /*if(!pacienteRegistrado(objPaciente)){
            
                if(pacientes.add(objPaciente)){
                    res = true;
                    log("Paciente nuevo:");
                    log("Nombre:"+objPaciente.getNombres()+" "+objPaciente.getApellidos());
                    log("Identificacion: "+objPaciente.getTipo_id()+" "+objPaciente.getId());
                    log("Direccion:"+objPaciente.getDireccion());
                    log("Creando historial...");
                    crearHistorialPaciente(objPaciente.getId());
                    resultado.value = "registrado";
                }else{
                    resultado.value = "Error";
                    log("Error al registrar paciente");
                }
            
        }else{
            resultado.value = "id_existente";
            log("El  paciente con id "+objPaciente.getId()+" ya esta registrado");
        }
        log(resultado.value);*/
        resultado.value = "";
        return res;
    }
    
    private boolean pacienteActivo(PacienteDTO objPaciente){
        //System.out.println("Buscando paciente...");
        return objRegistros.get(objPaciente.id) != null;
    }
    
    private boolean pacienteRegistrado(PacienteDTO objPacienteDTO){
        return objPacienteDAO.consultarPacienteId(objPacienteDTO.id) != null;
    }

    @Override
    public boolean actualizarPaciente(PacienteDTO objPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PacienteDTO buscarPaciente(int idPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean enviarIndicadores(int idPaciente, IndicadoresDTO objIndicadores) {
        System.out.println("Hola");
        HistorialAlertasDTO objAlertasDTO = new HistorialAlertasDTO();
        objAlertasDTO.alertas = obtenenrHistorial();
        objAlertasDTO.objPaciente = new PacienteDTO();
        objRefRemotaNotificaciones.enviarAlerta(objAlertasDTO);
        return true;
    }

    @Override
    public boolean establecerMaxPacientes(int num) {
        log("\nEjecutando establecerMaxPacientes...");
        if(num>=1 && num<=5){
            this.MAX_PACIENTES = num;
            return true;
        }else{
        return false;
        }
    }

    @Override
    public int getMaxPacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumRegistros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void consultarReferenciaRemota(NamingContextExt nce, String servicio) {
        try {
            this.objRefRemotaNotificaciones = NotificacionesHelper.narrow(nce.resolve_str(servicio));

            System.out.println("Obteniendo el manejador sobre el servidor de objetos:" + this.objRefRemotaNotificaciones);
            guiAlertas.setVisible(true);
        } catch (NotFound ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private AlertaDTO[] obtenenrHistorial() {
        AlertaDTO objHistorial[] = new AlertaDTO[6];
        System.out.print(objHistorial.length);
        for (int i = 0; i < objHistorial.length; i++) {
            objHistorial[i] = new AlertaDTO();
        }

        return objHistorial;
    }
    
    private void log(String log){
        System.out.println(log);
        guiAlertas.agregarLog(log);
    }
}
