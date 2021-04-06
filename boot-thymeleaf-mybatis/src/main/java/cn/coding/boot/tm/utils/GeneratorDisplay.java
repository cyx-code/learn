package cn.coding.boot.tm.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis逆向工程
 */
public class GeneratorDisplay {
    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser configurationParser = new ConfigurationParser(warnings);
        File configFile = ResourceUtils.getFile("classpath:generator/generatorConfig.xml");
        Configuration config = configurationParser.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

    public static void main(String[] args) {
        GeneratorDisplay display = new GeneratorDisplay();
        try {
            display.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
