package fr.quentinfrancois.JavaEE_Projet.persistance;

import java.util.ArrayList;

public class CD extends AbsDocument{
    private int nbPistes;
    private int durée;
    private String artiste;
    private ArrayList<Piste> pistes;
    private String maisonEditions;

    public CD() {super();}

    public CD(int id, String titre, String description, int annee, int nbExemplaires, int dispo, int nbPistes, int durée, String artiste, ArrayList<Piste> pistes, String maisonEditions) {
        super(id, titre, description, annee, nbExemplaires, dispo);
        this.nbPistes = nbPistes;
        this.durée = durée;
        this.artiste = artiste;
        this.pistes = pistes;
        this.maisonEditions = maisonEditions;
    }


    @Override
    public Object[] data(){
        Object[] superData = super.data();
        int nbChampsCom = superData.length;
        int nbChampsSpe = this.getClass().getDeclaredFields().length;
        //Champs commun à tous type de documents
        Object[] tab = new Object[nbChampsCom+nbChampsSpe];
        for(int i=0; i<nbChampsCom; i++)
            tab[i] = superData[i];
        //champs spécifique aux CD
        tab[nbChampsCom] = nbPistes;
        tab[nbChampsCom+1] = durée;
        tab[nbChampsCom+2] = artiste;
        tab[nbChampsCom+3] = maisonEditions;
        tab[nbChampsCom+4] = pistes;
        return tab;
    }

    public int getNbPistes() {
        return nbPistes;
    }
    public void setNbPistes(int nbPistes) {
        this.nbPistes = nbPistes;
    }

    public int getDurée() {
        return durée;
    }
    public void setDurée(int durée) {
        this.durée = durée;
    }

    public ArrayList<Piste> getPistes() {
        return pistes;
    }
    public void setPistes(ArrayList<Piste> pistes) {
        this.pistes = pistes;
    }

    public String getMaisonEditions() {
        return maisonEditions;
    }
    public void setMaisonEditions(String maisonEditions) {
        this.maisonEditions = maisonEditions;
    }
}
