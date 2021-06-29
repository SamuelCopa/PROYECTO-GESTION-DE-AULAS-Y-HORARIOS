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
        Horario    
    </h1>
    <form action="HorarioControlador" method="post">
        <input type="hidden" name="id" value="${objeto.id}">
    <div class="datagrid">
        <table width="311">       
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Campo</th>
                </tr>
            </thead>

            <tr class="alt">
                <td>Gestion</td>
                <td>   
                    <select name="gestion" class="btn">

                        <option value="I-2021" <c:if test="${objeto.gestion == 'I-2021'}">selected </c:if> >I-2021</option>
                        <option value="II-2021" <c:if test="${objeto.gestion == 'II-2021'}">selected </c:if> >II-2021</option>
                        <option value="I-2022" <c:if test="${objeto.gestion == 'I-2022'}">selected </c:if> >I-2022</option>
                        <option value="II-2022" <c:if test="${objeto.gestion == 'II-2022'}">selected </c:if> >II-2022</option>
                        </select>
                    </td>
                </tr>

                <tr >
                    <td>Turno</td>
                    <td>   
                        <select name="turno" class="btn">

                            <option value="Manana" <c:if test="${objeto.turno == 'Manana'}">selected </c:if> >Mañana</option>
                        <option value="Tarde" <c:if test="${objeto.turno == 'Tarde'}">selected </c:if> >Tarde</option>
                        <option value="Noche" <c:if test="${objeto.turno == 'Noche'}">selected </c:if> >Noche</option>
                        </select>
                    </td>

                </tr>
                <tr class="alt">
                    <td>Dia</td>
                    <td>   
                        <select name="dia" class="btn">

                            <option value="Lunes" <c:if test="${objeto.dia == 'Lunes'}">selected </c:if> >Lunes</option>
                        <option value="Martes" <c:if test="${objeto.dia == 'Martes'}">selected </c:if> >Martes</option>
                        <option value="Miercoles" <c:if test="${objeto.dia == 'Miercoles'}">selected </c:if> >Miercoles</option>
                        <option value="Jueves" <c:if test="${objeto.dia == 'Jueves'}">selected </c:if> >Jueves</option>
                        <option value="Viernes" <c:if test="${objeto.dia == 'Viernes'}">selected </c:if> >Viernes</option>
                        <option value="Sabado" <c:if test="${objeto.dia == 'Sabado'}">selected </c:if> >Sabado</option>
                        </select>
                    </td>

                </tr>

                <tr >
                    <td>Horas</td>

                    <td >                    
                        <select name="id_horas" class="btn">
                        <c:forEach var="item" items="${lista_horas}"> 
                            <option value= ${item.id}    
                                    <c:if test="${objeto.id_horas == item.id}">
                                        selected
                                    </c:if>
                                    >${item.rango}</option>
                        </c:forEach>
                    </select>        

                </td> 

            </tr> 

            <tr class="alt">
                <td>Aula</td>


                <td >                    
                    <select name="id_aula" class="btn">
                        <c:forEach var="item" items="${lista_aulas}"> 
                            <option value= ${item.id}    
                                    <c:if test="${objeto.id_aula == item.id}">
                                        selected
                                    </c:if>
                                    >${item.descripcion}</option>
                        </c:forEach>
                    </select>        

                </td> 
            </tr>



            <tr >
                <td>Materia y Paralelo</td>
                <td >                    
                    <select name="id_mat_par" class="btn">
                        <c:forEach var="item" items="${lista_matpar}"> 
                            <option value= ${item.id}    
                                    <c:if test="${objeto.id_mat_par == item.id}">
                                        selected
                                    </c:if>
                                    >${item.mat_par}</option>
                        </c:forEach>
                    </select>        

                </td>                   
            </tr>

            <tr class="alt">
                <td>Docente</td>
                <td >                    
                    <select name="id_docente" class="btn">
                        <c:forEach var="item" items="${lista_docentes}"> 
                            <option value= ${item.id}    
                                    <c:if test="${objeto.id_docente == item.id}">
                                        selected
                                    </c:if>
                                    >${item.apellidos}${" "}${item.nombre}</option>
                        </c:forEach>
                    </select>        

                </td>                   
            </tr>                     

            <tr >
                <td></td>
                <td><input type="submit" value="enviar" class="btn"></td>
            </tr>

        </table>
    </div>
</form>
<p><a href="HorarioControlador" class="btn">Volver</a></p>

<jsp:include page="WEB-INF/pie.jsp" />
