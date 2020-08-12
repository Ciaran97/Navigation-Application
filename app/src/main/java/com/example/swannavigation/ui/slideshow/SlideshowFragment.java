package com.example.swannavigation.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.swannavigation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlideshowFragment extends Fragment {


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ToggleButton tb;
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


                db.collection(uid).document("Setting").set(unit);
            }
        });
        return root;
    }
}

