<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>

</head>
<body>

<h2>Line Information</h2>
<form:form method="POST" action="/springProject/addLines" >
   <table>
    <tr>
        <td><form:label path="lines">Enter the comments :</form:label></td>
        <td><form:input path="lines" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>