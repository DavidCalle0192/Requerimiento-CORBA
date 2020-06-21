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
    private int MAX_PACIENTES = -1;
    PacienteDAO objPacienteDAO;
    
    public GestionPacienteImpl() {
        objRegistros = new Hashtable<>();
        objPacienteDAO = new PacienteDAO();
        this.guiAlertas = new VistaLogAlertas();
        guiAlertas.setVisible(true);

    }
    
    /**
     * Registra un paciente en la base de datos, si el paciente ya esta registrado 
     * actualiza sus datos
     * @param objPaciente Objeto que contiene los datos del paciente
     * @param refCliente Servant del paciente 
     * @param resultado parametro de salida donde se retorna un mensaje del registro
     * @return Retorna true si el registro o actualizacion es exitoso
     */
    @Override
    public boolean registrarPaciente(PacienteDTO objPaciente, Paciente refCliente, StringHolder resultado) {
        log("Ejecutando registrarPaciente...");
        boolean res = false;
        resultado.value = "";
        if(objRegistros.size() < MAX_PACIENTES){
            if(!pacienteActivo(objPaciente)){
                if(!pacienteRegistrado(objPaciente)){
                    res = objPacienteDAO.registrarPaciente(objPaciente);
                    resultado.value = "registrado";
                }else{
                    resultado.value = "actualizado";
                    log("El paciente con id "+objPaciente.id+" ya esta registrado se procedera actualizar sus datos");//TODO actualizar
                    res = actualizarPaciente(objPaciente);
                }
                if(res==true){
                    objRegistros.put(objPaciente.id, new Pair<PacienteDTO,Paciente>(objPaciente,refCliente));
                    log("Paciente nuevo:");
                    log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
                    log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
                    log("Direccion:"+objPaciente.direccion);
                    //refCliente.alertarPaciente("Hola");
                }else{
                    resultado.value = "Error";
                    log("Error al registrar paciente");
                }
            }else{
                resultado.value = "Paciente_activo";
                log("El paciente con id "+objPaciente.id+" ya esta siendo monitorizado");
            }
        }else{
            resultado.value = "Limite_superado";
            log("No se pueden almacenar mas pacientes.");
        }
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
        log("Ejecutando actualizarPaciente...");
        boolean res = false;
        if(pacienteRegistrado(objPaciente)){
            res = objPacienteDAO.actualizarPaciente(objPaciente);
            log("Actualizando datos paciente con id"+objPaciente.id+":");
            log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
            log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
            log("Direccion:"+objPaciente.direccion);
        }else{
            log("El paciente con id "+objPaciente.id+" no esta registrado se procedera a registrar sus datos");
            res = objPacienteDAO.registrarPaciente(objPaciente);
            log("Paciente nuevo:");
            log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
            log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
            log("Direccion:"+objPaciente.direccion);
        }
        return res;
    }

    @Override
    public PacienteDTO buscarPaciente(int idPaciente) {
        log("Ejecutando buscarPaciente...");
        PacienteDTO objPaciente = objPacienteDAO.consultarPacienteId(idPaciente);
        if(objPaciente!=null){
            log("Paciente encontrado:");
            log("Nombre:"+objPaciente.nombres+" "+objPaciente.apellidos);
            log("Identificacion: "+objPaciente.tipo_id+" "+objPaciente.id);
            log("Direccion:"+objPaciente.direccion);
        }else{
            log("No se encontro el paciente con id "+idPaciente);
        }
        return objPaciente;
    }

    @Override
    public boolean enviarIndicadores(int idPaciente, IndicadoresDTO objIndicadores) {
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
       return MAX_PACIENTES;
    }

    @Override
    public int getNumRegistros() {
        return objRegistros.size();
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
