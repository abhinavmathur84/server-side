package com.abhinav.gappengine1;

import com.google.appengine.api.datastore.GeoPt;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by amathu4 on 12/22/15.
 */
@Entity
public class Location {

    /**
     * Place from where the entry is made
     */

    public GeoPt center;

    @Id
    public String someId;



}
