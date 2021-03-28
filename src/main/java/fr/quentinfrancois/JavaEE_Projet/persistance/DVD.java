package fr.quentinfrancois.JavaEE_Projet.persistance;


public class DVD extends AbsDocument{
    private int durée;
    private String realisateur;
    private String producteur;

    public DVD() {super();}

    public DVD(int id, String titre, String description, int annee, int nbExemplaires, int dispo, int durée, String realisateur, String producteur) {
        super(id, titre, description, annee, nbExemplaires, dispo);
        this.durée = durée;
        this.realisateur = realisateur;
        this.producteur = producteur;
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
        //champs spécifique aux DVDs
        tab[nbChampsCom] = durée;
        tab[nbChampsCom+1] = realisateur;
        tab[nbChampsCom+2] = producteur;
        return tab;
    }


    public int getDurée() {
        return durée;
    }

    public void setDurée(int durée) {
        this.durée = durée;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getProducteur() {
        return producteur;
    }

    public void setProducteur(String producteur) {
        this.producteur = producteur;
    }



}
