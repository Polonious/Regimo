package au.com.regimo.core.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TextGenerator {

	private static final Logger logger = LoggerFactory.getLogger(TextGenerator.class);
	
	public static String generateText(String templateString, Map<String, Object> model) {

		Writer resultOutput = new StringWriter();

		model.put("operator", SecurityUtils.getOperator());
		Configuration freemarkerConfiguration = new Configuration();
		freemarkerConfiguration.setTagSyntax(Configuration.SQUARE_BRACKET_TAG_SYNTAX);

		try {
			Template template = new Template("", new StringReader(templateString), freemarkerConfiguration);
			template.process(model, resultOutput);
		}
		catch (IOException e) {
			logger.error("Unable to create freemarker template", e);
			throw new RuntimeException(e);
		}
		catch (TemplateException e) {
			logger.error("Unable to generate content from text template :"+templateString, e);
			throw new RuntimeException(e);
		}
		return resultOutput.toString();
	}

}
