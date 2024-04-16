package com.wego.demo.common;

import org.geotools.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

public class CoordinateConverter {
    private MathTransform transform;
    private GeometryFactory geometryFactory = new GeometryFactory();

    public CoordinateConverter() {
        try {
            CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:32648"); // source CRS
            CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326"); // WGS84
            transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Coordinate transform(double x, double y) throws TransformException {
        double[] sourceCoords = new double[] {x, y};
        double[] destCoords = new double[2];

        // Transform the coordinates
        transform.transform(sourceCoords, 0, destCoords, 0, 1);

        // Return a new Coordinate object with the transformed coordinates
        return new Coordinate(destCoords[0], destCoords[1]);
    }
}
