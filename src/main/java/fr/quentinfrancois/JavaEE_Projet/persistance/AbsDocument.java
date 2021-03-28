package fr.quentinfrancois.JavaEE_Projet.persistance;

import mediatek2021.Document;

public abstract class AbsDocument implements Document {
    private String titre;
    private String description;
    private int annee;
    private int nbExemplaires;
    private int id;
    private int disponible;

    public AbsDocument() {
    }

    public AbsDocument(int id, String titre, String description, int annee, int nbExemplaires, int dispo) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.annee = annee;
        this.nbExemplaires = nbExemplaires;
        this.disponible = dispo;
    }


    @Override
    public Object[] data() {
        return new Object[]{id, titre, description, annee, nbExemplaires, disponible};
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNbExemplaires() {
        return nbExemplaires;
    }

    public void setNbExemplaires(int nbExemplaires) {
        this.nbExemplaires = nbExemplaires;
    }
}

