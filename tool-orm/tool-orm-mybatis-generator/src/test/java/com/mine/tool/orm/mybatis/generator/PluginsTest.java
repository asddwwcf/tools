package com.mine.tool.orm.mybatis.generator;

import org.junit.Test;
import org.mybatis.generator.api.ShellRunner;

/**
 * 功能 : 
 *
 */
public class PluginsTest {
    @Test
    public void run(){
        String config = this.getClass().getClassLoader().getResource("generatorConfig.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
    }
}
