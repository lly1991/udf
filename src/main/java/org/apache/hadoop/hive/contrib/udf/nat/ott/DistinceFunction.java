package org.apache.hadoop.hive.contrib.udf.nat.ott;

import com.google.common.base.Optional;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class DistinceFunction extends UDF {


    private double a_2d = 6378137;
    private double e_2d = 0.00669438;
    private double h_2d = 15;
    private double deg2rad = 0.01745329252;


    public double evaluate(double lon1, double lat1, double lon2, double lat2) {


        double x_rads;
        double y_rads;

        double n_2ds;
        double x_2d;
        double y_2d;
        double z_2d;
        double x_radm;
        double y_radm;
        double n_2dm;
        double x_2d_mark;
        double y_2d_mark;
        double z_2d_mark;
        double curdistance;


        if (!Optional.fromNullable(lon1).isPresent() || !Optional.fromNullable(lat1).isPresent()
                || !Optional.fromNullable(lon2).isPresent() || !Optional.fromNullable(lon2).isPresent()) {

            return 0;
        }

        x_rads = Math.abs(lon1) * deg2rad;
        y_rads = Math.abs(lat1) * deg2rad;

        n_2ds = a_2d / Math.sqrt(1 - e_2d * Math.sin(y_rads) * Math.sin(y_rads));

        x_2d = (n_2ds + h_2d) * Math.cos(y_rads) * Math.cos(x_rads);
        y_2d = (n_2ds + h_2d) * Math.cos(y_rads) * Math.sin(x_rads);
        z_2d = (n_2ds * (1 - e_2d) + h_2d) * Math.sin(y_rads);

        x_radm = Math.abs(lon2) * deg2rad;
        y_radm = Math.abs(lat2) * deg2rad;

        n_2dm = a_2d / Math.sqrt(1 - e_2d * Math.sin(y_radm) * Math.sin(y_radm));

        x_2d_mark = (n_2dm + h_2d) * Math.cos(y_radm) * Math.cos(x_radm);
        y_2d_mark = (n_2dm + h_2d) * Math.cos(y_radm) * Math.sin(x_radm);
        z_2d_mark = (n_2dm * (1 - e_2d) + h_2d) * Math.sin(y_radm);

        curdistance = (x_2d_mark - x_2d) * (x_2d_mark - x_2d) +
                (y_2d_mark - y_2d) * (y_2d_mark - y_2d) +
                (z_2d_mark - z_2d) * (z_2d_mark - z_2d);
        curdistance = Math.sqrt(curdistance);

        return curdistance;


    }


}
