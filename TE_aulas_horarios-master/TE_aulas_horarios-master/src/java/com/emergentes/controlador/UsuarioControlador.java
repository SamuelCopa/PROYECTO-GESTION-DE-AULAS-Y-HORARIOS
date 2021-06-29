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
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            CatUsuarioDAO dao_catus = new CatUsuarioDAOimpl();
            UsuarioDAO dao = new UsuarioDAOimpl();
            List<CatUsuario> lista_catus;

            int id;
            Usuario obj = new Usuario();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    lista_catus = dao_catus.getAll();
                    request.setAttribute("catus", lista_catus);

                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    lista_catus = dao_catus.getAll();
                    request.setAttribute("catus", lista_catus);

                    id = Integer.parseInt(request.getParameter("id"));
                    obj = dao.getById(id);
                    System.out.println(obj);
                    request.setAttribute("objeto", obj);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/UsuarioControlador");
                    break;
                case "view":
                    lista_catus = dao_catus.getAll();
                    request.setAttribute("catus", lista_catus);

                    List<Usuario> lista = dao.getAll();
                    request.setAttribute("lista_obj", lista);
                    request.getRequestDispatcher("Usuario.jsp").forward(request, response);
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
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        int id_cat_usuario = Integer.parseInt(request.getParameter("id_cat_usuario"));

        Usuario obj = new Usuario();

        obj.setId(id);
        obj.setUsuario(usuario);
        obj.setPassword(password);
        obj.setId_cat_usuario(id_cat_usuario);

        if (id == 0) {
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.insert(obj);
                response.sendRedirect(request.getContextPath() + "/UsuarioControlador");

            } catch (Exception e) {
                System.out.println("Error insertar " + e.getMessage());
            }
        } else {
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.update(obj);
                response.sendRedirect(request.getContextPath() + "/UsuarioControlador");

            } catch (Exception e) {
                System.out.println("Error actualizar " + e.getMessage());
            }
        }

    }

}
