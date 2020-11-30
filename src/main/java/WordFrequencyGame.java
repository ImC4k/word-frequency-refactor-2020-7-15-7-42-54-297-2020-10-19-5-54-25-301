import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String LINE_FEED = "\n";

    public String getResult(String sentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = sentence.split(WHITE_SPACE_REGEX);

            List<WordFrequency> wordFrequencyList = new ArrayList<>();
            for (String word : words) {
                WordFrequency wordFrequency = new WordFrequency(word, 1);
                wordFrequencyList.add(wordFrequency);
            }

            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> wordToFrequencyListMap = getListMap(wordFrequencyList);

            List<WordFrequency> list = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : wordToFrequencyListMap.entrySet()) {
                WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                list.add(wordFrequency);
            }
            wordFrequencyList = list;

            wordFrequencyList.sort((wordFrequency1, wordFrequency2) -> wordFrequency2.getWordCount() - wordFrequency1.getWordCount());

            StringJoiner wordFrequencyResultJoiner = new StringJoiner(LINE_FEED);
            for (WordFrequency wordFrequency : wordFrequencyList) {
                String wordFrequencyResultLine = generateWordFrequencyResultLine(wordFrequency);
                wordFrequencyResultJoiner.add(wordFrequencyResultLine);
            }
            return wordFrequencyResultJoiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String generateWordFrequencyResultLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getWordCount());
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordToFrequencyListMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordToFrequencyListMap.containsKey(wordFrequency.getWord())) {
                ArrayList frequencyList = new ArrayList<>();
                frequencyList.add(wordFrequency);
                wordToFrequencyListMap.put(wordFrequency.getWord(), frequencyList);
            } else {
                wordToFrequencyListMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return wordToFrequencyListMap;
    }


}
