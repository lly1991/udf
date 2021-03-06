package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class BdDecryptLongFunction extends UDF {

    private double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    public double evaluate(double bdLat, double bdLong) {
        double x;
        double y;
        double z;
        double theta;
        double gcjlong;

        x = bdLong - 0.0065;
        y = bdLat - 0.006;
        z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        gcjlong = z * Math.cos(theta);
        return gcjlong;
    }
}
