package com.example.wangdavid.soap.Class;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.Map;

///Classe Actu hérite de la classe Categorie
public class Actu {

    //Attributs

    private String m_auteur;
    private String m_titre;
    private Map<String, Object> m_date_parution;

    private String m_commentaire;
    private int m_nb_commentaire;
    private int m_nb_etoile;
    private int m_nb_jaime;
    private String m_categorie;
    private String m_url;

    //Constructeur
    public Actu(String auteur, String titre,  String commentaire, int nb_commentaire, int nb_etoile, int nb_jaime, String categorie, String url)
    {
        //Héritage de la classe Categorie
        //super(auteur, titre, date_parution);

        m_auteur=auteur;
        m_titre=titre;
        //m_date_parution=date_parution;

        m_commentaire=commentaire;
        m_nb_commentaire=nb_commentaire;
        m_nb_etoile=nb_etoile;
        m_nb_jaime=nb_jaime;
        m_categorie=categorie;
        m_url=url;



    }

    //Getteurs

    public String getAuteur(){ return m_auteur;}

    public String getTitre(){ return m_titre;}

    public String getCommentaire()
    {
        return m_commentaire;
    }

    public int getNbCommentaire()
    {
        return m_nb_commentaire;
    }

    public int getNbEtoile()
    {
        return m_nb_etoile;
    }

    public int getNbJaime()
    {
        return m_nb_jaime;
    }

    public String getCategorie(){return m_categorie;}

    public String getUrl(){ return m_url;}


}
