package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class DeltaLongFunction extends UDF {


    private double a = 6378245.0;
    private double ee = 0.00669342162296594323;
    private double pi = 3.14159265358979324;

    public double evaluate(double lat, double lon) {

        double dLong;
        double deltaLong;
        double radLat;
        double magic;
        double sqrtMagic;
        double x;
        double y;

        x = lon - 105.0;
        y = lat - 35.0;


        dLong = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x))
                + (20 * Math.sin(6 * x * pi) + 20 * Math.sin(2 * x * pi)) * 2 / 3
                + (20 * Math.sin(x * pi) + 40 * Math.sin(x / 3 * pi)) * 2 / 3
                + (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;

        radLat = lat / 180.0 * pi;
        magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        sqrtMagic = Math.sqrt(magic);
        deltaLong = (dLong * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);

        return deltaLong;
    }




}
