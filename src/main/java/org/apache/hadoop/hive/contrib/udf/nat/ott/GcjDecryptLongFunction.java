package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class GcjDecryptLongFunction extends UDF {


    public double evaluate(double gcjlat, double gcjlong) {


        DeltaLongFunction deltaLongFunction = new DeltaLongFunction();


        double re;

        if (gcjlong < 72.004 || gcjlong > 137.8347) {
            re = 0;
        } else {
            re = gcjlong - deltaLongFunction.evaluate(gcjlat, gcjlong);
        }

        return re;


    }


}
