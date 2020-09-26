package com.example.wangdavid.soap.Activity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import com.example.wangdavid.soap.Adapter.ActuAdapter;
import com.example.wangdavid.soap.Class.Actu;
import com.example.wangdavid.soap.Fragment.Fragment_NewsFeed;

import com.example.wangdavid.soap.Fragment.Fragment_Profile;
import com.example.wangdavid.soap.Fragment.Fragment_Search;
import com.example.wangdavid.soap.Fragment.Fragment_SignUp;
import com.example.wangdavid.soap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public  class MainActivity extends AppCompatActivity implements
        Fragment_NewsFeed.OnFragmentInteractionListener , Fragment_SignUp.OnFragmentInteractionListener,
        Fragment_Search.OnFragmentInteractionListener, Fragment_Profile.OnFragmentInteractionListener{


    //Fragment
    private Fragment_NewsFeed news = new Fragment_NewsFeed();
    private Fragment_SignUp signUp = new Fragment_SignUp();
    private Fragment_Search search = new Fragment_Search();
    private Fragment_Profile profile = new Fragment_Profile();
    //Firebase
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    //SharedPreferences
    SharedPreferences sharedPreferences;
    //Id layout
    private ViewPager viewPager;
    private BottomNavigationView navigation;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);


        navigation = (BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Fil d'actualité");

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new Fragment_NewsFeed();
                    case 1:
                        return new Fragment_Search();
                    case 2:
                        return new Fragment_Profile();

                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

         ///////////////////////////////////////////////////////////////////////////////////////////

        firestore =FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        /*firestore.collection("Utilisateur")
                .document(auth.getCurrentUser().getUid())
                .get().
                addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
        {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {




                sharedPreferences = getBaseContext().getSharedPreferences("soap", MODE_PRIVATE);
                sharedPreferences.edit().putString("prenom_utilisateur", (documentSnapshot.get("prenom")).toString()).apply();



                String temp = sharedPreferences.getString("prenom_utilisateur",null);


            }
        });*/



        firestore.collection("Utilisateur")
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
                                sharedPreferences = getBaseContext().getSharedPreferences("soap", MODE_PRIVATE);
                                sharedPreferences.edit().putString("nom_utilisateur", (document.get("nom")).toString()).apply();
                                sharedPreferences.edit().putString("prenom_utilisateur",(document.get("prenom")).toString()).apply();
                                sharedPreferences.edit().putString("date_naissance_utilisateur",(document.get("date_naissance")).toString()).apply();
                                sharedPreferences.edit().putString("sexe_utilisateur",(document.get("sexe")).toString()).apply();
                                sharedPreferences.edit().putString("mail_utilisateur",(document.get("mail")).toString()).apply();
                                sharedPreferences.edit().putString("password_utilisateur",(document.get("mot_de_passe")).toString()).apply();


                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());

                        }
                    }
                });



        ////////////////////////////////////////////////////////////////////////////////////////////
        /*FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_main,news).commit();*/
        ////////////////////////////////////////////////////////////////////////////////////////////


    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    setTitle("Fil d'actualité");
                    return true;
                case R.id.navigation_search:
                    viewPager.setCurrentItem(1);
                    setTitle("Recherche");
                    return true;
                case R.id.navigation_profile:
                    viewPager.setCurrentItem(2);
                    setTitle("Mon profil");
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
