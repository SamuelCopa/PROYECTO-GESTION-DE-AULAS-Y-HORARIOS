package com.emergentes.controlador;

import com.emergentes.dao.ParaleloDAO;
import com.emergentes.dao.ParaleloDAOimpl;
import com.emergentes.modelo.Paralelo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sammy
 */
@WebServlet(name = "ParaleloControlador", urlPatterns = {"/ParaleloControlador"})
public class ParaleloControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            Paralelo pa = new Paralelo();
            int id;
            ParaleloDAO dao = new ParaleloDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch (action) {
                case "add":
                    request.setAttribute("paralelo", pa);
                    request.getRequestDispatcher("frmparalelo.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    pa = dao.getById(id);
                    request.setAttribute("paralelo", pa);
                    request.getRequestDispatcher("frmparalelo.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("ParaleloControlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<Paralelo> lista = dao.getAll();
                    request.setAttribute("paralelos", lista);
                    request.getRequestDispatcher("paralelo.jsp").forward(request, response);
                    break;
            }
            
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre_paralelo = request.getParameter("nombre_paralelo");

        Paralelo pa = new Paralelo();
        
        pa.setId(id);
        pa.setNombre_paralelo(nombre_paralelo);
        
        if (id == 0) {
            ParaleloDAO dao = new ParaleloDAOimpl();
            try {
                // Nuevo registro

                dao.insert(pa);
                response.sendRedirect("ParaleloControlador");
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            ParaleloDAO dao = new ParaleloDAOimpl();
            try {
                // Edicion de registro
                dao.update(pa);
                response.sendRedirect("ParaleloControlador");
            } catch (Exception ex) {
                System.out.println("Error al editar " + ex.getMessage());
            }
        }
    }
}
