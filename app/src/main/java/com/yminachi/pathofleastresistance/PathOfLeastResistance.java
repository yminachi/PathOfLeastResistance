package com.yminachi.pathofleastresistance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathThroughGridCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathsThroughColumnCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.GridConverter;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.MinimumPathPicker;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.OutputFormatter;

public class PathOfLeastResistance extends AppCompatActivity {

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
        PathOfLeastResistanceCalculator pathOfLeastResistanceCalculator = getPathOfLeastResistanceCalculator();
        try {
            input = inputText.getText().toString();
            output = pathOfLeastResistanceCalculator.calculatePathOfLeastResistance(input);
            outputText.setText(output);
        } catch (Exception e) {
            outputText.setText(e.getMessage());
        }
    }

    private PathOfLeastResistanceCalculator getPathOfLeastResistanceCalculator(){
        MinimumPathPicker minimumPathPicker = new MinimumPathPicker();
        GridConverter gridConverter = new GridConverter();
        OutputFormatter outputFormatter = new OutputFormatter();
        BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator = new BestPathsThroughColumnCalculator(minimumPathPicker);
        BestPathThroughGridCalculator bestPathThroughGridCalculator = new BestPathThroughGridCalculator(bestPathsThroughColumnCalculator, minimumPathPicker);

        return new PathOfLeastResistanceCalculator(bestPathThroughGridCalculator, gridConverter, outputFormatter);
    }
}
