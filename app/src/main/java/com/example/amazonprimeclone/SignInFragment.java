package com.example.amazonprimeclone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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

    TextView tvConditions;
    private CheckBox checkBox;
    EditText inputPassword, inputEmail;
    Button btnCreateNewAccount, btnSignIn;
    FrameLayout frameLayout;
    TextView tvForgotPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private SharedPreference_Config sharedPreference_config;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        inputPassword = view.findViewById(R.id.sign_in_password);
        inputEmail = view.findViewById(R.id.sign_in_email);
        checkBox = view.findViewById(R.id.checkBox);
        btnCreateNewAccount = view.findViewById(R.id.btnCreateNewAccount);
        frameLayout = getActivity().findViewById(R.id.fragment_container);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        progressBar = view.findViewById(R.id.progressBar);
        btnSignIn = view.findViewById(R.id.sign_in_button);
        sharedPreference_config = new SharedPreference_Config(getContext());

        if (sharedPreference_config.readLoginStatus()) {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        }

        firebaseAuth
                = FirebaseAuth.getInstance();


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b) {
                    inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        btnCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SignUpFragment());

            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ForgotPasswordFragment());

            }
        });

        tvConditions = view.findViewById(R.id.tvConditions);

        String line1 = getColoredSpanned("By continuing, you agree to Amazons's", "#FFFFFF");
        String line2 = getColoredSpanned("Conditions of Use", "#0099CC");
        String line3 = getColoredSpanned("and", "#FFFFFF");
        String line4 = getColoredSpanned("Privacy Notice", "#0099CC");

        tvConditions.setText(Html.fromHtml(line1 + " " + line2 + " " + line3 + " " + line4));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        return view;

    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getActivity().findViewById(R.id.frameLayout1).getId(), fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private void signIn() {
        final String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //authenticate user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            // there was an error
                            if (password.length() < 6) {
                                inputPassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(getContext(), getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }


                        } else {
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(getContext(), MainActivity.class);

                            sharedPreference_config.writeLoginStatus(true);

                            startActivity(intent);
                            getActivity().finish();


                        }
                    }
                });


    }


}

