package com.castle.cooltask;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by apple on 15/6/28.
 */
public class AddFragment extends Fragment implements View.OnClickListener {

    private Button btn_complete;
    private Button btn_cancel;

    private MainActivity activity;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_task,null);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_complete = (Button) view.findViewById(R.id.btn_complete);
        activity = (MainActivity)getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_cancel.setOnClickListener(this);
        btn_complete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        activity.rollBack();




    }
}
