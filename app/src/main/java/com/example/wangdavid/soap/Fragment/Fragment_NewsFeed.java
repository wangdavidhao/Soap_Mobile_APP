package com.example.wangdavid.soap.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wangdavid.soap.Adapter.ActuAdapter;
import com.example.wangdavid.soap.Class.Actu;
import com.example.wangdavid.soap.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_NewsFeed.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_NewsFeed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_NewsFeed extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    //Déclarations des variables
    ListView list;
    ActuAdapter actuAdapter;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private ArrayList<Actu> actu;

    private OnFragmentInteractionListener mListener;



    public Fragment_NewsFeed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_NewsFeed.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_NewsFeed newInstance(String param1, String param2) {
        Fragment_NewsFeed fragment = new Fragment_NewsFeed();
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
        View view ;
        view= inflater.inflate(R.layout.fragment_fragment__news_feed, container, false);

        //Identification avec les ids des layouts
        list=(ListView)view.findViewById(R.id.list);
        actu = new ArrayList<>();

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("Actu")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String autor=document.getData().get("auteur").toString();
                                String title=document.getData().get("titre").toString();
                                //Map<String, Object> parution=(Map<String,Object>)document.getData().get("date_parution");
                                String com=document.getData().get("commentaire").toString();
                                int m=0;

                                ///Cast des int
                                int nb_com;
                                nb_com=Integer.parseInt(document.getData().get("nb_commentaire").toString());
                                int nb_etoile;
                                nb_etoile=Integer.parseInt(document.getData().get("nb_etoile").toString());
                                int nb_jaime;
                                nb_jaime=Integer.parseInt(document.getData().get("nb_jaime").toString());
                                int j=nb_jaime;

                                String cat=document.getData().get("categorie").toString();
                                String URL=document.getData().get("image_url").toString();

                                ///Allocation d'un nouveau fil d'actualité
                                actu.add( new Actu (autor, title, com, nb_com, nb_etoile, nb_jaime, cat, URL));


                                actuAdapter = new ActuAdapter(getActivity(),actu);
                                //On ajoute à la listView avec setAdapter
                                list.setAdapter(actuAdapter);


                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());

                        }
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
