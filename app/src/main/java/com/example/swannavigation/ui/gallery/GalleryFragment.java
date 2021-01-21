package com.example.swannavigation.ui.gallery;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swannavigation.AddressView;
import com.example.swannavigation.R;
import com.example.swannavigation.RecyclerAdaptor;
import com.example.swannavigation.ui.home.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("Routes");

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String uid = mAuth.getCurrentUser().getUid();

    ListView listview;

    ArrayList<AddressView> arrayList = new ArrayList();
    RecyclerAdaptor arrayAdapt;
    private RecyclerView recycView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // galleryViewModel =ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trips, container, false);

        recycView = root.findViewById(R.id.recycler_view);

        recycView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        arrayAdapt = new RecyclerAdaptor(arrayList, requireContext());
        recycView.setAdapter(arrayAdapt);

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Geocoder geocoder;
                List<Address> addressesO;
                List<Address> addressesD;
                geocoder = new Geocoder(requireContext(), Locale.getDefault());

                Trip trip;
                for (DataSnapshot snapshot : dataSnapshot.child(uid).getChildren()) {
                    try {
                        addressesD = geocoder.getFromLocation(Double.parseDouble(snapshot.child("Destination").child("Latitude").getValue().toString()), Double.parseDouble(snapshot.child("Destination").child("Longitude").getValue().toString()), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                        addressesO = geocoder.getFromLocation(Double.parseDouble(snapshot.child("Origin").child("Latitude").getValue().toString()), Double.parseDouble(snapshot.child("Origin").child("Longitude").getValue().toString()), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


                        String OriginAddress = addressesO.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String DestinationAddress = addressesD.get(0).getAddressLine(0);


                        AddressView addV = new AddressView(DestinationAddress, OriginAddress, snapshot.getKey());


                        arrayList.add(addV);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                arrayAdapt.notifyDataSetChanged();

            }

        });

        return root;
    }
}
