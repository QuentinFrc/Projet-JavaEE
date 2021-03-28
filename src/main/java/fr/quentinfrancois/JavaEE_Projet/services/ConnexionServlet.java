package fr.quentinfrancois.JavaEE_Projet.services;

import mediatek2021.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "connexion", value="/connexion-servlet")
public class ConnexionServlet extends HttpServlet {


    public void destroy() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("login");
        String mdp = request.getParameter("password");
        Utilisateur user = Mediatek.getInstance().getUser(username, mdp);
        if(user==null) {
            request.setAttribute("msg", "L'identifiant et/ou le mot de passe sont incorrects");
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            String droits = (String) user.data()[2];
            if(droits.contentEquals("biblio"))
                this.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
            else {
                request.setAttribute("msg", "L'application n'accepte que les bibliothécaire pour le moment désolé...");
                this.getServletContext().getRequestDispatcher(("/WEB-INF/index.jsp")).forward(request, response);
            }
        }
    }
}