package com.example.mypc.demophrasalverbs.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mypc.demophrasalverbs.ChiTietTuVungAcitivity;
import com.example.mypc.demophrasalverbs.DAO.TuVungDAO;
import com.example.mypc.demophrasalverbs.DTO.TuVungDTO;

import com.example.mypc.demophrasalverbs.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by MyPC on 24/03/2018.
 */

public class TuVungAdapter extends RecyclerView.Adapter<TuVungAdapter.TuVungViewHolder> implements Filterable {

    private Context context;
    private List<TuVungDTO> mFilteredList;
    List<TuVungDTO> data;
    private TextToSpeech tts;
    TuVungDAO tuVungDAO;


    public TuVungAdapter(List<TuVungDTO> data, Context context) {
        this.context = context;
        this.data = data;
        tuVungDAO = new TuVungDAO(context);
        mFilteredList = data;

    }


    @Override
    public TuVungViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_tu_vung, parent, false);
        return new TuVungViewHolder(rootView);

    }

    @Override
    public void onBindViewHolder(TuVungViewHolder holder, final int position) {

        final TuVungDTO tuVungDTO = mFilteredList.get(position);
        holder.tvTuVung.setText(tuVungDTO.getTuVung());
        holder.tvNghia.setText(tuVungDTO.getNghia());

        holder.btnSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SpeakOut(tuVungDTO.getTuVung());
            }
        });






        holder.cbYeuThich.setChecked(tuVungDAO.checkYeuThichTheoId(tuVungDTO.getId()));

        holder.cbYeuThich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tuVungDAO.danhDauYeuThichTheoId(tuVungDTO.getId());
                    tuVungDTO.setYeuthich(1);

                } else {
                    tuVungDAO.boDanhDauYeuThichTheoId(tuVungDTO.getId());
                    tuVungDTO.setYeuthich(0);
                }
                Log.d("vitri", position + "");
            }
        });



        holder.llChiTietTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("tuvung" , tuVungDTO);
                Intent intentChiTietTuVung = new Intent(context , ChiTietTuVungAcitivity.class);
                intentChiTietTuVung.putExtras(bundle);
                context.startActivity(intentChiTietTuVung);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {                     // Custom tìm kiếm cho rv TUVung
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = data;
                } else {

                    List<TuVungDTO> filteredList = new ArrayList<>();

                    for (TuVungDTO tuVungDTO : data) {

                        if (tuVungDTO.getTuVung().toLowerCase().contains(charString) ) {

                            filteredList.add(tuVungDTO);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<TuVungDTO>) filterResults.values;
                notifyDataSetChanged();
                Log.d("soTu", mFilteredList.size()+"");
            }
        };
    }

    public class TuVungViewHolder extends RecyclerView.ViewHolder {

        CheckBox cbYeuThich;
        Button btnSpeaker;
        TextView tvTuVung, tvNghia;
        LinearLayout llChiTietTuVung;

        public TuVungViewHolder(View itemView) {
            super(itemView);
            cbYeuThich = itemView.findViewById(R.id.cbYeuThich);
            btnSpeaker = itemView.findViewById(R.id.btnSpeaker);
            tvTuVung = itemView.findViewById(R.id.tvTuVung);
            tvNghia = itemView.findViewById(R.id.tvNghia);
            llChiTietTuVung = itemView.findViewById(R.id.llChiTietTuVung);

        }


    }


    private void SpeakOut(final String tuVung) {

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                tts.speak(tuVung, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }



}
