<%@page import="com.emergentes.modelo.*"%>
<%@page import="com.emergentes.dao.*"%>
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
            <c:if test="${objeto.id == 0}">Nuevo </c:if>
            <c:if test="${objeto.id > 0}">Editar </c:if>
                Aula   
            </h1>
            <form action="AulaControlador" method="post">
                <input type="hidden" name="id" value="${objeto.id}">
            <div class="datagrid">
                <table width="311">       
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Campo</th>
                        </tr>
                    </thead>
                    <tr >
                        <td>Descripcion</td>
                        <td ><input  name="descripcion" type="text" value="${objeto.descripcion}" required></td>                   
                    </tr>

                    <tr class="alt">
                        <td>Ubicacion</td>
                        <td ><input  name="ubicacion" type="text" value="${objeto.ubicacion}" required></td>                   
                    </tr>

                    <tr >
                        <td></td>
                        <td><input type="submit" value="enviar" class="btn"></td>
                    </tr>

                </table>
            </div>
        </form>
        <p><a href="AulaControlador" class="btn">Volver</a></p>
        
<jsp:include page="WEB-INF/pie.jsp" />
