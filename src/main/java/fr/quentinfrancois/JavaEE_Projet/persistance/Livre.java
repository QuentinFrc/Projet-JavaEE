package fr.quentinfrancois.JavaEE_Projet.persistance;

public class Livre extends AbsDocument{
    private int nbPages;
    private String auteur;
    private String edition;

    public Livre() {super();}

    public Livre(int id, String titre, String description, int annee, int nbExemplaires, int dispo, int nbPages, String auteur, String edition) {
        super(id, titre, description, annee, nbExemplaires, dispo);
        this.nbPages = nbPages;
        this.auteur = auteur;
        this.edition = edition;
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
        //champs spécifique aux Livres
        tab[nbChampsCom] = nbPages;
        tab[nbChampsCom+1] = auteur;
        tab[nbChampsCom+2] = edition;
        return tab;
    }


    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

}
