<%@page import="org.apache.commons.lang.exception.ExceptionUtils" %>
<%@page import="org.motechproject.util.DateUtil" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    boolean success = false;
    try {
        Method m = ClassLoader.class.getDeclaredMethod("loadLibrary", Class.class, String.class, Boolean.TYPE);
        m.setAccessible(true);
        m.invoke(null, java.lang.System.class, "jvmfaketime", false);
    } catch (Exception e) {
        System.out.println("couldn't load native library.");
    }
    System.startFakingTime();
    try {
        if (request.getMethod().equals("POST")) {
            String dateTime = request.getParameter("newDateTime");
            Date newDateTime = dateFormat.parse(dateTime);

            System.out.println("Current Time: " + dateFormat.format(new Date()));
            System.out.println("Request for Updated Time: " + dateFormat.format(newDateTime));

            System.moveTimeBy(newDateTime.getTime() - System.currentTimeMillis());

        }
    } catch (java.lang.Exception e) {
        out.println("Error: " + ExceptionUtils.getFullStackTrace(e));
        return;
    }

%>
<html>

<head>
    <title>FakeTime</title>
</head>

<body>
<span id="statusMessage" style="font-size: medium; font-weight: bold; color: blue;"></span>
<br/>
<br/>

<div class="container">
    <form action="" method="get">
        <p>
            Current Time : <span class="bold"> <%=DateUtil.now().toDate()%> </span>
            <input type="submit" value="Refresh"/>
        </p>
    </form>

    <form name="fakeTimeSubmit" method="post">
        <p>
            <label for="newDateTime">New Date Time</label>
            <input type="text" name="newDateTime" id="newDateTime" size="35" value="<%=dateFormat.format(new Date())%>"/>
            <input type="submit" value="Submit"/>
        </p>
    </form>
</div>
</body>
</html>
