package com.example.swannavigation.ui.gallery;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GalleryViewModel extends ViewModel {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("Routes");

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

            }

        });

    }
}
