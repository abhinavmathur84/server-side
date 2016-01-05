package com.abhinav.gappengine1;

import com.google.appengine.api.datastore.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by amathu4 on 12/22/15.
 */
public class GetCommentsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        DatastoreService dataStore = DSFactory.getInstance();

        double lat = Double.parseDouble(req.getParameter("lat"));
        double lon = Double.parseDouble(req.getParameter("long"));

        String latS = lat+"";
        latS = latS.substring(0,latS.length()-1); // remove the last decimal

        String lonS = lon+"";
        lonS = lonS.substring(0,lonS.length()-1); // remove the last decimal

        String key = latS+"_"+lonS;



        Query.Filter keyFilter =
                new Query.FilterPredicate("key",
                        Query.FilterOperator.EQUAL,
                        key);



        //Query.Filter longRangerFilter =Query.CompositeFilterOperator.and(longMinFilter, longMaxFilter);

      //  Query.Filter finalFilter = Query.CompositeFilterOperator.and(latRangeFilter,longRangerFilter);


       /* GeoPt center =  new GeoPt(37.7913156f,-122.3926051f);
        double radius = 0;
        Query.Filter f = new Query.StContainsFilter("location", new Query.GeoRegion.Circle(center, radius));
        Query q = new Query("Kind").setFilter(f);
*/



        // Use class Query to assemble a query
        Query q = new Query("Comment").setFilter(keyFilter);

        // Use PreparedQuery interface to retrieve results
        PreparedQuery pq = dataStore.prepare(q);


        String s ="";
        for (Entity result : pq.asIterable()) {
           s= s+result.getProperty("comment")+"\n";
        }
        resp.getOutputStream().print(s);

    }


}
