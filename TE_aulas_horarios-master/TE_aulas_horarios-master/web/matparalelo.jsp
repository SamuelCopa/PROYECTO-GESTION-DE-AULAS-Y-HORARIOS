<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.*"%>
<%@page import="com.emergentes.dao.*"%>
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

        <h1>LISTADO DE MATERIA Y PARALELOS</h1>
        <p><a href="MatParaleloControlador?action=add" class="btn"><img src="Imagenes/nuevo.png" title="Nuevo registro"/>Nuevo</a></p>
        <div class="datagrid">
            <table border="2">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Mat_Par</th>
                        <th>Materia</th>
                        <th>Paralelo</th>                                       
                        <th colspan="2"><center>Opcion</center></th>
                    </tr>
                </thead>
                <c:forEach var="item" items="${matparalelos}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.mat_par}</td>
                        <td>

                            <c:forEach var="item2" items="${lista_materia}">
                                <c:if test="${item.id_materia == item2.id}">
                                    ${item2.nombre}  
                                </c:if>
                            </c:forEach>
                        </td>                     

                        <td>

                            <c:forEach var="item2" items="${lista_paralelo}">
                                <c:if test="${item.id_paralelo == item2.id}">
                                    ${item2.nombre_paralelo}  
                                </c:if>
                            </c:forEach>
                        </td>

                        
                        <th><a href="MatParaleloControlador?action=edit&id=${item.id}"><img src="Imagenes/editar.png" title="Modificar"/></a></th>
                        <td><a href="MatParaleloControlador?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar??'))">
                                <img src="Imagenes/delete.png" title="Eliminar"/></a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        
<jsp:include page="WEB-INF/pie.jsp" />