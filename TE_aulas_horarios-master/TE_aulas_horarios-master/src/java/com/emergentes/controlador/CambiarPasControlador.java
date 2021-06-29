package com.emergentes.controlador;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdk.nashorn.internal.runtime.Debug.id;

@WebServlet(name = "CambiarPasControlador", urlPatterns = {"/CambiarPasControlador"})
public class CambiarPasControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String pas1 = request.getParameter("pas1");
        String pas2 = request.getParameter("pas2");
        if (pas1.equals(pas2)) {
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.update_pas(id, pas2);
                response.sendRedirect(request.getContextPath() + "/CambiarPas.jsp");

            } catch (Exception e) {
                System.out.println("Error cambiar Password" + e.getMessage());
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/CambiarPas.jsp");
        }

    }

}
