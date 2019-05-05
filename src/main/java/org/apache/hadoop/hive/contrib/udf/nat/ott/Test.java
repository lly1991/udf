package org.apache.hadoop.hive.contrib.udf.nat.ott;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName Test
 * @Description TODO
 * @Author lly
 * @Date 2018/12/206:18 PM
 * @Version 1.0
 **/
public class Test extends UDF {

    public String evaluate(String word) {
        return word;
    }

}
