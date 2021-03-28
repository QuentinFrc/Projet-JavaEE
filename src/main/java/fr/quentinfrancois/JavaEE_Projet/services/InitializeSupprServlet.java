package fr.quentinfrancois.JavaEE_Projet.services;

import fr.quentinfrancois.JavaEE_Projet.persistance.Piste;
import mediatek2021.Document;
import mediatek2021.Mediatek;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "InitializeSuppression", value="/initSuppress-servlet")
public class InitializeSupprServlet extends HttpServlet {


    public void destroy() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Document> CDs = new ArrayList<>();
        ArrayList<Document> DVDs = new ArrayList<>();
        ArrayList<Document> Livres = new ArrayList<>();
        CDs.addAll(Mediatek.getInstance().catalogue(1));
        DVDs.addAll(Mediatek.getInstance().catalogue(2));
        Livres.addAll(Mediatek.getInstance().catalogue(3));
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        request.setAttribute("CDs", CDs);
        request.setAttribute("DVDs", DVDs);
        request.setAttribute("Livres", Livres);
        this.getServletContext().getRequestDispatcher("/WEB-INF/suppressionDoc.jsp").forward(request, response);
    }
}