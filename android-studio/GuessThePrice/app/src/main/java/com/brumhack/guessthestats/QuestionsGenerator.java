package com.brumhack.guessthestats;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bertie on 26/10/2014.
 */
public final class QuestionsGenerator {
    private static QuestionsGenerator instance;
    private List<Question> questions;
    private Random rgen;

    private QuestionsGenerator() {
        rgen = new Random();
    }

    public static QuestionsGenerator getInstance() {
        if(instance == null) {
            instance = new QuestionsGenerator();
        }
        return instance;
    }

    public Question getRandomQuestion(Context context) {
        List<Question> questions = getQuestions(context);
        return questions.isEmpty()
                ? null
                : questions.get(rgen.nextInt(questions.size()));
    }

    public List<Question> getQuestions(Context context) {
        if(questions == null) {
            questions = new ArrayList<Question>();
            try {
                InputStream is = context.getAssets().open("questions.csv");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while((line = br.readLine()) != null) {
                    try {
                        String[] pieces = line.split(","); // pieces.length == 3
                        questions.add(new Question(pieces[0], pieces[1], Double.parseDouble(pieces[2])));
                    } catch(Exception e) {} // Just don't give a truck
                }
            } catch(IOException e) {
                // ohgodwhy.jpg
            }
        }
        return questions;
    }

    public List<Question> getRandomQuestions(Context context, int numQuestions) {
        List<Question> questions = new ArrayList<Question>();
        while(questions.size() < numQuestions) {
            Question question = getRandomQuestion(context);
            if(!questions.contains(question)) {
                questions.add(question);
            }
        }
        return questions;
    }
}
