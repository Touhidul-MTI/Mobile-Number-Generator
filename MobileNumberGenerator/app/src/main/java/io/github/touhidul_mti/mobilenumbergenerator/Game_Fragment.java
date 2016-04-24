package io.github.touhidul_mti.mobilenumbergenerator;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Game_Fragment extends Fragment implements View.OnClickListener {

    TextView a_tv1, a_tv2;
    EditText a_et1;
    Button backButton, nextButton;
    int stepClickCounter;

    long mobileNumber;

    Communicator communicator;
    String[] stepDescriptions = {
            "Ask your friend to write down 10 digits of his/her mobile number (without first zero) on a paper. It's not important, just to make the calculations easy for him/her.\n" +
                    "Suppose, if the mobile number is 01234-567890, he/she will consider only 1234-567890 (excluding first zero).",
            "Tell your friend to take the first 3 (three) digits of the mobile number and multiply it by 2000.\nSuppose, if mobile number is 1234-567890, multiply first 3 digits 123 by 2000." +
                    "\nWarning: Must not take the first zero of number as said before.",
            "Now tell him/her to add the next 3 (three) digits for 2 times with the result.\n" +
                    "Suppose, if the next 3 digits are 456, then do it like: result+456+456 or, result+(2*456).",
            "Tell him/her to multiply the result by 80.",
            "Ask him/her to add 1 (one) to the result.",
            "Tell him/her to once again multiply the result by 250.",
            "Now tell him/her to add the last 4 (four) digits a total of 4 times with the result. " +
                    "Suppose, if the last 4 digits are 7890, then do it like: result+7890+7890+7890+7890 or, result+(4*7890).",
            "May be your friend is tired now. So, no more calculations, just take the result from him/her.\nNow enter the result below and click on GENERATE."};
    String stepMessage, stepText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        View view = inflater.inflate(R.layout.fragment_game_, container, false);

        if (savedInstanceState == null) {
            stepClickCounter = 0;
            stepMessage = "Ask your friend to write down 10 digits of his/her mobile number (without first zero) on a paper. It's not important, just to make the calculations easy for him/her.\n" +
                    "Suppose, if the mobile number is 01234-567890, he/she will consider only 1234-567890 (excluding first zero).";
            stepText = "STEP A";
        } else {
            stepClickCounter = savedInstanceState.getInt("stepCounter");
            stepMessage = (String) savedInstanceState.get("stepDescription");
            stepText = (String) savedInstanceState.get("stepText");
        }

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        a_tv1 = (TextView) getActivity().findViewById(R.id.gamea_tv1);
        a_tv2 = (TextView) getActivity().findViewById(R.id.gamea_tv2);
        backButton = (Button) getActivity().findViewById(R.id.gamea_b1);
        nextButton = (Button) getActivity().findViewById(R.id.gamea_b2);
        a_et1 = (EditText) getActivity().findViewById(R.id.gamea_et1);

        communicator = (Communicator) getActivity();//typecast the activity to communicator

        //upto final step, I hide edit text
        a_et1.setEnabled(false);
        a_et1.setInputType(InputType.TYPE_NULL);
        a_et1.setFocusable(false);

        a_tv2.setText(stepMessage);
        a_tv1.setText(stepText);
        backButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == nextButton) {
            stepClickCounter++;
            goSetStep();
        } else if (v == backButton) {
            if (stepClickCounter == 0) {
                getActivity().finish();
            } else if (stepClickCounter >= 7) {
                stepClickCounter = 6;
                a_et1.setText("");
                a_et1.setHint("");
                a_et1.setEnabled(false);
                a_et1.setInputType(InputType.TYPE_NULL);
                a_et1.setFocusable(false);
                nextButton.setText("NEXT STEP");
                goSetStep();
            } else {
                stepClickCounter--;
                goSetStep();
            }
        }
    }

    public void goSetStep() {
        if (stepClickCounter == 0) {
            stepMessage = stepDescriptions[0];
            a_tv1.setText("STEP A");
            a_tv2.setText(stepMessage);

        } else if (stepClickCounter == 1) {
            stepMessage = stepDescriptions[1];
            a_tv1.setText("STEP B");
            a_tv2.setText(stepMessage);

        } else if (stepClickCounter == 2) {
            stepMessage = stepDescriptions[2];
            a_tv1.setText("STEP C");
            a_tv2.setText(stepMessage);

        } else if (stepClickCounter == 3) {
            stepMessage = stepDescriptions[3];
            a_tv1.setText("STEP D");
            a_tv2.setText(stepMessage);

        } else if (stepClickCounter == 4) {
            stepMessage = stepDescriptions[4];
            a_tv1.setText("STEP E");
            a_tv2.setText(stepMessage);

        } else if (stepClickCounter == 5) {
            stepMessage = stepDescriptions[5];
            a_tv1.setText("STEP F");
            a_tv2.setText(stepMessage);

        } else if (stepClickCounter == 6) {
            stepMessage = stepDescriptions[6];
            a_tv1.setText("STEP G");
            a_tv2.setText(stepMessage);

        } else if (stepClickCounter == 7) {
            stepMessage = stepDescriptions[7];
            a_tv1.setText("FINAL STEP");
            a_tv2.setText(stepMessage);
            a_et1.setHint("Final Result Here");
            //final step, so edittext appear
            a_et1.setEnabled(true);
            a_et1.setInputType(InputType.TYPE_CLASS_TEXT);
            a_et1.setFocusableInTouchMode(true);
            a_et1.setFocusable(true);

            nextButton.setText("GENERATE");

        } else {

            try {
                mobileNumber = Long.parseLong(a_et1.getText().toString());
            } catch (NumberFormatException e) {
                //Toast.makeText(getActivity(), "Enter valid result", Toast.LENGTH_LONG).show();
            }

            if(mobileNumber <=0){
                Toast.makeText(getActivity(), "Enter valid result", Toast.LENGTH_LONG).show();
            }else {
                mobileNumber-=250;
                mobileNumber/=4;
                int digitCount= 0;
                for(long n=mobileNumber; n>0; n/=10){
                    digitCount++;
                }
                if(digitCount!=10){
                    Toast.makeText(getActivity(), "Incorrect Calculations", Toast.LENGTH_LONG).show();
                }else{
                    a_et1.setText("");
                    BackupFragmentDataToPassAnotherFragment.setMobileNumber("0" + mobileNumber);
                    communicator.communicateResponse();
                }
            }
        }
    }

    //if screen rotate or something that make fragment destroy and reset,
    //before destroy this method called and save the value
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("stepCounter", stepClickCounter);
        //outState.putString("stepText", st);
        outState.putString("stepDescription", stepMessage);
        outState.putString("stepText", a_tv1.getText().toString());

    }
}
