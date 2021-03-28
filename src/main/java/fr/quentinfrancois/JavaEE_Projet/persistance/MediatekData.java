package fr.quentinfrancois.JavaEE_Projet.persistance;

import mediatek2021.*;
import java.sql.*;
import java.time.ZoneId;
import java.util.*;

public class MediatekData implements PersistentMediatek {
    // Jean-François Brette 01/01/2018
    Connection connect;
    static {
        // injection dynamique de la dépendance dans le package stable mediatek2021
        Mediatek.getInstance().setData(new MediatekData());
    }

    private MediatekData() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##root", "root");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // renvoie la liste de tous les documents de la bibliothèque
    @Override
    public List<Document> catalogue(int type) {
        synchronized (MediatekData.class) {
            List<Document> docs = new ArrayList<Document>();
            try {
                PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM V_DOC WHERE TYPE=?");
                preparedStatement.setInt(1, type);
                preparedStatement.executeQuery();
                ResultSet res = preparedStatement.getResultSet();
                while (res.next()) {
                    int id = res.getInt("id");
                    String titre = res.getString("titre");
                    String desc = (res.getString("description"));
                    int annee = (res.getInt("annee"));
                    int nbExp = res.getInt("nbExemplaires");
                    int dispo = res.getInt("disponible");
                    switch (type) {
                        case 1: {
                            //CHAMPS CD
                            int nbPistes = res.getInt("nbPistesCD");
                            int durée = res.getInt("dureeCD");
                            String artiste = res.getString("artisteCD");
                            String maisEdi = res.getString("maisonEditionCD");
                            ArrayList<Piste> pistes = getPistes(titre, artiste);
                            CD cd = new CD(id, titre, desc, annee, nbExp,dispo, nbPistes, durée, artiste, pistes, maisEdi);
                            docs.add(cd);
                            break;
                        }
                        case 2: {
                            // Champs DVD
                            int durée = res.getInt("dureeDVD");
                            String realisateur = res.getString("realisateurDVD");
                            String producteur = res.getString("producteurDVD");
                            DVD dvd = new DVD(id, titre, desc, annee, nbExp,dispo, durée, realisateur, producteur);
                            docs.add(dvd);
                            break;
                        }
                        case 3: {
                            //Champs livre
                            int nbPages = res.getInt("nbPagesLIVRE");
                            String auteur = res.getString("auteurLIVRE");
                            String edition = res.getString("editionLIVRE");
                            Livre livre = new Livre(id, titre, desc, annee, nbExp,dispo, nbPages, auteur, edition);
                            docs.add(livre);
                            break;
                        }
                    }
                }
            } catch (SQLException throwables) {
            }
            return docs;
        }
    }

