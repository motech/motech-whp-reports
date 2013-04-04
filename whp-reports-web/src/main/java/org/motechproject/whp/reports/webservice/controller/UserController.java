package org.motechproject.whp.reports.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Controller
public class UserController {

    @RequestMapping("/login")
    public void login(HttpServletRequest request) throws IOException {
        setVersion(request.getSession());
    }

    private void setVersion(HttpSession session) throws IOException {
        if (session.getAttribute("version") != null) return;
        String version = "0";
        InputStream stream = session.getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF");
        if (stream != null) {
            Properties prop = new Properties();
            prop.load(stream);
            String versionProperty = prop.getProperty("Build-Number");
            version = versionProperty == null ? "0" : versionProperty;
        }
        session.setAttribute("version", version);
    }
}

