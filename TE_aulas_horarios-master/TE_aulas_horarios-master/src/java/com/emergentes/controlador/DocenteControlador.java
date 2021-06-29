package com.emergentes.controlador;

import com.emergentes.modelo.Docente;
import com.emergentes.dao.DocenteDAO;
import com.emergentes.dao.DocenteDAOimpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DocenteControlador", urlPatterns = {"/DocenteControlador"})
public class DocenteControlador extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Docente doc = new Docente();
            int id;
            DocenteDAO dao = new DocenteDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch (action) {
                case "add":
                    request.setAttribute("docente", doc);
                    request.getRequestDispatcher("frmdocente.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    doc = dao.getById(id);
                    request.setAttribute("docente", doc);
                    request.getRequestDispatcher("frmdocente.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("DocenteControlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<Docente> lista = dao.getAll();
                    request.setAttribute("docentes", lista);
                    request.getRequestDispatcher("docente.jsp").forward(request, response);
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
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String grado_estudio = request.getParameter("grado_estudio");
       
        
        Docente doc = new Docente();
        
        doc.setId(id);
        doc.setNombre(nombre);
        doc.setApellidos(apellidos);
        doc.setGrado_estudio(grado_estudio);
        
        if (id == 0) {
            DocenteDAO dao = new DocenteDAOimpl();
            try {
                // Nuevo registro

                dao.insert(doc);
                response.sendRedirect("DocenteControlador");
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            DocenteDAO dao = new DocenteDAOimpl();
            try {
                // Edicion de registro
                dao.update(doc);
                response.sendRedirect("DocenteControlador");
            } catch (Exception ex) {
                System.out.println("Error al editar " + ex.getMessage());
            }
        }
    }    
}
