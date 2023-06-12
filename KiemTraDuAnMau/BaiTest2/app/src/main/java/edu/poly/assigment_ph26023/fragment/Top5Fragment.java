package edu.poly.assigment_ph26023.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.poly.assigment_ph26023.R;
import edu.poly.assigment_ph26023.adapter.TopAdapter;
import edu.poly.assigment_ph26023.dao.ThongKeDao;
import edu.poly.assigment_ph26023.objects.Top;

public class Top5Fragment extends Fragment {
    private RecyclerView rcvTop;
    private TopAdapter adapter;
    private ThongKeDao thongKeDao;
    private ArrayList<Top> list;

    private Spinner spnSapXep;
    private String []duLieuSpinner;

    public static Top5Fragment newInstance() {
        Top5Fragment fragment = new Top5Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spnSapXep = view.findViewById(R.id.spn_sapXep);
        rcvTop = view.findViewById(R.id.rcv_top);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvTop.setLayoutManager(layoutManager);

        duLieuSpinner = new String[]{"Giảm dần", "Tăng dần"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, duLieuSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSapXep.setAdapter(arrayAdapter);

        thongKeDao = new ThongKeDao(getActivity());
        adapter = new TopAdapter(getActivity());

        spnSapXep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    list = thongKeDao.getTopUserDESC();
                    adapter.setData(list);
                    rcvTop.setAdapter(adapter);
                } else {
                    list = thongKeDao.getTopUserASC();
                    adapter.setData(list);
                    rcvTop.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}