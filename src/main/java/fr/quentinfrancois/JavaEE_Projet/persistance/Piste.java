package fr.quentinfrancois.JavaEE_Projet.persistance;

public class Piste {
    private String albumFrom;
    private String titre;
    private int duree;
    private String artiste;


    public Piste(String albumFrom, String tit, int dur, String artiste){
        this.titre = tit;
        this.duree = dur;
        this.artiste = artiste;
        this.albumFrom = albumFrom;
    }

    @Override
    public String toString() {
        return titre +" "+ duree;
    }

    public Object[] data(){return new Object[]{titre, duree, albumFrom, artiste};}
}
