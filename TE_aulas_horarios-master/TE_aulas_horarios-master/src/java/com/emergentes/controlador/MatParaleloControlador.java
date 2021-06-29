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

/**
 *
 * @author Sammy
 */
@WebServlet(name = "MatParaleloControlador", urlPatterns = {"/MatParaleloControlador"})
public class MatParaleloControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MatParaleloDAO dao = new MatParaleloDAOimpl();
            MateriaDAO daoMat = new MateriaDAOimpl();
            ParaleloDAO daoPar = new ParaleloDAOimpl();
            
            MatParalelo mp = new MatParalelo();
            int id;

            List<Materia> lista_materia = null;
            List<Paralelo> lista_paralelo = null;
            

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    lista_materia = daoMat.getAll();
                    lista_paralelo = daoPar.getAll();
        
                    //Enviar lista
                    request.setAttribute("lista_materia", lista_materia);
                    request.setAttribute("lista_paralelo", lista_paralelo);
                    
                    request.setAttribute("matparalelo", mp);
                    request.getRequestDispatcher("frmmatparalelo.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    mp = dao.getById(id);

                    lista_materia = daoMat.getAll();
                    lista_paralelo = daoPar.getAll();
                    

                    request.setAttribute("lista_materia", lista_materia);
                    request.setAttribute("lista_paralelo", lista_paralelo);
                    

                    request.setAttribute("matparalelo", mp);
                    request.getRequestDispatcher("frmmatparalelo.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("MatParaleloControlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<MatParalelo> lista = dao.getAll();
                    
                     lista_materia = daoMat.getAll();
                    lista_paralelo = daoPar.getAll();
                    
                    //Enviar lista
                    request.setAttribute("lista_materia", lista_materia);
                    request.setAttribute("lista_paralelo", lista_paralelo);
                    
                    request.setAttribute("matparalelos", lista);
                    request.getRequestDispatcher("matparalelo.jsp").forward(request, response);
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
        int id_materia = Integer.parseInt(request.getParameter("id_materia"));
        int id_paralelo = Integer.parseInt(request.getParameter("id_paralelo"));
        

        MatParalelo mp = new MatParalelo();

       mp.setId(id);
       mp.setId_materia(id_materia);
       mp.setId_paralelo(id_paralelo);


        if (id == 0) {
            MatParaleloDAO dao = new MatParaleloDAOimpl();
            try {
                // Nuevo registro

                dao.insert(mp);
                response.sendRedirect("MatParaleloControlador");
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            MatParaleloDAO dao = new MatParaleloDAOimpl();
            try {
                // Edicion de registro
                dao.update(mp);
                response.sendRedirect("MatParaleloControlador");
            } catch (Exception ex) {
                System.out.println("Error al editar " + ex.getMessage());
            }
        }
    }
}
