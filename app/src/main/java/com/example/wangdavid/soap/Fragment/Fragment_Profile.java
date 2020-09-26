package com.example.wangdavid.soap.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wangdavid.soap.Activity.LoginActivity;
import com.example.wangdavid.soap.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Profile extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SharedPreferences sharedPreferences;

    FirebaseAuth auth;

    Button button_deconnexion;
    TextView textview_last_name;
    TextView textview_first_name;
    TextView textview_date;
    EditText editText_num_tel;
    EditText editText_mail;
    EditText editText_password;

    String last_name, first_name, date, num_tel, mail, password;


    public Fragment_Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Profile newInstance(String param1, String param2) {
        Fragment_Profile fragment = new Fragment_Profile();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment__profile, container, false);

        button_deconnexion = (Button)view.findViewById(R.id.button_deconnexion);
        textview_first_name = (TextView)view.findViewById(R.id.textview_first_name);
        textview_last_name = (TextView)view.findViewById(R.id.textview_last_name);
        textview_date = (TextView)view.findViewById(R.id.textview_date);
        editText_mail =(EditText)view.findViewById(R.id.editText_mail);
        editText_password =(EditText)view.findViewById(R.id.editText_password);

        sharedPreferences = this.getActivity().getSharedPreferences("soap", Context.MODE_PRIVATE);

        last_name = sharedPreferences.getString("nom_utilisateur",null);
        textview_last_name.setText(last_name);

        first_name = sharedPreferences.getString("prenom_utilisateur",null);
        textview_first_name.setText(first_name);


        date = sharedPreferences.getString("date_naissance_utilisateur",null);
        textview_date.setText(date);

        mail = sharedPreferences.getString("mail_utilisateur",null);
        editText_mail.setText(mail);

        auth = FirebaseAuth.getInstance();

        button_deconnexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                auth.signOut();
                Intent login = new Intent(getContext(), LoginActivity.class);
                startActivity(login);
            }
        });



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
