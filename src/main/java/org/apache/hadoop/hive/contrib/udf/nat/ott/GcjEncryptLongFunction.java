package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class GcjEncryptLongFunction extends UDF {


    public double evaluate(double wgslat, double wgslong) {


        DeltaLongFunction deltaong = new DeltaLongFunction();

        double re;
        if (wgslong < 72.004 || wgslong > 137.8347) {
            re = 0;

        } else {
            re = wgslong + deltaong.evaluate(wgslat, wgslong);
        }
        return re;

    }


}
