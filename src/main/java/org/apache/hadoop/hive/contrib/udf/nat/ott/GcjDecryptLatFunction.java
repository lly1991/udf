package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class GcjDecryptLatFunction extends UDF {




    public double evaluate(double gcjlat, double gcjlong) {


        DeltaLatFunction deltaLatFunction = new DeltaLatFunction();


        double re;

        if (gcjlat < 0.8293 || gcjlat > 55.8271) {
            re = 0;
        } else {
            re = gcjlat - deltaLatFunction.evaluate(gcjlat, gcjlong);
        }

        return re;


    }


}
