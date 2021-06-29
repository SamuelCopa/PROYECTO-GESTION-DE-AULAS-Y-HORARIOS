package com.emergentes.controlador;

import com.emergentes.dao.*;
import com.emergentes.modelo.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HorasControlador", urlPatterns = {"/HorasControlador"})
public class HorasControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HorasDAO dao = new HorasDAOimpl();
            int id;
            Horas obj = new Horas();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {

                case "add":
                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmhoras.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    obj = dao.getById(id);
                    System.out.println(obj);
                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmhoras.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/HorasControlador");
                    break;

                case "view":
                    List<Horas> lista = dao.getAll();
                    request.setAttribute("list_obj", lista);
                    request.getRequestDispatcher("Horas.jsp").forward(request, response);
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
        String rango = request.getParameter("rango");

        Horas obj = new Horas();

        obj.setId(id);
        obj.setRango(rango);

        if (id == 0) {
            try {
                HorasDAO dao = new HorasDAOimpl();
                dao.insert(obj);
                response.sendRedirect(request.getContextPath() + "/HorasControlador");

            } catch (Exception e) {
                System.out.println("Error al insertar " + e.getMessage());
            }
        } else {
            try {
                HorasDAO dao = new HorasDAOimpl();
                dao.update(obj);
                response.sendRedirect(request.getContextPath() + "/HorasControlador");

            } catch (Exception e) {
                System.out.println("Error actualizar" + e.getMessage());
            }
        }
    }

}
