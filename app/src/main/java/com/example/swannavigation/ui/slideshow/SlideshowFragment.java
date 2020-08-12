package com.example.swannavigation.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.swannavigation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlideshowFragment extends Fragment implements View.OnClickListener {


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ToggleButton tb;
    RadioButton drive;
    RadioButton walk;
    RadioButton cycle;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        tb = (ToggleButton) root.findViewById(R.id.unitsTB);


        db.collection(uid).document("Setting").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                if (Objects.requireNonNull(task.getResult().get("units")).toString().equals("metric")) {
                    tb.setChecked(false);
                } else if (Objects.requireNonNull(task.getResult().get("units")).toString().equals("imperial")) {
                    tb.setChecked(true);
                }

                if (Objects.requireNonNull(task.getResult().get("mode")).toString().equals("driving")) {
                    // mode.put("mode", "driving");
                    drive.setChecked(true);
                    Toast.makeText(getActivity(), "A", Toast.LENGTH_SHORT).show();

                } else if (Objects.requireNonNull(task.getResult().get("mode")).toString().equals("walking")) {
                    walk.setChecked(true);
                    //  mode.put("mode", "walking");
                    Toast.makeText(getActivity(), "B", Toast.LENGTH_SHORT).show();

                } else if (Objects.requireNonNull(task.getResult().get("mode")).toString().equals("cycling")) {
                    cycle.setChecked(true);
                    //  mode.put("mode", "cycling");
                    Toast.makeText(getActivity(), "C", Toast.LENGTH_SHORT).show();

                }

            }


        });
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Map<String, Object> unit = new HashMap<>();

                if (isChecked) {
                    unit.put("units", "imperial");
                } else {
                    unit.put("units", "metric");
                }


                db.collection(uid).document("Setting").update(unit);
            }
        });


        drive = root.findViewById(R.id.driving);
        walk = root.findViewById(R.id.walking);
        cycle = root.findViewById(R.id.public_Tran);

        drive.setOnClickListener(this);
        walk.setOnClickListener(this);
        cycle.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        Map<String, Object> mode = new HashMap<>();

        if (view.getId() == R.id.driving) {
            mode.put("mode", "driving");
            drive.setChecked(true);
            Toast.makeText(getActivity(), "A", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.walking) {
            walk.setChecked(true);
            mode.put("mode", "walking");
            Toast.makeText(getActivity(), "B", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.public_Tran) {
            cycle.setChecked(true);
            mode.put("mode", "cycling");
            Toast.makeText(getActivity(), "C", Toast.LENGTH_SHORT).show();

        }

        db.collection(uid).document("Setting").update(mode);

    }


}

