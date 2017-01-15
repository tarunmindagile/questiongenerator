package com.mindagile.questiongenerator.nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class NounPhraseExtractor {
	//static String sentence = "This article provides a review of the literature on clinical correlates of awareness in dementia. Most inconsistencies were found with regard to an association between depression and higher levels of awareness. Dysthymia, but not major depression, is probably related to higher levels of awareness. Anxiety also appears to be related to higher levels of awareness. Apathy and psychosis are frequently present in patients with less awareness, and may share common neuropathological substrates with awareness. Furthermore, unawareness seems to be related to difficulties in daily life functioning, increased caregiver burden, and deterioration in global dementia severity. Factors that may be of influence on the inconclusive data are discussed, as are future directions of research.";

	static Set<String> nounPhrases = new HashSet<>();
	static Map<String, List<String>> sentenceToNounPhraseMap = new HashMap<>();

	public static void main(String[] args) throws IOException {

		String sentence =WikiPediaHelper.getWikiPediaString("https://en.wikipedia.org/wiki/Amitabh_Bachchan");
		InputStream modelInParse = null;
		InputStream smis = null;
		try {
			// load chunking model
			smis = new FileInputStream("/home/tarun/Desktop/en-sent.bin");
			modelInParse = new FileInputStream("/home/tarun/Desktop/en-parser-chunking.bin");
			SentenceModel smodel = new SentenceModel(smis);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(smodel);

			Span[] sentSpans = sentenceDetector.sentPosDetect(sentence);
			
			ParserModel model = new ParserModel(modelInParse);
			Parser parser = ParserFactory.create(model);
			for (Span sentSpan : sentSpans) {
				
				String sent = sentSpan.getCoveredText(sentence).toString();
				// System.out.println("ddddddddddddddd" + sent);
				printNounPhrase(sent, modelInParse, parser);
			}
			
			for(String key :sentenceToNounPhraseMap.keySet()){
				System.out.println(key+":");
				System.out.println("_______________________________");
				for(String str : sentenceToNounPhraseMap.get(key)){
					System.out.println(str);
				}
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelInParse != null) {
				try {
					modelInParse.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static synchronized void printNounPhrase(String sentence, InputStream modelInParse, Parser parser) throws IOException {
		
		Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);

		for (Parse p : topParses)
			getNounPhrases(p, sentence);
	}

	// recursively loop through tree, extracting noun phrases
	public static void getNounPhrases(Parse p, String sentence) {

		if (p.getType().equals("NP")) { // NP=noun phrase
			nounPhrases.add(p.getCoveredText());
			if(sentenceToNounPhraseMap.get(sentence)==null){
				List<String> list = new ArrayList<>();
				sentenceToNounPhraseMap.put(sentence, list);
			}
			sentenceToNounPhraseMap.get(sentence).add(p.getCoveredText());
		}
		for (Parse child : p.getChildren())
			getNounPhrases(child, sentence);
	}
}
