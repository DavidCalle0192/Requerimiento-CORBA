/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorNotificaciones.sop_corba;

import servidorAlertas.dto.HistorialAlertasDTO;
import servidorNotificaciones.vista.VistaNotificaciones;

/**
 *
 * @author JhonMZ
 */
public class NotificacionesImpl implements NotificacionesOperations{
    
    VistaNotificaciones guiNotificaciones;
    
    public NotificacionesImpl(VistaNotificaciones guiNotificaciones) {
        this.guiNotificaciones = guiNotificaciones;
    }

    
    @Override
    public void enviarAlerta(HistorialAlertasDTO objHistorial) {
        //guiNotificaciones.editarInfo(objHistorial);
        System.out.println("Hola");
        guiNotificaciones.setVisible(true);
    }
    
}
