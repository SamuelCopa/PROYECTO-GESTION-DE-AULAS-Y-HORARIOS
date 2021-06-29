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
            <c:if test="${materia.id == 0}">Nuevo </c:if>
            <c:if test="${materia.id > 0}">Editar </c:if>
                materia   
            </h1>
            <form action="MateriaControlador" method="post">
                <input type="hidden" name="id" value="${materia.id}">
            <div class="datagrid">
                <table width="311">       
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Datos</th>
                        </tr>
                    </thead>
                    <tr>
                        <td>Materia</td>
                        <td ><input name="nombre" type="text" value="${materia.nombre}" required></td>                   
                    </tr>

                    <tr class="alt">
                        <td>Sigla</td>
                        <td ><input name="sigla" type="text" value="${materia.sigla}" required></td>                   
                    </tr> 

                    <tr>
                        <td>Nivel</td>

                        <td>   
                            <select name="nivel" class="btn">

                                <option value="1" <c:if test="${materia.nivel == '1'}">selected </c:if> >1er. Semestre</option>
                                <option value="2" <c:if test="${materia.nivel == '2'}">selected </c:if> >2do. Semestre</option>
                                <option value="3" <c:if test="${materia.nivel == '3'}">selected </c:if> >3er. Semestre</option>
                                <option value="4" <c:if test="${materia.nivel == '4'}">selected </c:if> >4to. Semestre</option>
                                <option value="5" <c:if test="${materia.nivel == '5'}">selected </c:if> >5to. Semestre</option>
                                <option value="6" <c:if test="${materia.nivel == '6'}">selected </c:if> >6to. Semestre</option>
                                <option value="7" <c:if test="${materia.nivel == '7'}">selected </c:if> >7mo. Semestre</option>
                                <option value="8" <c:if test="${materia.nivel == '8'}">selected </c:if> >8vo. Semestre</option>
                                <option value="9" <c:if test="${materia.nivel == '9'}">selected </c:if> >9no. Semestre</option>
                                <option value="10" <c:if test="${materia.nivel == '10'}">selected </c:if> >10mo. Semestre</option>

                            </select>
                     
                        </td>


                    </tr> 


                    <tr class="alt">
                        <td></td>
                        <td><input type="submit" value="enviar" class="btn"></td>
                    </tr>

                </table>
            </div>
        </form>
        <p><a href="MateriaControlador" class="btn">Volver</a></p>

<jsp:include page="WEB-INF/pie.jsp" />