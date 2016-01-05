package com.abhinav.gappengine1;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by amathu4 on 12/22/15.
 */
public class AddCommentServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        DatastoreService dataStore = DSFactory.getInstance();

        Entity comment = new Entity("Comment");


        double lat = Double.parseDouble(req.getParameter("lat"));
        double lon = Double.parseDouble(req.getParameter("long"));

        String latS = lat+"";
        latS = latS.substring(0,latS.length()-1); // remove the last decimal

        String lonS = lon+"";
        lonS = lonS.substring(0,lonS.length()-1); // remove the last decimal

        String key = latS+"_"+lonS;

        String content = req.getParameter("comment");
        comment.setProperty("key",key);
        comment.setProperty("lat",lat);
        comment.setProperty("long",lon);
        comment.setProperty("comment",content);

        dataStore.put(comment);

        resp.getOutputStream().print("OK\n"+key);

    }



}
