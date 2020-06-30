package org.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.junit.Test;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/**
 * Description:
 *
 * @author: chixiao
 * @date: 2019-11-28
 * @time: 16:25
 */
public class TestIk {

    @Test
    public void testAnalyzer() throws IOException {
        Settings settings =  Settings.builder()
//                .put("use_smart", true)
                .put("enable_lowercase", false)
                .put("enable_remote_dict", false)
//                .putList("ext_dic_main", Arrays.asList("#dicName$extra#dicPath$extra_test.dic#isRemote$false"))
                .build();
//        Environment env = new Environment(null, null);
        Configuration configuration=new Configuration(null,settings).setUseSmart(true);

        IKAnalyzer ik =new IKAnalyzer(configuration);

        String t = "得饶人处且饶人";
//        String t = "IK分词器Lucene Analyzer接口实现类 民生银行 我是中国人";
//        String t = "分词器";
        TokenStream tokenStream = ik.tokenStream("", new StringReader(t));
        tokenStream.reset();
        CharTermAttribute termAtt  = tokenStream.addAttribute(CharTermAttribute.class);
        while(tokenStream.incrementToken()){
            System.out.println(termAtt);
        }
        tokenStream.end();
        tokenStream.close();
    }

    @Test
    public void testIkSegment() throws IOException {
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
