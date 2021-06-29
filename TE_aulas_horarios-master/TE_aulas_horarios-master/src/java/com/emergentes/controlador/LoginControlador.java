package com.emergentes.controlador;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginControlador", urlPatterns = {"/LoginControlador"})
public class LoginControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {
            case "logout":
                if (request.getParameter("cerrar") != null) {
                HttpSession ses = request.getSession();
                ses.invalidate();
                response.sendRedirect("Login.jsp");
                }
                break;
            case "view":
                HttpSession sesion = request.getSession();
                sesion.setAttribute("fail", "vacio");
                response.sendRedirect("Login.jsp");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        System.out.println("email: " + usuario);
        System.out.println("password: " + password);

        Validate v = new Validate();

        if (v.checkUser(usuario, password)) {
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                List<Usuario> datos = new ArrayList<Usuario>();
                datos = dao.verifica_usuario2(usuario, password);

                int id2 = 0;
                String usuario2 = "";
                String password2 = "";
                int id_cat_usuario2 = 0;

                for (Usuario u : datos) {
                    id2 = u.getId();
                    usuario2 = u.getUsuario();
                    password2 = u.getPassword();
                    id_cat_usuario2 = u.getId_cat_usuario();
                }
                HttpSession ses = request.getSession();
                
                ses.setAttribute("usuario", usuario2);
                ses.setAttribute("id", id2);
                ses.setAttribute("id_cat_usuario", id_cat_usuario2);
                ses.setAttribute("login", "OK");
                response.sendRedirect("HorarioControlador");
            } catch (Exception ex) {
                Logger.getLogger(LoginControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            HttpSession ses = request.getSession();
            ses.setAttribute("fail", "error");
            response.sendRedirect("Login.jsp ");
        }

    }

}
