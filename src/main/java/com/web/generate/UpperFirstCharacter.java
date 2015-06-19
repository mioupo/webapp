package com.web.generate;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.web.base.util.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author heqian
 * freemarker 首字母大写并且下划线后第一个字母大写
 *
 */
public class UpperFirstCharacter implements TemplateDirectiveModel {

	@Override
	public void execute(Environment environment, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
        
        if (body != null) {
            body.render(new UpperCaseFilterWriter(environment.getOut()));
        } else {
            throw new RuntimeException("missing templateDirectiveBody");
        }
		
	}
	private static class UpperCaseFilterWriter extends Writer {
	       
        private final Writer out;
           
        UpperCaseFilterWriter (Writer out) {
            this.out = out;
        }

        public void write(char[] cbuf, int off, int len)
                throws IOException {
        	String upperCase = StringUtils.capAndUpperCase(cbuf.toString());
        	out.write(upperCase.trim());///把空格去掉
        }

        public void flush() throws IOException {
            out.flush();
        }

        public void close() throws IOException {
            out.close();
        }
    }

}
