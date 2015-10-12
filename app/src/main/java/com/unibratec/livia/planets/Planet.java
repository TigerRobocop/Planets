package com.unibratec.livia.planets;
import android.content.res.Resources;

import java.io.Serializable;
/**
 * Created by Livia on 10/10/2015.
 */
public class Planet implements Serializable {

    public int name;
    public int discovery;
    public int namedFor;
    public double diameter;
    public double orbit;
    public double day;

    public Planet(int name, int discovery, int namedFor
            , double diameter, double orbit, double day) {
        this.name = name;
        this.discovery = discovery;
        this.namedFor = namedFor;
        this.diameter = diameter;
        this.orbit = orbit;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
