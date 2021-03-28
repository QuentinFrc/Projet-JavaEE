package fr.quentinfrancois.JavaEE_Projet.services;

import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

@WebServlet(name = "InitializeCreation", value="/initCreation-servlet")
public class InitializeCreationServlet extends HttpServlet {


    public void destroy() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", session.getAttribute("user"));
        request.setAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        this.getServletContext().getRequestDispatcher("/WEB-INF/creationDoc.jsp").forward(request, response);
    }
}