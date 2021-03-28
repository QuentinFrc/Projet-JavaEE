package fr.quentinfrancois.JavaEE_Projet.services;

import mediatek2021.Mediatek;
import mediatek2021.NewDocException;
import mediatek2021.SuppressException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "suppresionDoc", value="/suppression-servlet")
public class SupressionServlet extends HttpServlet {


    public void destroy() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numeroDocument = Integer.parseInt(request.getParameter("idDoc"));
        try {
            Mediatek.getInstance().suppressDoc(numeroDocument);
            request.setAttribute("msg", "la suppression du document a bien été effectué, merci !");
        } catch (SuppressException e) {
            request.setAttribute("msg", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
    }
}