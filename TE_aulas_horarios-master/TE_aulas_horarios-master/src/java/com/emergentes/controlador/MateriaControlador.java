package com.emergentes.controlador;

import com.emergentes.modelo.Materia;
import com.emergentes.dao.MateriaDAO;
import com.emergentes.dao.MateriaDAOimpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MateriaControlador", urlPatterns = {"/MateriaControlador"})
public class MateriaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Materia mat = new Materia();
            int id;
            MateriaDAO dao = new MateriaDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("materia", mat);
                    request.getRequestDispatcher("frmmateria.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    mat = dao.getById(id);
                    request.setAttribute("materia", mat);
                    request.getRequestDispatcher("frmmateria.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("MateriaControlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<Materia> lista = dao.getAll();
                    request.setAttribute("materias", lista);
                    request.getRequestDispatcher("materia.jsp").forward(request, response);
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
        String sigla = request.getParameter("sigla");
        int nivel = Integer.parseInt(request.getParameter("nivel"));
       

        Materia mat = new Materia();

        mat.setId(id);
        mat.setNombre(nombre);
        mat.setSigla(sigla);
        mat.setNivel(nivel);
   

        if (id == 0) {
            MateriaDAO dao = new MateriaDAOimpl();
            try {
                // Nuevo registro

                dao.insert(mat);
                response.sendRedirect("MateriaControlador");
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            MateriaDAO dao = new MateriaDAOimpl();
            try {
                // Edicion de registro
                dao.update(mat);
                response.sendRedirect("MateriaControlador");
            } catch (Exception ex) {
                System.out.println("Error al editar " + ex.getMessage());
            }
        }

    }

}
