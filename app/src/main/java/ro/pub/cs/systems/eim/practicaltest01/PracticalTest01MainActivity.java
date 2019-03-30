package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private EditText leftTextEdit = null;
    private EditText rightTextEdit = null;
    private Button butLeft = null;
    private Button butRight = null;
    private Button navigateToSecondaryActivityButton = null;

    private ButtonLisener but = new ButtonLisener();
    private class ButtonLisener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.left_button:
                    int left = Integer.parseInt(leftTextEdit.getText().toString());
                    left++;
                    leftTextEdit.setText(String.valueOf(left));
                    break;
                case R.id.right_button:
                    int right = Integer.parseInt(rightTextEdit.getText().toString());
                    right++;
                    rightTextEdit.setText(String.valueOf(right));
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    int numberOfClicks = Integer.parseInt(leftTextEdit.getText().toString()) +
                            Integer.parseInt(rightTextEdit.getText().toString());
                    intent.putExtra("numberOfClicks", numberOfClicks);
                    startActivityForResult(intent, 270);
                    break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        leftTextEdit = (EditText)findViewById(R.id.left_edit_text);
        rightTextEdit = (EditText)findViewById(R.id.right_edit_text);

        butLeft = (Button)findViewById(R.id.left_button);
        butRight = (Button)findViewById(R.id.right_button);

        butLeft.setOnClickListener(but);
        butRight.setOnClickListener(but);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("leftCount")) {
                leftTextEdit.setText(savedInstanceState.getString("leftCount"));
            } else {
                leftTextEdit.setText(String.valueOf(0));
            }
            if (savedInstanceState.containsKey("rightCount")) {
                rightTextEdit.setText(savedInstanceState.getString("rightCount"));
            } else {
                rightTextEdit.setText(String.valueOf(0));
            }
        } else {
            leftTextEdit.setText(String.valueOf(0));
            rightTextEdit.setText(String.valueOf(0));
        }

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(but);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("leftCount", leftTextEdit.getText().toString());
        savedInstanceState.putString("rightCount", rightTextEdit.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("leftCount")) {
            leftTextEdit.setText(savedInstanceState.getString("leftCount"));
        } else {
            leftTextEdit.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("rightCount")) {
            rightTextEdit.setText(savedInstanceState.getString("rightCount"));
        } else {
            rightTextEdit.setText(String.valueOf(0));
        }
    }
}
