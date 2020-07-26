package com.am.profile.platform.comm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Utils {
    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static Date getStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date result = formatter.parse(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            LOG.error("string date 解析出错", e);
        }
        return null;
    }

    //产生标签id
    public static String getLabelId(String labelOwnAppNum, String labelVersion, String labelLevel,
                                    Long maxLabelId) {
      /*

        产品编号+标签层级+版本编号（第3层才有版本号，每一层自动编码）

        产品编号    层级1  层级2   层级3  层级4  层级5   版本    标签id
        1            01                                   1
        1            01                                   2
        1            01                                   99
        1            01                                   111
        1            01                                   121
        1            01     02                            1
        1            01     02     03             01      1        //03级开始有版本
        1            01     02     03             01      1
        1            01     02     03             02      1
        1            01     02     03    04       01      1
        1            01     02     03    04       01      10001
        1            01     02     03    04  05   01      1
        1            01     02     03    04  05   01      10001



        产品编号    层级1  层级2   层级3  层级4     版本    标签id
        1            01                                   1
        1            01                                   2
        1            01                                   99
        1            01                                   111
        1            01                                   121
        1            01     02                            1
        1            01     02     03             1       1
        1            01     02     03             1       1    101020311
        1            01     02     03             1       1
        1            01     02     03             2       1
        1            01     02     03    04       1       1
        1            01     02     03    04       1       1    1010203041
        1            01     02     03    04       1       10001

       */
        LabelLevel level = LabelLevel.getLabelLevel(labelLevel);

        if (null == maxLabelId) {
            return getLabelIdByNull(labelOwnAppNum, labelVersion, level);
        }

        if (StringUtils.isBlank(labelOwnAppNum)) {
            labelOwnAppNum= String.valueOf(AppCode.VSKIT.getCode());
        }

        String strLabelId = Long.toString(maxLabelId);
        String preLabelId = null;
        String subLabelId ;
        AtomicLong id = null;
        switch (level){
            case Level_1:
                preLabelId = strLabelId.substring(labelOwnAppNum.length() -1, (labelOwnAppNum.length() -1)+2);
                subLabelId = strLabelId.substring(preLabelId.length()-1, strLabelId.length());
                id = new AtomicLong(Long.parseLong(subLabelId));
                break;
            case Level_2:
                preLabelId = strLabelId.substring(labelOwnAppNum.length() -1, (labelOwnAppNum.length() -1)+4);
                subLabelId = strLabelId.substring(preLabelId.length()-1, strLabelId.length());
                id = new AtomicLong(Long.parseLong(subLabelId));
                break;
            case Level_3:
                preLabelId = strLabelId.substring(labelOwnAppNum.length() -1, (labelOwnAppNum.length() -1)+7);
                subLabelId = strLabelId.substring(preLabelId.length(), strLabelId.length());
                id = new AtomicLong(Long.parseLong(subLabelId));
                break;
            case Level_4:
                preLabelId = strLabelId.substring(labelOwnAppNum.length() -1, (labelOwnAppNum.length() -1)+9);
                subLabelId = strLabelId.substring(preLabelId.length(), strLabelId.length());
                id = new AtomicLong(Long.parseLong(subLabelId));
                break;
            case Level_5:
                preLabelId = strLabelId.substring(labelOwnAppNum.length() -1, (labelOwnAppNum.length() -1)+11);
                subLabelId = strLabelId.substring(preLabelId.length(), strLabelId.length());
                id = new AtomicLong(Long.parseLong(subLabelId));
                break;
        }

        return preLabelId + id.incrementAndGet() ;
    }

    public static String getLabelIdByNull(String labelOwnAppNum, String labelVersion,
                                          LabelLevel level) {
        String labelId = (labelOwnAppNum != null ? labelOwnAppNum : String.valueOf(AppCode.VSKIT.getCode()));
        switch (level){
            case Level_1:
                labelId = labelId + "01";
                break;
            case Level_2:
                labelId = labelId + "0102";
                break;
            case Level_3:
                labelVersion = labelVersion == null ? "1" : labelVersion;
                labelId = labelId + "010203" + labelVersion;
                break;
            case Level_4:
                labelVersion = labelVersion == null ? "1" : labelVersion;
                labelId = labelId + "01020304" + labelVersion;
                break;
            case Level_5:
                labelVersion = labelVersion == null ? "1" : labelVersion;
                labelId = labelId + "0102030405" + labelVersion;
                break;
        }
        return labelId + "1";
    }
}

