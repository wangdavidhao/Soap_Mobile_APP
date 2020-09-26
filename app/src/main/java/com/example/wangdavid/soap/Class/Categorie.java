package com.example.wangdavid.soap.Class;

import java.util.Map;

public class Categorie {

    //Attributs
    private String m_auteur;
    private String m_titre;
    private Map<String,Object> m_date_parution;

    //Constructeur
    public Categorie(String auteur, String titre, Map<String, Object> date_parution)
    {
        m_auteur=auteur;
        m_titre=titre;
        m_date_parution=date_parution;
    }

    //Getteurs
    public String getAuteur()
    {
        return m_auteur;
    }
    public String getTitre()
    {
        return m_titre;
    }

    public Map<String,Object> getDateParution()
    {
        return m_date_parution;
    }

}
