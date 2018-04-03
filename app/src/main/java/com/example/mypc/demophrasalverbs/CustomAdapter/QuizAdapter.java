package com.example.mypc.demophrasalverbs.CustomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mypc.demophrasalverbs.DTO.QuizDTO;
import com.example.mypc.demophrasalverbs.R;

import java.util.List;

/**
 * Created by MyPC on 29/03/2018.
 */

public class QuizAdapter extends BaseAdapter {

    private Context context;
    private List<QuizDTO> quizDTOList;
    ViewHolerQuiz viewHolerQuiz;

    public QuizAdapter(Context context, List<QuizDTO> quizDTOList) {
        this.context = context;
        this.quizDTOList = quizDTOList;
    }

    @Override
    public int getCount() {
        return quizDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return quizDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolerQuiz {
        TextView tvTenQuiz, tvDiem;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            viewHolerQuiz = new ViewHolerQuiz();
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_quiz , parent , false);
            viewHolerQuiz.tvDiem = view.findViewById(R.id.tvDiem);
            viewHolerQuiz.tvTenQuiz = view.findViewById(R.id.tvTenQuiz);

            view.setTag(viewHolerQuiz);
        } else viewHolerQuiz = (ViewHolerQuiz) view.getTag();

        QuizDTO temp = quizDTOList.get(position);

        viewHolerQuiz.tvTenQuiz.setText(temp.getTenQuiz());
        viewHolerQuiz.tvDiem.setText("Score " + temp.getDiemSo());



        return view;
    }
}
