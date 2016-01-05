package com.abhinav.gappengine1;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 * Created by amathu4 on 12/22/15.
 */
public class DSFactory {

    volatile static DatastoreService dataStore;
    private static Object o = new Object();

    public static DatastoreService getInstance() {
        if(dataStore !=null) {
            return dataStore;
        } else {
            synchronized (o) {
                if(dataStore == null) {
                    dataStore = DatastoreServiceFactory.getDatastoreService();
                }
            }
        }
        return dataStore;
    }

}
