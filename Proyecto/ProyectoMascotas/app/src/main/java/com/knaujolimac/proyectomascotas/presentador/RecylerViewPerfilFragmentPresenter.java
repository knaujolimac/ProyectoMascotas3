package com.knaujolimac.proyectomascotas.presentador;

import android.content.Context;

import com.knaujolimac.proyectomascotas.view.fragment.IRecyclerViewPerfilFragmentView;
import com.knaujolimac.proyectomascotas.pojo.ConstructorPerfilMascota;
import com.knaujolimac.proyectomascotas.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by juan.chaparro on 22/07/2016.
 */
public class RecylerViewPerfilFragmentPresenter implements IRecylerViewPerfilFragmentPresenter {

    private IRecyclerViewPerfilFragmentView iRecyclerViewPerfilFragmentView;
    private Context context;
    private ArrayList<Mascota> listFotosPerfil;

    public RecylerViewPerfilFragmentPresenter(IRecyclerViewPerfilFragmentView iRecyclerViewPerfilFragmentView, Context context) {
        this.iRecyclerViewPerfilFragmentView = iRecyclerViewPerfilFragmentView;
        this.context = context;

        //Obtiene las fotos del perfil
        this.obtenerFotosPerfilMascota();
        //Muestra las fotos del perfil en el Recycle View
        this.mostrarPerfilRVMascotas();
    }


    @Override
    public void obtenerFotosPerfilMascota() {
        ConstructorPerfilMascota constructorPerfilMascota = new ConstructorPerfilMascota(this.context);
        listFotosPerfil = constructorPerfilMascota.obtenerFotosPerfilMascota();
    }

    @Override
    public void mostrarPerfilRVMascotas() {
        iRecyclerViewPerfilFragmentView.generarGridLayout();
        iRecyclerViewPerfilFragmentView.inicializarAdaptadorRV(iRecyclerViewPerfilFragmentView.crearAdaptador(listFotosPerfil));
    }
}
