package org.test;

import org.elasticsearch.common.settings.Settings;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;

/**
 * Description:
 *
 * @author: chixiao
 * @date: 2020-06-30
 * @time: 10:50
 */
public class TestIK {

    public static void main(String[] args) throws IOException {
        testIkSegment();
    }

    public static void testIkSegment() throws IOException {
//        String t = "木语窗帘套装4";
        String t = "得饶人处且饶人";
        Settings settings =  Settings.builder()
                .put("use_smart", false)
                .put("enable_lowercase", false)
                .put("enable_remote_dict", false)
//                .putList("ext_dic_main", Arrays.asList("#dicName$extra#dicPath$extra_test.dic#isRemote$false"))
                .build();
        Configuration configuration=new Configuration(null,settings).setUseSmart(false);
        IKSegmenter segmenter = new IKSegmenter(new StringReader(t), configuration);
        Lexeme next;
        while ((next = segmenter.next())!=null){
            System.out.println(next.getLexemeText());
        }
    }
}
