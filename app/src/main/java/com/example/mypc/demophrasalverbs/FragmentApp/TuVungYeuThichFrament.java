package com.example.mypc.demophrasalverbs.FragmentApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypc.demophrasalverbs.CustomAdapter.TuVungAdapter;
import com.example.mypc.demophrasalverbs.DAO.TuVungDAO;
import com.example.mypc.demophrasalverbs.DTO.TuVungDTO;
import com.example.mypc.demophrasalverbs.R;

import java.util.List;

/**
 * Created by MyPC on 27/03/2018.
 */

public class TuVungYeuThichFrament extends Fragment {

    private RecyclerView rvTuVungYeuThich;
    TuVungDAO tuVungDAO;

    List<TuVungDTO> tuVungDTOList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.layout_tu_vung, container, false);

        InitView(rootView);

        return rootView;


    }

    private void InitView(View rootView){
        tuVungDAO = new TuVungDAO(getContext());
        tuVungDTOList = tuVungDAO.layTatCaCacTuYeuThich();

        rvTuVungYeuThich = rootView.findViewById(R.id.rvTuVung);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTuVungYeuThich.setLayoutManager(linearLayoutManager);

        rvTuVungYeuThich.setAdapter(new TuVungAdapter(tuVungDTOList , getContext()));
    }
}
