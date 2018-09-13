package rlkj.common.participle;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Title:
 * @Description: 扩展分词
 * @Author: WuNing
 * @Date: Created in 2018/9/11 14:08
 */
public class DicUtil {
    public static void extendDic(){
        Configuration cfg= DefaultConfig.getInstance();
        System.out.println(cfg.getMainDictionary());
        Dictionary.initial(cfg);
        Dictionary dic=Dictionary.getSingleton();
        HashSet<String> set=new HashSet<>(Arrays.asList("吴富贵",
                "预试","隔离开关","开关微水试验"



        ));
        dic.addWords(set);
    }
}
