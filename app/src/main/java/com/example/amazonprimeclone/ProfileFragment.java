
package com.example.amazonprimeclone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private SharedPreference_Config sharedPreference_config;
    private TextView tvName;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ProgressBar progressBar;
    ImageView btnLogout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreference_config = new SharedPreference_Config(getContext());

        // firebase instances
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        // bind views
        progressBar = view.findViewById(R.id.progressBar3);
        tvName = view.findViewById(R.id.tvName);
        ImageView btnLogout = view.findViewById(R.id.btnLogout);



        DocumentReference docRef = firebaseFirestore.collection("USERS").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        progressBar.setVisibility(View.GONE);

                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String name = document.get("name").toString();
                        String email = document.get("email").toString();


                        tvName.setText(name);

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "No such document");

                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "get failed with ", task.getException());

                }
            }
        });





        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                new MaterialAlertDialogBuilder(getContext(), R.drawable.border_background)


                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to logout this app?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPreference_config.writeLoginStatus(false);
                                startActivity(new Intent(getContext(), RegistrationActivity.class));
                                getActivity().finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();


            }
        });


        return view;
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getActivity().findViewById(R.id.fragment_container).getId(), fragment)
                    .commit();
            return true;
        }
        return false;
    }


}