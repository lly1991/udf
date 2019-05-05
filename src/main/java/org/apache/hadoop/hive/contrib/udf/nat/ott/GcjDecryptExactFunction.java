package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName BdDecryptLatFunction
 * @Description TODO
 * @Author lly
 * @Date 2018/12/249:44 AM
 * @Version 1.0
 **/
public class GcjDecryptExactFunction extends UDF {


    private double initDelta = 0.01;
    private double threshold = 0.000000001;


    public String evaluate(double gcjLat, double gcjLong) {


        double dLat;
        double dLong;
        double mLat;
        double pLat;
        double mLong;
        double pLong;

        int i = 0;

        double wgsLat;
        double wgsLong;


        dLat = initDelta;
        dLong = initDelta;

        mLat = gcjLat - dLat;
        mLong = gcjLong - dLong;
        pLat = gcjLat + dLat;
        pLong = gcjLong + dLong;


        GcjEncryptLatFunction gcjEncryptLatFunction = new GcjEncryptLatFunction();
        GcjEncryptLongFunction gcjEncryptLongFunction = new GcjEncryptLongFunction();


        while (true) {
            wgsLat = (mLat + pLat) / 2;
            wgsLong = (mLong + pLong) / 2;
            dLat = gcjEncryptLatFunction.evaluate(wgsLat, wgsLong) - gcjLat;
            dLong = gcjEncryptLongFunction.evaluate(wgsLat, wgsLong) - gcjLong;

            if ((Math.abs(dLat) < threshold) && (Math.abs(dLong) < threshold)) {
                break;
            }

            if (dLat > 0) {
                pLat = wgsLat;
            } else {
                mLat = wgsLat;
            }


            if (dLong > 0) {
                pLong = wgsLong;
            } else {
                mLong = wgsLong;
            }
            i += 1;

            if (i > 10000) {
                break;
            }
        }
        return wgsLong + ":" + wgsLat;

    }



}
