package com.example.mypc.demophrasalverbs.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mypc.demophrasalverbs.CauHoiActivity;
import com.example.mypc.demophrasalverbs.CustomAdapter.QuizAdapter;
import com.example.mypc.demophrasalverbs.DAO.QuizDao;
import com.example.mypc.demophrasalverbs.DTO.QuizDTO;
import com.example.mypc.demophrasalverbs.R;

import java.util.List;

/**
 * Created by MyPC on 29/03/2018.
 */

public class QuizFrament extends Fragment {

    ListView lvQuiz;
    List<QuizDTO> quizDTOList;
    QuizAdapter quizAdapter;
    QuizDao quizDao;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.layout_quiz, container, false);
        initView(rootView);

        return rootView;

    }

    private void initView(View rootView) {
        quizDao = new QuizDao(getContext());

        quizDTOList = quizDao.layTatCaCacQuiz();

        quizAdapter = new QuizAdapter(getContext(), quizDTOList);

        lvQuiz = rootView.findViewById(R.id.lvQuiz);

        lvQuiz.setAdapter(quizAdapter);

        lvQuiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentCauHoiActivity = new Intent(getContext() ,CauHoiActivity.class);
                intentCauHoiActivity.putExtra("id" , position+1);
                startActivity(intentCauHoiActivity);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        quizDTOList = quizDao.layTatCaCacQuiz();
        lvQuiz.setAdapter(new QuizAdapter(getContext(), quizDTOList));


    }
}
