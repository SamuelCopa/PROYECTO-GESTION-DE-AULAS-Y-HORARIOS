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

            <c:if test="${matparalelo.id == 0}">Nuevo </c:if>
            <c:if test="${matparalelo.id > 0}">Editar </c:if>

            </h1>
            <form action="MatParaleloControlador" method="post">
                <input type="hidden" name="id" value="${matparalelo.id}">
            <div class="datagrid">
                <table width="311">       
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Datos</th>
                        </tr>
                    </thead>
                    <tr class="alt">
                        <td>Materia</td>
                        <td >                    
                            <select name="id_materia" class="btn">
                                <c:forEach var="item" items="${lista_materia}"> 
                                    <option value= ${item.id}    
                                            <c:if test="${matparalelo.id_materia == item.id}">
                                                selected
                                            </c:if>
                                            >${"Nivel "}${item.nivel}${" - "}${item.nombre}${" ("}${item.sigla}${")"}</option>
                                </c:forEach>
                            </select>        


                        </td>                  
                    </tr>
                    <tr>
                        <td>Paralelo </td>
                        <td >                    
                            <select name="id_paralelo" class="btn">
                                <c:forEach var="item" items="${lista_paralelo}"> 
                                    <option value= ${item.id}    
                                            <c:if test="${matparalelo.id_paralelo == item.id}">
                                                selected
                                            </c:if>
                                            >${item.nombre_paralelo}</option>
                                </c:forEach>
                            </select>       
                        </td>                  
                    </tr>


                    <tr>
                        <td></td>
                        <td><input type="submit" value="enviar" class="btn"></td>
                    </tr>

                </table>
            </div>
        </form>
        <p><a href="MatParaleloControlador" class="btn">Volver</a></p>
        
<jsp:include page="WEB-INF/pie.jsp" />