    private ArrayList<Piste> getPistes(String album, String artist) {
        synchronized (MediatekData.class) {
            ArrayList<Piste> pistes = new ArrayList<>();
            try {
                PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM PISTE WHERE albumFrom =? and artiste=?");
                preparedStatement.setString(1, album);
                preparedStatement.setString(2, artist);
                preparedStatement.executeQuery();
                ResultSet res = preparedStatement.getResultSet();
                while (res.next()) {
                    String track = res.getString("titre");
                    int duree = res.getInt("duree");
                    Piste p = new Piste(album, track, duree, artist);
                    pistes.add(p);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return pistes;
        }
    }

        // va récupérer le User dans la BD et le renvoie
    // si pas trouvé, renvoie null
    @Override
    public Utilisateur getUser(String login, String password) {
        String log = null;
        String pass = null;
        String droits = null;
        User u = null;
        try {
            String sql = "SELECT * FROM UTILISATEUR WHERE username=? and password=?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                log = res.getString("username");
                pass = res.getString("password");
                droits = res.getString("droits");
                break;
            }
            u = (log==null| pass==null | droits==null) ? null: new User(log, pass, droits);
            res.close();
            stmt.close();
    } catch (Exception e) {}
        return u;
    }

    // va récupérer le document de numéro numDocument dans la BD
    // et le renvoie
    // si pas trouvé, renvoie null
    @Override
    public Document getDocument(int numDocument) {
        synchronized (MediatekData.class) {
            AbsDocument doc = null;
            try {
                PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM V_DOC WHERE id =?");
                preparedStatement.setInt(1, numDocument);
                preparedStatement.executeQuery();
                ResultSet res = preparedStatement.getResultSet();
                while (res.next()) {
                    String titre = res.getString("titre");
                    String description = res.getString("description");
                    int annee = res.getInt("annee");
                    String type = res.getString("type");
                    int nbExemplaire = res.getInt("nbExemplaires");
                    int dispo = res.getInt("disponible");
                    int id = res.getInt("id");
                    switch (type) {
                        case "CD": {
                            int duree = res.getInt("dureeCD");
                            int nbpistes = res.getInt("nbpistesCD");
                            String artiste = res.getString("artisteCD");
                            String maisonEdition = res.getString("maisonEditionCD");
                            ArrayList<Piste> pistes = getPistes(titre, artiste);
                            doc = new CD(id, titre, description, annee, nbExemplaire, dispo, nbpistes, duree, artiste, pistes, maisonEdition);
                        }
                        case "DVD": {
                            int duree = res.getInt("dureeDVD");
                            String realisateur = res.getString("realisateurDVD");
                            String producteur = res.getString("producteurDVD");
                            doc = new DVD(id, titre, description, annee, nbExemplaire, dispo, duree, realisateur, producteur);
                        }
                        case "LIVRE": {
                            int nbPages = res.getInt("nbPagesLIVRE");
                            String auteur = res.getString("auteurLIVRE");
                            String edition = res.getString("editionLIVRE");
                            doc = new Livre(id, titre, description, annee, nbExemplaire, dispo, nbPages, auteur, edition);
                        }
                    }
                }
            } catch (SQLException e) {
            }
            return doc;
        }
    }

    // ajoute un nouveau document - exception à définir
    @Override
    public void newDocument(int type, Object... args) throws NewDocException {
        try {
            String titre = (String) args[0];
            String description = (String) args[1];
            int annee = (Integer) args[2];
            int nbExemplaire = (Integer) args[3];
            int dispo = (Integer) args[4];
            String sql = new String();
            switch (type){
                case 1:{
                    sql="INSERT INTO V_DOC(V_DOC.type, V_DOC.titre, V_DOC.description, V_DOC.annee, V_DOC.nbExemplaires, V_DOC.disponible, V_DOC.dureeCD, V_DOC.nbPistesCD, V_DOC.artisteCD, V_DOC.maisonEditionCD)" +
                            "VALUES(1, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    int duree = (Integer) args[5] ;
                    String artiste = (String) args[6] ;
                    String maisonEdition = (String) args[7];
                    int nbpistes = (Integer) args[8];
                    ArrayList<String> titresPistes = (ArrayList<String>) args[9];
                    ArrayList<Integer> dureesPistes = (ArrayList<Integer>) args[10];
                    //create statement and setParameters
                    PreparedStatement stmt = connect.prepareStatement(sql);
                    stmt.setString(1, titre);
                    stmt.setString(2, description);
                    stmt.setInt(3, annee);
                    stmt.setInt(4, nbExemplaire);
                    stmt.setInt(5, dispo);
                    stmt.setInt(6, duree);
                    stmt.setInt(7, nbpistes);
                    stmt.setString(8, artiste);
                    stmt.setString(9, maisonEdition);
                    stmt.executeUpdate();
                    stmt.close();
                    //create all pistes
                    for(int i=0; i<nbpistes;i++){
                        PreparedStatement stmt2 = connect.prepareStatement("INSERT INTO PISTE(num, albumFrom, titre, duree, artiste) VALUES(?, ?, ?, ?, ?)");
                        stmt2.setInt(1, i+1);
                        stmt2.setString(2, titre);
                        stmt2.setString(3, titresPistes.get(i));
                        stmt2.setInt(4, dureesPistes.get(i));
                        stmt2.setString(5, artiste);
                        stmt2.executeUpdate();
                        stmt2.close();
                    }
                    break;

                }
                case 2: {
                    sql="INSERT INTO V_DOC(V_DOC.type, V_DOC.titre, V_DOC.description, V_DOC.annee, V_DOC.nbExemplaires,V_DOC.disponible, V_DOC.dureeDVD, V_DOC.realisateurDVD, V_DOC.producteurDVD)" +
                            "VALUES(2, ?, ?, ?, ?, ?, ?, ?, ?)";
                    int duree = (Integer) args[5];
                    String realisateur = (String) args[6];
                    String producteur = (String) args[7];
                    //create statement and setParameters
                    PreparedStatement stmt = connect.prepareStatement(sql);
                    stmt.setString(1, titre);
                    stmt.setString(2, description);
                    stmt.setInt(3, annee);
                    stmt.setInt(4, nbExemplaire);
                    stmt.setInt(5, dispo);
                    stmt.setInt(6, duree);
                    stmt.setString(7, realisateur);
                    stmt.setString(8, producteur);
                    stmt.executeUpdate();
                    stmt.close();
                    break;
                }
                case 3: {
                    sql="INSERT INTO V_DOC(V_DOC.type, V_DOC.titre, V_DOC.description, V_DOC.annee, V_DOC.nbExemplaires, V_DOC.disponible,V_DOC.nbPagesLIVRE, V_DOC.auteurLIVRE, V_DOC.editionLIVRE)" +
                            "VALUES(3, ?, ?, ?, ?, ?, ?, ?, ?)";
                    int nbPages = (Integer) args[4];
                    String auteur = (String) args[5];
                    String edition = (String) args[6];
                    //create statement and setParameters
                    PreparedStatement stmt = connect.prepareStatement(sql);
                    stmt.setString(1, titre);
                    stmt.setString(2, description);
                    stmt.setInt(3, annee);
                    stmt.setInt(4, nbExemplaire);
                    stmt.setInt(5, dispo);
                    stmt.setInt(6, nbPages);
                    stmt.setString(7, auteur);
                    stmt.setString(8, edition);
                    stmt.executeUpdate();
                    stmt.close();
                    break;
                }
            }
        }
        catch (Exception e){
            throw new NewDocException("Une erreur est survenue lors de l'insertion du cocument dans la base de donnée, ré-essayer ou contacter le support...(code :"+e.getMessage());
        }
    }

    // supprime un document - exception à définir
    @Override
    public void suppressDoc(int numDoc) throws SuppressException {
        synchronized (MediatekData.class) {
            try {
                PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM V_DOC WHERE id = ?");
                preparedStatement.setInt(1, numDoc);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (Exception e) {
                throw new SuppressException(e.getMessage());
            }
        }
    }

}
