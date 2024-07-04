package com.bongoacademy.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionCollection extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    public static List<QuestionModule> question_list;
    int score;
    public static String SUBJECT_NAME = "";
    public static ArrayList <ArrayList<QuestionModule>> questionBank = new ArrayList<>();
    public static ArrayList <HashMap<String, String>> subjectList = new ArrayList<>();
    LinearLayout rootLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        rootLay = findViewById(R.id.rootLay);
        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);
        loadQuestion();

    }




    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rootLay!=null) rootLay.startAnimation(AnimationUtils.loadAnimation(QuestionCollection.this, R.anim.middle_to_top));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void loadQuestion(){
        //Toast.makeText(getApplicationContext(), "Total Questions: "+question_list.size(), Toast.LENGTH_SHORT).show();
        if(question_list.size() > 0) {
            QuestionModule q = question_list.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));
            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }



    //===============================================================================================







    //====================================================================
    //====================================================================
    public static  ArrayList <QuestionModule> questions;
    public static void createQuestionBank(){
        QuestionCollection.subjectList = new ArrayList<>();
        QuestionCollection.questionBank = new ArrayList<>();



        //------------- Subject 1
        questions = new ArrayList(){
            {
                add(new QuestionModule("এই অ্যাপটি কার স্মরণে তৈরি?", "A", "অফসারুল ইসলাম আদর", "তৌফিক আলম", "মাসুমুল হক", "জুবায়ের হোসেন"));
                add(new QuestionModule("বাংলাদেশের রাজধানীর নাম কি?", "A", "ঢাকা", "খুলনা","বরিশাল", "ফেনী"));
                add(new QuestionModule("বাংলাদেশের মুদ্রার নাম কি?", "A", "টাকা", "ডলার","রুপি", "বাথ"));
                add(new QuestionModule("বিজয় দিবস কত তারিখে পালন করা হয়?", "D", "২৬ মার্চ", "২১ ফেব্রুয়ারি","১৪ ডিসেম্বর", "১৬ ডিসেম্বর"));
                add(new QuestionModule("স্মৃতিসৌধের কয়টি স্তম্ভ?", "B", "৫ টি", "৭ টি", "৯ টি" , "১০ টি"));
                add(new QuestionModule("ঢাকার পূর্ব নাম কি ছিল?", "D", "ইসলামাবাদ", "আগরতলা", "বিজয়নগর", "জাহাঙ্গিরনগর"));
                add(new QuestionModule("রোকেয়া দিবস কত তারিখে পালন করা হয়?", "C", "১৭ ফেব্রুয়ারি", "১৯ ফেব্রুয়ারি", "৯ ডিসেম্বর", "২২ ফেব্রুয়ারি"));
                add(new QuestionModule("দিল্লির হিংহাসনে অধিষ্ঠিত প্রথম মুসলিম নারী কে?", "B", "রোকেয়া ইসলাম", "সুলতানা রাজিয়া", "সালেহা বেগম", "পারভিন সুলতানা"));
                add(new QuestionModule("উপমহাদেশের প্রথম অস্কার জয়ী কে?", "B", "রবীন্দ্রনাথ ঠাকুর", "সত্যজিৎ রায়", "জুবায়ের হোসেন", "নুহাশ হুমায়ুন"));
                add(new QuestionModule("বাংলাদেশের গভীরতম নদী কোনটি?", "D", "পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("সাধারন জ্ঞান", R.drawable.category_icon1, questions);



        //------------- Subject 2
        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশ ও ভারতের মধ্যে চলাচলকারী ট্রেনের নাম কী?", "D", "মৈত্রী এক্সপ্রেস", "বন্ধন এক্সপ্রেস", "মিতালী এক্সপ্রেস", "ওপরের সবগুলাে"));
                add(new QuestionModule("বঙ্গবন্ধু শেখ মুজিব ভ্রাম্যমাণ রেল জাদুঘর কবে উদ্বোধন করা হয়?", "A", "২৭ এপ্রিল ২০২২", "৩০ এপ্রিল ২০২২","২ মে ২০২২", "৫ মে ২০২২"));
                add(new QuestionModule("বাংলাদেশ সিকিউরিটিজ অ্যান্ড এক্সচেঞ্জ কমিশনের (BSEC) প্রথম নারী কমিশনার কে?", "C", "কবিতা খানম", "রাশিদা সুলতানা","রুমানা ইসলাম", "রাজিয়া বেগম"));
                add(new QuestionModule("২৫ মে ২০২২ পর্যন্ত দেশের GI পণ্য কতটি?", "A", "১০ টি", "১২ টি","১৪ টি", "১৭ টি"));
                add(new QuestionModule("সপদ্মা বহুমুখী সেতুর দৈর্ঘ্য কত ?", "B", "৬ কিমি", "৬.১৫ কিমি", "৭.১৫ কিমি" , "৮.১৫ কিমি"));
                add(new QuestionModule("বর্তমানে দেশে সরকারি বিশ্ববিদ্যালয় কতটি?", "D", "৪৮ টি", "৪৯ টি", "৫০ টি", "৫১ টি"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী মাথাপিছু GDP কত?", "D", "২,৪৯৭ মার্কিন ডলার", "২,৫৯৭ মার্কিন ডলার", "২,৬৯৭ মার্কিন ডলার", "২,৭২৩ মার্কিন ডলার"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী GDP’র প্রবৃদ্ধির হার কত?", "B", "৫.৪৩%", "৭.২৫%", "৬.৯৪%", "৭.৪৭%"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী কৃষি খাতের অবদানের হার কত?", "A", "১১.৫০%", "১২.১৫%", "১৩. ৪৭%", "১৪.৭৯%"));
                add(new QuestionModule("দেশের ৫১তম সরকারি বিশ্ববিদ্যালয় কোনটি?", "A", "কুড়িগ্রাম কৃষি বিশ্ববিদ্যালয়", "ঠাকুরগাঁও বিশ্ববিদ্যালয়", "বঙ্গবন্ধু শেখ মুজিবুর রহমান প্রযুক্তি বিশ্ববিদ্যালয়", " সুনামগঞ্জ বিজ্ঞান ও প্রযুক্তি বিশ্ববিদ্যালয়"));
            }
        };
        QuestionModule.createQuestionsForSubject("বাংলাদেশ", R.drawable.category_icon2, questions);







        //------------- Subject 3
        questions = new ArrayList(){
            {
                add(new QuestionModule("এই অ্যাপটি কার স্মরণে তৈরি?", "A", "অফসারুল ইসলাম আদর", "তৌফিক আলম", "মাসুমুল হক", "জুবায়ের হোসেন"));
                add(new QuestionModule("বাংলাদেশের রাজধানীর নাম কি?", "A", "ঢাকা", "খুলনা","বরিশাল", "ফেনী"));
                add(new QuestionModule("বাংলাদেশের মুদ্রার নাম কি?", "A", "টাকা", "ডলার","রুপি", "বাথ"));
                add(new QuestionModule("বিজয় দিবস কত তারিখে পালন করা হয়?", "D", "২৬ মার্চ", "২১ ফেব্রুয়ারি","১৪ ডিসেম্বর", "১৬ ডিসেম্বর"));
                add(new QuestionModule("স্মৃতিসৌধের কয়টি স্তম্ভ?", "B", "৫ টি", "৭ টি", "৯ টি" , "১০ টি"));
                add(new QuestionModule("ঢাকার পূর্ব নাম কি ছিল?", "D", "ইসলামাবাদ", "আগরতলা", "বিজয়নগর", "জাহাঙ্গিরনগর"));
                add(new QuestionModule("রোকেয়া দিবস কত তারিখে পালন করা হয়?", "C", "১৭ ফেব্রুয়ারি", "১৯ ফেব্রুয়ারি", "৯ ডিসেম্বর", "২২ ফেব্রুয়ারি"));
                add(new QuestionModule("দিল্লির হিংহাসনে অধিষ্ঠিত প্রথম মুসলিম নারী কে?", "B", "রোকেয়া ইসলাম", "সুলতানা রাজিয়া", "সালেহা বেগম", "পারভিন সুলতানা"));
                add(new QuestionModule("উপমহাদেশের প্রথম অস্কার জয়ী কে?", "B", "রবীন্দ্রনাথ ঠাকুর", "সত্যজিৎ রায়", "জুবায়ের হোসেন", "নুহাশ হুমায়ুন"));
                add(new QuestionModule("বাংলাদেশের গভীরতম নদী কোনটি?", "D", "পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("পদার্থ বিজ্ঞান", R.drawable.category_icon3, questions);



        //------------- Subject 4
        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশ ও ভারতের মধ্যে চলাচলকারী ট্রেনের নাম কী?", "D", "মৈত্রী এক্সপ্রেস", "বন্ধন এক্সপ্রেস", "মিতালী এক্সপ্রেস", "ওপরের সবগুলাে"));
                add(new QuestionModule("বঙ্গবন্ধু শেখ মুজিব ভ্রাম্যমাণ রেল জাদুঘর কবে উদ্বোধন করা হয়?", "A", "২৭ এপ্রিল ২০২২", "৩০ এপ্রিল ২০২২","২ মে ২০২২", "৫ মে ২০২২"));
                add(new QuestionModule("বাংলাদেশ সিকিউরিটিজ অ্যান্ড এক্সচেঞ্জ কমিশনের (BSEC) প্রথম নারী কমিশনার কে?", "C", "কবিতা খানম", "রাশিদা সুলতানা","রুমানা ইসলাম", "রাজিয়া বেগম"));
                add(new QuestionModule("২৫ মে ২০২২ পর্যন্ত দেশের GI পণ্য কতটি?", "A", "১০ টি", "১২ টি","১৪ টি", "১৭ টি"));
                add(new QuestionModule("সপদ্মা বহুমুখী সেতুর দৈর্ঘ্য কত ?", "B", "৬ কিমি", "৬.১৫ কিমি", "৭.১৫ কিমি" , "৮.১৫ কিমি"));
                add(new QuestionModule("বর্তমানে দেশে সরকারি বিশ্ববিদ্যালয় কতটি?", "D", "৪৮ টি", "৪৯ টি", "৫০ টি", "৫১ টি"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী মাথাপিছু GDP কত?", "D", "২,৪৯৭ মার্কিন ডলার", "২,৫৯৭ মার্কিন ডলার", "২,৬৯৭ মার্কিন ডলার", "২,৭২৩ মার্কিন ডলার"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী GDP’র প্রবৃদ্ধির হার কত?", "B", "৫.৪৩%", "৭.২৫%", "৬.৯৪%", "৭.৪৭%"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী কৃষি খাতের অবদানের হার কত?", "A", "১১.৫০%", "১২.১৫%", "১৩. ৪৭%", "১৪.৭৯%"));
                add(new QuestionModule("দেশের ৫১তম সরকারি বিশ্ববিদ্যালয় কোনটি?", "A", "কুড়িগ্রাম কৃষি বিশ্ববিদ্যালয়", "ঠাকুরগাঁও বিশ্ববিদ্যালয়", "বঙ্গবন্ধু শেখ মুজিবুর রহমান প্রযুক্তি বিশ্ববিদ্যালয়", " সুনামগঞ্জ বিজ্ঞান ও প্রযুক্তি বিশ্ববিদ্যালয়"));
            }
        };
        QuestionModule.createQuestionsForSubject("রসায়ন", R.drawable.category_icon4, questions);






        //------------- Subject 5
        questions = new ArrayList(){
            {
                add(new QuestionModule("এই অ্যাপটি কার স্মরণে তৈরি?", "A", "অফসারুল ইসলাম আদর", "তৌফিক আলম", "মাসুমুল হক", "জুবায়ের হোসেন"));
                add(new QuestionModule("বাংলাদেশের রাজধানীর নাম কি?", "A", "ঢাকা", "খুলনা","বরিশাল", "ফেনী"));
                add(new QuestionModule("বাংলাদেশের মুদ্রার নাম কি?", "A", "টাকা", "ডলার","রুপি", "বাথ"));
                add(new QuestionModule("বিজয় দিবস কত তারিখে পালন করা হয়?", "D", "২৬ মার্চ", "২১ ফেব্রুয়ারি","১৪ ডিসেম্বর", "১৬ ডিসেম্বর"));
                add(new QuestionModule("স্মৃতিসৌধের কয়টি স্তম্ভ?", "B", "৫ টি", "৭ টি", "৯ টি" , "১০ টি"));
                add(new QuestionModule("ঢাকার পূর্ব নাম কি ছিল?", "D", "ইসলামাবাদ", "আগরতলা", "বিজয়নগর", "জাহাঙ্গিরনগর"));
                add(new QuestionModule("রোকেয়া দিবস কত তারিখে পালন করা হয়?", "C", "১৭ ফেব্রুয়ারি", "১৯ ফেব্রুয়ারি", "৯ ডিসেম্বর", "২২ ফেব্রুয়ারি"));
                add(new QuestionModule("দিল্লির হিংহাসনে অধিষ্ঠিত প্রথম মুসলিম নারী কে?", "B", "রোকেয়া ইসলাম", "সুলতানা রাজিয়া", "সালেহা বেগম", "পারভিন সুলতানা"));
                add(new QuestionModule("উপমহাদেশের প্রথম অস্কার জয়ী কে?", "B", "রবীন্দ্রনাথ ঠাকুর", "সত্যজিৎ রায়", "জুবায়ের হোসেন", "নুহাশ হুমায়ুন"));
                add(new QuestionModule("বাংলাদেশের গভীরতম নদী কোনটি?", "D", "পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("জীব বিজ্ঞান", R.drawable.category_icon5, questions);



        //------------- Subject 6
        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশ ও ভারতের মধ্যে চলাচলকারী ট্রেনের নাম কী?", "D", "মৈত্রী এক্সপ্রেস", "বন্ধন এক্সপ্রেস", "মিতালী এক্সপ্রেস", "ওপরের সবগুলাে"));
                add(new QuestionModule("বঙ্গবন্ধু শেখ মুজিব ভ্রাম্যমাণ রেল জাদুঘর কবে উদ্বোধন করা হয়?", "A", "২৭ এপ্রিল ২০২২", "৩০ এপ্রিল ২০২২","২ মে ২০২২", "৫ মে ২০২২"));
                add(new QuestionModule("বাংলাদেশ সিকিউরিটিজ অ্যান্ড এক্সচেঞ্জ কমিশনের (BSEC) প্রথম নারী কমিশনার কে?", "C", "কবিতা খানম", "রাশিদা সুলতানা","রুমানা ইসলাম", "রাজিয়া বেগম"));
                add(new QuestionModule("২৫ মে ২০২২ পর্যন্ত দেশের GI পণ্য কতটি?", "A", "১০ টি", "১২ টি","১৪ টি", "১৭ টি"));
                add(new QuestionModule("সপদ্মা বহুমুখী সেতুর দৈর্ঘ্য কত ?", "B", "৬ কিমি", "৬.১৫ কিমি", "৭.১৫ কিমি" , "৮.১৫ কিমি"));
                add(new QuestionModule("বর্তমানে দেশে সরকারি বিশ্ববিদ্যালয় কতটি?", "D", "৪৮ টি", "৪৯ টি", "৫০ টি", "৫১ টি"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী মাথাপিছু GDP কত?", "D", "২,৪৯৭ মার্কিন ডলার", "২,৫৯৭ মার্কিন ডলার", "২,৬৯৭ মার্কিন ডলার", "২,৭২৩ মার্কিন ডলার"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী GDP’র প্রবৃদ্ধির হার কত?", "B", "৫.৪৩%", "৭.২৫%", "৬.৯৪%", "৭.৪৭%"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী কৃষি খাতের অবদানের হার কত?", "A", "১১.৫০%", "১২.১৫%", "১৩. ৪৭%", "১৪.৭৯%"));
                add(new QuestionModule("দেশের ৫১তম সরকারি বিশ্ববিদ্যালয় কোনটি?", "A", "কুড়িগ্রাম কৃষি বিশ্ববিদ্যালয়", "ঠাকুরগাঁও বিশ্ববিদ্যালয়", "বঙ্গবন্ধু শেখ মুজিবুর রহমান প্রযুক্তি বিশ্ববিদ্যালয়", " সুনামগঞ্জ বিজ্ঞান ও প্রযুক্তি বিশ্ববিদ্যালয়"));
            }
        };
        QuestionModule.createQuestionsForSubject("জ্যোতির্বিজ্ঞান", R.drawable.category_icon6, questions);











    }




//====================================================================
// ====================================================================
//====================================================================
// ====================================================================

}
