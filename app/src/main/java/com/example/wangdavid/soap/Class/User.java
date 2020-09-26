package com.example.wangdavid.soap.Class;

import java.util.Map;

public class User {

    //Attributs
    private String m_nom;
    private String m_prenom;
    private boolean m_sexe;
    private String m_mail;
    private String m_mdp;
    private Map<String,Object> m_date_naissance;
    private String m_telephone;

    //Constructeur
    public User(String nom, String prenom, boolean sexe, String mail, String mdp, Map<String,Object> date_naissance, String telephone)
    {
        m_nom=nom;
        m_prenom=prenom;
        m_sexe=sexe;
        m_mail=mail;
        m_mdp=mdp;
        m_date_naissance=date_naissance;
        m_telephone=telephone;
    }

    ///Getteurs
    public String getNom()
    {
        return m_nom;
    }

    public String getPrenom()
    {
        return m_prenom;
    }

    public boolean getSexe()
    {
        return m_sexe;
    }

    public String getMail()
    {
        return m_mail;
    }

    public String getMdp()
    {
        return m_mdp;
    }

    public Map<String, Object> getDateNaissance()
    {
        return m_date_naissance;
    }

    public String getTelephone()
    {
        return m_telephone;
    }

}
