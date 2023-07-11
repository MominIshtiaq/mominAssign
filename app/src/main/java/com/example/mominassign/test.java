package com.example.mominassign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class test extends Fragment {
    Context context;
    private TextView letterTextView, answerTextView;
    private char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
    private char[] rootLetters = {'g', 'j', 'p', 'q', 'y'};
    private char[] grassLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
    private String answerString = "";
    int questionCount;
    MyDbHelper dBHelper;

    public test(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        letterTextView = view.findViewById(R.id.letter_text_view);
        TextView answer_text_view = view.findViewById(R.id.answer_text_view);

        Button skyButton = view.findViewById(R.id.sky_button);
        Button grassButton = view.findViewById(R.id.grass_button);
        Button rootButton = view.findViewById(R.id.root_button);

        dBHelper = new MyDbHelper(requireContext());

        skyButton.setOnClickListener(v -> checkAnswer("Sky Letter"));
        grassButton.setOnClickListener(v -> checkAnswer("Grass Letter"));
        rootButton.setOnClickListener(v -> checkAnswer("Root Letter"));

        questionCount = 0;

        displayRandomLetter();

        // Clear the answerTextView when coming back from QuizResultFragment
        if (getArguments() != null && getArguments().getBoolean("clearAnswer", false)) {
            answer_text_view.setText("");
        }
    }

    private void displayRandomLetter() {
        Random random = new Random();
        int category = random.nextInt(3);
        char letter;
        switch (category) {
            case 0:
                letter = skyLetters[random.nextInt(skyLetters.length)];
                answerString = "Sky Letter";
                break;
            case 1:
                letter = grassLetters[random.nextInt(grassLetters.length)];
                answerString = "Grass Letter";
                break;
            default:
                letter = rootLetters[random.nextInt(rootLetters.length)];
                answerString = "Root Letter";
                break;
        }
        letterTextView.setText(String.valueOf(letter));
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer(String selectedAnswer) {
        questionCount++;
//        boolean isCorrect = selectedAnswer.equals(answerString);
//        String message;
//        if (isCorrect) {
//            message = "Awesome! Your answer is correct.";
//            insertQuestionScore(letterTextView.getText().toString(), ); // Insert the score of 1 for a correct answer
//        } else {
//            message = "Incorrect! The answer is " + answerString + ".";
//            insertQuestionScore(0); // Insert the score of 0 for an incorrect answer
//        }
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

        insertQuestionScore(letterTextView.getText().toString(), answerString, selectedAnswer);

        // Wait for 2 seconds and display a new letter
        new Handler().postDelayed(() -> {
            if (questionCount < 5) {
                displayRandomLetter();
            } else {
                finishQuiz();
            }
        }, 2000);
    }

    private void insertQuestionScore(String q, String a1, String a2) {
        // databaseHelper.deleteAllQuizResults();
//        dBHelper.insertQuestion(q);
//        dBHelper.insertActualAnswer(a1);
//        dBHelper.insertUserAns(a2);
        dBHelper.insertEverything(q,a1,a2);
    }

    private void finishQuiz() {
        String completionMessage = "Quiz completed!";
        Toast.makeText(requireContext(), completionMessage, Toast.LENGTH_SHORT).show();

        // Start the MainActivity
        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);

        // Finish the current activity (QuizActivity)
        requireActivity().finish();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dBHelper.close();
    }
}
