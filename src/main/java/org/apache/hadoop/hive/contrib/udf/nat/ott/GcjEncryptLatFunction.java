package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class GcjEncryptLatFunction extends UDF {


    public double evaluate(double wgslat, double wgslong) {


        DeltaLatFunction deltaLatFunction = new DeltaLatFunction();

        double re;
        if (wgslat < 0.8293 || wgslat > 55.8271) {
            re = 0;

        } else {
            re = wgslat + deltaLatFunction.evaluate(wgslat, wgslong);
        }
        return re;

    }


}
