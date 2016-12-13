package com.cts.controller;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.InvalidFormatException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cts.form.InputLines;
/*import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.SpellCheckerOptions;
*/
@RestController
@EnableWebMvc
public class LineController {

	 @RequestMapping(value = "/lines", method = RequestMethod.GET)
	   public ModelAndView InputLines() {
	      return new ModelAndView("input", "command", new InputLines());
	   }

	   @RequestMapping(value = "/addLines", method = RequestMethod.POST, headers="Accept=application/json", produces= MediaType.APPLICATION_JSON_VALUE)

	   public @ResponseBody InputLines addStudent(@ModelAttribute("lines")InputLines lines) {

		   	InputStream is = null;
			try {
			is = new FileInputStream("C:/Users/Administrator/Downloads/en-sent.bin");
			SentenceModel model;
			model = new SentenceModel(is);
			SentenceDetectorME sdetector = new SentenceDetectorME(model);
			String sentences[] = sdetector.sentDetect(lines.getLines());
			if(sentences != null){
				StringBuffer str= new StringBuffer();
				for (String string : sentences) {
					str.append(string);
				}

				// To auto correct the spellings
				/*JEditorPane text = new JEditorPane();
		        text.setText(str.toString());
		        text.add( text );
		        SpellChecker.registerDictionaries(LineController.class.getResource("/dictionary"), "en");
		        SpellChecker.register(text);
		        SpellChecker.getOptions().setSuggestionsLimitMenu(0);
		        System.out.println("Hello World");*/

				lines.setLines(str.toString());
			}
			is.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		   return lines;
	   }
}
