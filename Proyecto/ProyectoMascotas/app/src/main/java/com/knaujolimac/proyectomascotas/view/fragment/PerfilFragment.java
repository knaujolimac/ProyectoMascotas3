package com.knaujolimac.proyectomascotas.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knaujolimac.miappcorporativa.R;
import com.knaujolimac.proyectomascotas.adapter.PerfilMascotaAdaptador;
import com.knaujolimac.proyectomascotas.pojo.Mascota;
import com.knaujolimac.proyectomascotas.presentador.IRecylerViewPerfilFragmentPresenter;
import com.knaujolimac.proyectomascotas.presentador.RecylerViewPerfilFragmentPresenter;

import java.util.ArrayList;

public class PerfilFragment extends Fragment implements IRecyclerViewPerfilFragmentView {

    ArrayList<Mascota> listFotosPerfil;
    private RecyclerView rvMiPerfil;
    private IRecylerViewPerfilFragmentPresenter iRecylerViewPerfilFragmentPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil,container,false);
        rvMiPerfil = (RecyclerView) view.findViewById(R.id.rvMiPerfil);
        iRecylerViewPerfilFragmentPresenter = new RecylerViewPerfilFragmentPresenter(this,getContext());

        return view;
    }


    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        rvMiPerfil.setLayoutManager(glm);
    }

    @Override
    public PerfilMascotaAdaptador crearAdaptador(ArrayList<Mascota> listFotos) {
        PerfilMascotaAdaptador perfilMascotaAdaptador = new PerfilMascotaAdaptador(listFotos, getActivity());
        return perfilMascotaAdaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PerfilMascotaAdaptador perfilAdaptador) {
        rvMiPerfil.setAdapter(perfilAdaptador);

    }
}
