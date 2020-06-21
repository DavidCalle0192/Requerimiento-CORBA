/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorAlertas.sop_corba;

import cliente.sop_corba.Paciente;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.StringHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
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
public class GestionPacienteImpl implements GestionPacientesOperations{

    VistaLogAlertas guiAlertas;
    Notificaciones objRefRemotaNotificaciones;
    public GestionPacienteImpl(VistaLogAlertas guiAlertas) {
        this.guiAlertas = guiAlertas;
    }
    
    @Override
    public boolean registrarPaciente(PacienteDTO objPaciente, Paciente refCliente, StringHolder resultado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxPacientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumRegistros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void consultarReferenciaRemota(NamingContextExt nce, String servicio){
        try {
            this.objRefRemotaNotificaciones = NotificacionesHelper.narrow(nce.resolve_str(servicio));
            System.out.println("Obteniendo el manejador sobre el servidor de objetos:"+this.objRefRemotaNotificaciones);
            guiAlertas.setVisible(true);
        } catch (NotFound ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(GestionPacienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private AlertaDTO[] obtenenrHistorial(){
        AlertaDTO objHistorial[] = new AlertaDTO[6];
        System.out.print(objHistorial.length);
        for (int i = 0; i < objHistorial.length; i++) {
            objHistorial[i] = new AlertaDTO();
        }
        
        return objHistorial;
    }
}
