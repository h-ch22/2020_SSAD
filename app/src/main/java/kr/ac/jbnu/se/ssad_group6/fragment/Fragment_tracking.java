package kr.ac.jbnu.se.ssad_group6.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

import kr.ac.jbnu.se.ssad_group6.R;

public class Fragment_tracking extends Fragment {
    private final Calendar calendar = Calendar.getInstance();
    private int hour = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking,
                container, false);

        Button btn_date = view.findViewById(R.id.btn_date);
        Button btn_time = view.findViewById(R.id.btn_time);
        CheckBox check_date = view.findViewById(R.id.checkbox_date);
        CheckBox check_time = view.findViewById(R.id.checkbox_time);

        check_date.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_date.setText("전체 보기");
            }
        });

        check_time.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_time.setText("전체 보기");
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_date.setBackgroundResource(R.drawable.btn_rounded_clicked);
                btn_time.setBackgroundResource(R.drawable.btn_roundedbtn);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month + 1);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        btn_date.setText(year + " - " + month + " - " + dayOfMonth + " 이후");
                        check_date.setChecked(false);
                    }
                }, year, month, dayofMonth);

                datePickerDialog.show();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_time.setBackgroundResource(R.drawable.btn_rounded_clicked);
                btn_date.setBackgroundResource(R.drawable.btn_roundedbtn);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btn_time.setText(hourOfDay + " : " + minute + " 이후");
                        check_time.setChecked(false);
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(getContext()));

                timePickerDialog.show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}