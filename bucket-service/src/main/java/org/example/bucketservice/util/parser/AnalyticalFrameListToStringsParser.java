package org.example.bucketservice.util.parser;

import org.example.bucketservice.model.AnalyticalFrame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnalyticalFrameListToStringsParser {

    public List<List<String>> parseFramesToStrings(List<AnalyticalFrame> frames){
        List<List<String>> stringsFrames = new ArrayList<>();
        stringsFrames.add(parseRequestsToStrings(frames));
        stringsFrames.add(parseOperationsToStrings(frames));
        stringsFrames.add(parseDatesToStrings(frames));
        stringsFrames.add(parseProvidersToStrings(frames));
        stringsFrames.add(parsePricesToStrings(frames));
        return stringsFrames;
    }

    private List<String> parseRequestsToStrings(List<AnalyticalFrame> frames){
        return frames.stream()
                .map(frame -> frame.getRequest().toString())
                .toList();
    }

    private List<String> parseOperationsToStrings(List<AnalyticalFrame> frames){
        return frames.stream()
                .map(frame -> frame.getOperation().toString())
                .toList();
    }

    private List<String> parseDatesToStrings(List<AnalyticalFrame> frames){
        return frames.stream()
                .map(frame -> frame.getDateOfAction().toString())
                .toList();
    }

    private List<String> parseProvidersToStrings(List<AnalyticalFrame> frames){
        return frames.stream()
                .map(frame -> frame.getProvider())
                .toList();
    }

    private List<String> parsePricesToStrings(List<AnalyticalFrame> frames){
        return frames.stream()
                .map(frame -> Double.valueOf(frame.getPrice()).toString())
                .toList();
    }
}
