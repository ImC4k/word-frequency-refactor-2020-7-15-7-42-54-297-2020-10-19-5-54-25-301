import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITE_SPACE_REGEX = "\\s+";
    private static final String LINE_FEED = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = calculateWordFrequency(sentence);

            wordFrequencyList.sort((wordFrequency1, wordFrequency2) -> wordFrequency2.getFrequency() - wordFrequency1.getFrequency());

            return generateWordFrequencyReport(wordFrequencyList);
        } catch (Exception exception) {
            return "Calculate Error";
        }
    }

    private String generateWordFrequencyReport(List<WordFrequency> wordFrequencyList) {
        StringJoiner wordFrequencyResultJoiner = new StringJoiner(LINE_FEED);
        wordFrequencyList.stream().map(this::generateWordFrequencyResultLine).forEach(wordFrequencyResultJoiner::add);
        return wordFrequencyResultJoiner.toString();
    }

    private List<WordFrequency> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.stream(sentence.split(WHITE_SPACE_REGEX)).collect(Collectors.toList());
        HashSet<String> distinctWords = new HashSet<>(words);
        return distinctWords.stream().map(distinctWord -> new WordFrequency(distinctWord, Collections.frequency(words, distinctWord))).collect(Collectors.toList());
    }

    private String generateWordFrequencyResultLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getFrequency());
    }

}
