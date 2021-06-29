
package com.emergentes.controlador;

import com.emergentes.dao.CatUsuarioDAO;
import com.emergentes.dao.CatUsuarioDAOimpl;
import com.emergentes.modelo.CatUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControladorCatUsuario", urlPatterns = {"/ControladorCatUsuario"})
public class ControladorCatUsuario extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               try {
              
            CatUsuarioDAO dao = new CatUsuarioDAOimpl();
            int id;
            CatUsuario obj = new CatUsuario();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                
                case "add":
                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmcatusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    obj = dao.getById(id);
                    System.out.println(obj);
                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmcatusuario.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/ControladorCatUsuario");
                    break;
                
                case "view":
                    List<CatUsuario> lista = dao.getAll();
                    request.setAttribute("list_obj", lista);
                    request.getRequestDispatcher("CatUsuario.jsp").forward(request, response);
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

        CatUsuario obj = new CatUsuario();

        obj.setId(id);
        obj.setDescripcion(descripcion);


        if (id == 0) {
            try {
                CatUsuarioDAO dao = new CatUsuarioDAOimpl();
                dao.insert(obj);
                response.sendRedirect(request.getContextPath() + "/ControladorCatUsuario");

            } catch (Exception e) {
                System.out.println("Error al insertar " + e.getMessage());
            }
        } else {
            try {
                CatUsuarioDAO dao = new CatUsuarioDAOimpl();
                dao.update(obj);
                response.sendRedirect(request.getContextPath() + "/ControladorCatUsuario");

            } catch (Exception e) {
                System.out.println("Error actualizar" + e.getMessage());
            }
        }
        
       
    }



}
