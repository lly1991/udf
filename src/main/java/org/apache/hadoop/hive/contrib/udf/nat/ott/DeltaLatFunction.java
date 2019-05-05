package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class DeltaLatFunction extends UDF {


    private double a = 6378245.0;
    private double ee = 0.00669342162296594323;
    private double pi = 3.14159265358979324;

    public double evaluate(double lat, double lon) {

        double dLat;
        double deltaLat;
        double radLat;
        double magic;
        double sqrtMagic;
        double x;
        double y;

        x = lon - 105.0;
        y = lat - 35.0;


        dLat = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x))
                + (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0
                + (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0
                + (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;

        radLat = lat / 180.0 * pi;
        magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        sqrtMagic = Math.sqrt(magic);
        deltaLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        return deltaLat;
    }




}
