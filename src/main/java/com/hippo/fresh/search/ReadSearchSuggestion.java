package com.hippo.fresh.search;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadSearchSuggestion {
    private File file;

    @Autowired
    SearchSuggestionRepository searchSuggestionRepository;

    public List<SearchSuggestion> Read() throws IOException {
        file = new File("/home/wyyadd/Desktop/word.txt");
        List<SearchSuggestion> word = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader((new FileReader(file)));
        String line;
        int num = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] sub = line.split("[ ,，、]");
            for(String i : sub){
                if(i.length() >= 1 && i != "\n"){
                    SearchSuggestion searchSuggestion = new SearchSuggestion();
                    searchSuggestion.setId(String.valueOf(++num));
                    searchSuggestion.setName(i);
                    word.add(searchSuggestion);
                }
            }
        }
       return word;
    }
}