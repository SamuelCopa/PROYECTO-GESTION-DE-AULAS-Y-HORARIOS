package com.emergentes.controlador;

import com.emergentes.dao.AulaDAO;
import com.emergentes.dao.AulaDAOimpl;
import com.emergentes.modelo.Aula;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AulaControlador", urlPatterns = {"/AulaControlador"})
public class AulaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            AulaDAO dao = new AulaDAOimpl();
            int id;
            Aula obj = new Aula();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {

                case "add":
                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmaula.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    obj = dao.getById(id);
                    System.out.println(obj);
                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmaula.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/AulaControlador");
                    break;

                case "view":
                    List<Aula> lista = dao.getAll();
                    request.setAttribute("list_obj", lista);
                    request.getRequestDispatcher("Aula.jsp").forward(request, response);
                    break;

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        String ubicacion = request.getParameter("ubicacion");

        Aula obj = new Aula();

        obj.setId(id);
        obj.setDescripcion(descripcion);
        obj.setUbicacion(ubicacion);

        if (id == 0) {
            try {
                AulaDAO dao = new AulaDAOimpl();
                dao.insert(obj);
                response.sendRedirect(request.getContextPath() + "/AulaControlador");

            } catch (Exception e) {
                System.out.println("Error al insertar " + e.getMessage());
            }
        } else {
            try {
                AulaDAO dao = new AulaDAOimpl();
                dao.update(obj);
                response.sendRedirect(request.getContextPath() + "/AulaControlador");

            } catch (Exception e) {
                System.out.println("Error actualizar" + e.getMessage());
            }
        }
    }

}
