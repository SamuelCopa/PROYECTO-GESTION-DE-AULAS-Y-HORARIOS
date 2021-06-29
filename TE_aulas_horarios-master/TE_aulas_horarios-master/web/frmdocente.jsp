<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    HttpSession ses = request.getSession();
    String usuario = "";
    String id = "";
    int id_cat_usuario = 0;

    if (ses.getAttribute("usuario") != null && ses != null && ses.getAttribute("id") != null) {
        usuario = ses.getAttribute("usuario").toString();
        id = ses.getAttribute("id").toString();
        id_cat_usuario = Integer.parseInt(ses.getAttribute("id_cat_usuario").toString());

        /*
    if(!usuario.equals("admin")){
        response.sendRedirect("login.jsp");
    } 
         */
    } else {
        response.sendRedirect("Login.jsp");
    }


%>
<jsp:include page="WEB-INF/cabezera.jsp" />

        <h1>

            <c:if test="${docente.id == 0}">Nuevo </c:if>
            <c:if test="${docente.id > 0}">Editar </c:if>
                docente  
            </h1>
            <form action="DocenteControlador" method="post">
                <input type="hidden" name="id" value="${docente.id}">
            <div class="datagrid">
                <table width="311">       
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Datos</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>Grado de estudio</td>

                        <td>   
                            <select name="grado_estudio" class="btn">

                                <option value="Ing." <c:if test="${docente.grado_estudio == 'Ing.'}">selected </c:if> >Ing.</option>
                                <option value="Lic." <c:if test="${docente.grado_estudio == 'Lic.'}">selected </c:if> >Lic.</option>
                                <option value="Mg.Sc." <c:if test="${docente.grado_estudio == 'Mg.Sc.'}">selected </c:if> >Mg.Sc.</option>
                                <option value="Dr." <c:if test="${docente.grado_estudio == 'Dr.'}">selected </c:if> >Dr.</option>
                                </select>
                            </td>

                        </tr> 

                        <tr class="alt">
                            <td>Apellidos</td>
                            <td ><input name="apellidos" type="text" value="${docente.apellidos}" required></td>                   
                    </tr> 

                    <tr>
                        <td>Nombre </td>
                        <td ><input name="nombre" type="text" value="${docente.nombre}" required></td>                   
                    </tr>




                    <tr class="alt">
                        <td></td>
                        <td><input type="submit" value="enviar" class="btn"></td>
                    </tr>

                </table>
            </div>
        </form>
        <p><a href="DocenteControlador" class="btn">Volver</a></p>
        
<jsp:include page="WEB-INF/pie.jsp" />