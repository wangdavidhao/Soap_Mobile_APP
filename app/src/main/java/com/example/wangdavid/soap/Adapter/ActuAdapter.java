package com.example.wangdavid.soap.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangdavid.soap.Class.Actu;
import com.example.wangdavid.soap.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.System.load;

public class ActuAdapter extends ArrayAdapter<Actu> {


    private final Context context;
    private List<Actu> actu=new ArrayList<>();
     SharedPreferences sharedPreferences;

    private String first_name;


    public ActuAdapter(Context context, ArrayList<Actu> actualite ) {

        super(context,0,actualite);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.actu=actualite;


    }

    public View getView(int position, View view, ViewGroup parent) {

        View rowView = view;


        if(rowView == null)
            rowView = LayoutInflater.from(context).inflate(R.layout.background_suggestion,parent,false);

        Actu actualite=actu.get(position);

        sharedPreferences = this.getContext().getSharedPreferences("soap", MODE_PRIVATE);
        first_name = sharedPreferences.getString("prenom_utilisateur", null);


        TextView textViewUser = (TextView)rowView.findViewById(R.id.textViewUser);
        TextView textViewTitre = (TextView)rowView.findViewById(R.id.textViewTitre);
        TextView textViewCom = (TextView)rowView.findViewById(R.id.textViewCom);
        TextView textViewNbCom= (TextView)rowView.findViewById(R.id.textViewNbCom);
        TextView textViewNbJaime = (TextView)rowView.findViewById(R.id.textViewNbJaime);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView4);

        ImageView star1 = (ImageView) rowView.findViewById(R.id.star1);
        ImageView star2 = (ImageView) rowView.findViewById(R.id.star2);
        ImageView star3 = (ImageView) rowView.findViewById(R.id.star3);
        ImageView star4 = (ImageView) rowView.findViewById(R.id.star4);
        ImageView star5 = (ImageView) rowView.findViewById(R.id.star5);
        //Récupération des données et cast

        String url=actualite.getUrl();

        int nb_etoile=actualite.getNbEtoile();

        int cast_nb_com=actualite.getNbCommentaire();
        String nb_com=String.valueOf(cast_nb_com);

        int cast_nb_jaime=actualite.getNbJaime();
        String nb_jaime=String.valueOf(cast_nb_jaime);


        Glide.with(this.getContext()).load(url).into(imageView);

        textViewUser.setText(first_name +" a partagé un(e) " + actualite.getCategorie());
        textViewTitre.setText(actualite.getTitre());
        textViewCom.setText(actualite.getCommentaire());
        textViewNbCom.setText(nb_com + " autre commentaires");
        textViewNbJaime.setText(nb_jaime + " j'aime");

        switch (nb_etoile)
        {
            case 1:
                star1.setVisibility(view.VISIBLE);
                break;
            case 2:
                star1.setVisibility(view.VISIBLE);
                star2.setVisibility(view.VISIBLE);
                break;
            case 3:
                star1.setVisibility(view.VISIBLE);
                star2.setVisibility(view.VISIBLE);
                star3.setVisibility(view.VISIBLE);
                break;
            case 4:
                star1.setVisibility(view.VISIBLE);
                star2.setVisibility(view.VISIBLE);
                star3.setVisibility(view.VISIBLE);
                star4.setVisibility(view.VISIBLE);
                break;
            case 5:
                star1.setVisibility(view.VISIBLE);
                star2.setVisibility(view.VISIBLE);
                star3.setVisibility(view.VISIBLE);
                star4.setVisibility(view.VISIBLE);
                star5.setVisibility(view.VISIBLE);
                break;

        }



        return rowView;

    }

}
