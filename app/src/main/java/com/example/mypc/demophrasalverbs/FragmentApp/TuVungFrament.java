package com.example.mypc.demophrasalverbs.FragmentApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypc.demophrasalverbs.CustomAdapter.TuVungAdapter;
import com.example.mypc.demophrasalverbs.DAO.TuVungDAO;
import com.example.mypc.demophrasalverbs.DTO.TuVungDTO;
import com.example.mypc.demophrasalverbs.R;

import java.util.List;

/**
 * Created by MyPC on 24/03/2018.
 */

public class TuVungFrament extends Fragment implements SearchView.OnQueryTextListener{

    private RecyclerView rvTuVung;
    private SearchView searchView;
    TuVungDAO tuVungDAO;

    TuVungAdapter tuVungAdapter;

    List<TuVungDTO> tuVungDTOList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tu_vung, container, false);
        InitView(view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    void InitView(View view) {
        tuVungDAO = new TuVungDAO(getActivity());
        tuVungDTOList = tuVungDAO.layTatCaCacTu();

        rvTuVung = view.findViewById(R.id.rvTuVung);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTuVung.setLayoutManager(layoutManager);
        tuVungAdapter = new TuVungAdapter(tuVungDTOList, getContext());
        rvTuVung.setAdapter(tuVungAdapter);

        setHasOptionsMenu(true);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_view, menu);
        MenuItem itSeach = menu.findItem(R.id.search_view);
        searchView = (SearchView) itSeach.getActionView();
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        tuVungAdapter.getFilter().filter(newText);
        return false;
    }
}
