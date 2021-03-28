package fr.quentinfrancois.JavaEE_Projet.services;

import mediatek2021.Mediatek;
import mediatek2021.NewDocException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "creationDoc", value="/creation-servlet")
public class CreationServlet extends HttpServlet {


    public void destroy() {}

    //type = 1: CD, 2 = DVD, 3 livre
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Object[] args = null;
            String titre = request.getParameter("titre");
            String desc = request.getParameter("desc")=="" ? "NO-DESCRIPTION" : request.getParameter("desc");
            int annee = Integer.parseInt(request.getParameter("annee"));
            int nbExemplaires = Integer.parseInt(request.getParameter("exemplaires"));
            int type = Integer.parseInt(request.getParameter("type"));
            switch (type) {
                //CD
                case 1: {
                    String artiste = request.getParameter("artiste");
                    String maisEdi = request.getParameter("maisEdition");
                    int nbPistes = Integer.parseInt(request.getParameter("nbPistes"));
                    ArrayList<String> titresPistes = new ArrayList<>();
                    ArrayList<Integer> dureesPistes = new ArrayList<>();
                    for (int i = 0; i < nbPistes; i++) {
                        String colomnTitre = "piste" + i;
                        String colomnDuree = "dureePiste" + i;
                        String titrePiste = request.getParameter(colomnTitre);
                        int dureePiste = Integer.parseInt(request.getParameter(colomnDuree));
                        titresPistes.add(titrePiste);
                        dureesPistes.add(dureePiste);
                    }
                    int dureeTotal = 0;
                    for (int dureePiste : dureesPistes)
                        dureeTotal += dureePiste;
                    args = new Object[]{titre, desc, annee, nbExemplaires, 1, dureeTotal, artiste, maisEdi, nbPistes, titresPistes, dureesPistes};
                    for(Object c : args){
                        System.out.println(c);
                    }
                    break;
                }
                //DVD
                case 2: {
                    int dureeDVD = Integer.parseInt(request.getParameter("dureeDVD"));
                    String realisateur = request.getParameter("realisateur");
                    String producteur = request.getParameter("producteur");
                    args = new Object[]{titre, desc, annee, nbExemplaires, 1, dureeDVD, realisateur, producteur};
                    break;
                }
                //LIVRE
                case 3: {
                    int nbPages = Integer.parseInt(request.getParameter("nbPages"));
                    String auteur = request.getParameter("auteur");
                    String edition = request.getParameter("edition");
                    args = new Object[]{titre, desc, annee, nbExemplaires, 1, nbPages, auteur, edition};
                    break;
                }
            }
            Mediatek.getInstance().newDocument(type, args);
            request.setAttribute("msg", "l'ajout du document a bien été effectué, merci !");
        } catch (NewDocException e) {
            request.setAttribute("msg", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
        }
}