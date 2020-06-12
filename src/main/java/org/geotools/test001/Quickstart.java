package org.geotools.test001;

/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2019, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */



import java.io.File;
import java.io.FileInputStream;

import org.geotools.data.FileDataStore;

import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

import org.geotools.data.FeatureSource; 
import org.geotools.data.store.ContentFeatureCollection; 
import org.geotools.feature.FeatureCollection; 
import org.geotools.kml.KMLConfiguration; 
import org.geotools.xml.Parser; 
import org.opengis.feature.simple.SimpleFeature; 
import org.opengis.feature.simple.SimpleFeatureType; 
import org.xml.sax.SAXException; 
 
import javax.xml.parsers.ParserConfigurationException; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.util.Collection; 
import java.util.List; 

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a map frame.
 *
 * <p>This is the GeoTools Quickstart application used in documentationa and tutorials. *
 */
public class Quickstart {

    /**
     * GeoTools Quickstart demo application. Prompts the user for a shapefile and displays its
     * contents on the screen in a map frame
     */
    public static void main(String[] args) throws Exception {
    	FileInputStream reader = new FileInputStream("file.kml");
        PullParser parser = new PullParser(new KMLConfiguration(), reader,
                SimpleFeature.class);

        FeatureJSON fjson = new FeatureJSON();
        FileWriter tmp = new FileWriter("file.geojson");
        BufferedWriter writer = new BufferedWriter(tmp);
        ArrayList<SimpleFeature> features = new ArrayList<>();
        SimpleFeature simpleFeature = (SimpleFeature) parser.parse();
        while (simpleFeature != null) {
            features.add(simpleFeature);
            simpleFeature = (SimpleFeature) parser.parse();
        }
        SimpleFeatureCollection fc = DataUtilities.collection(features);
        fjson.writeFeatureCollection(fc, writer);
        writer.close();
    
        /* display a data store file chooser dialog for shapefiles
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }

        FileDataStore store = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource featureSource = store.getFeatureSource();

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");

        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);

        // Now display the map
        JMapFrame.showMap(map);*/
    }
}
