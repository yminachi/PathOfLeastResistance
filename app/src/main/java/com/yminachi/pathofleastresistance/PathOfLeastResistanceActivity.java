package com.yminachi.pathofleastresistance;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PathOfLeastResistanceActivity extends Activity {

	private TextView outputText;
	private EditText inputText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_of_least_resistance);

		outputText = (TextView) findViewById(R.id.outputText);
		inputText = (EditText) findViewById(R.id.inputText);
	}

	public void calculatePathOfLeastResistance(View view) {
		String input, output;
		PathOfLeastResistanceCalculator pathOfLeastResistanceCalculator = new PathOfLeastResistanceCalculator();
		input = inputText.getText().toString();
		output = pathOfLeastResistanceCalculator.calculatePathOfLeastResistance(input);
		outputText.setText(output);
	}
}
