package rlkj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作任务分词Util
 */
public class JobContentSubUtil {

    private static   Map<String,List<String>> hotWord =new HashMap<>();

    public static String[] startSub(String content){
        String[] temps1 = content.split("，");

        return  temps1;
    }



    public static void main(String[] args){
        List<String> mc = new ArrayList<>();
        mc.add("开关");
        mc.add("电杆");
        mc.add("刀闸");
        List<String> dc = new ArrayList<>();
        dc.add("年检预试");
        dc.add("防腐");
        dc.add("微水试验");
        dc.add("取油样");
        hotWord.put("1",mc);
        hotWord.put("2",dc);

        String content  = "开关及CT年检预试、电杆及设备防腐、精益化整改，1511、1516刀闸设备防腐、精益化整改，避雷器接地整改，开关微水试验，CT取油样，10号爬梯处门型构架防腐";
        String[] temps = JobContentSubUtil.startSub(content);
        for (int i = 0; i < temps.length; i++ ) {
            System.out.println(temps[i]);
        }

    }
}
