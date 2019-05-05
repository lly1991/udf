package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class GetDstncFunction extends UDF {


    private double r = 6378137;
    private double pi = Math.acos(-1);

    public double evaluate(double lon1, double lat1, double lon2, double lat2) {

        double result;
        try {
            result = r * Math.acos(Math.cos(lat1) * Math.cos(lat2) * Math.cos((lon1 - lon2)) +
                    Math.sin(lat1) * Math.sin(lat2)) / 180 * pi;
        } catch (Exception e) {
            result = 0;
        }
        return result;

    }


}
