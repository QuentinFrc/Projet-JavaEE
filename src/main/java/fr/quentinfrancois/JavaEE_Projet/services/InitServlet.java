package fr.quentinfrancois.JavaEE_Projet.services;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "init", loadOnStartup=1, value="/init-servlet")
public class InitServlet extends HttpServlet {


    public void init() {

        try{
            Class.forName("fr.quentinfrancois.JavaEE_Projet.persistance.MediatekData");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void destroy() {
    }
/*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String name = request.getParameter("monParam");
        //response.getOutputStream().println("Hello " + name + "!");
        //renvoyer ybe jsp
        this.getServletContext().getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
    }*/
}