package sop.augmentedandroids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingsActivity extends AppCompatActivity{

    private static final String TAG = "SOP::SettingsActivity";

    // User inputs are strings because the input is given through EditText
    private String frameSkip = "5";
    private String numberOfDilations = "1";

    // RefObjDetector parameters
    private int refObjHue;
    private int refObjColThreshold;
    private int refObjSatMinimum;
    private int value;
    private String refObjMinContourArea = "500";
    private String refObjSideRatioLimit = "1.45";

    // MeasObjDetector variables
    private String measObjBound;
    private String measObjMaxBound;
    private String measObjMaxArea;
    private String measObjMinArea;

    private TextView hueTextView;
    private TextView saturationTextView;
    private TextView valueTextView;
    private TextView refObjColThresholdTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Settings activity", "Settings activity created.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        initTextViews();
        initSeekbars();
    }

    public void onApplySettings(View view) {
        /* Function gets called when Apply-button is pressed and gets values of the fields below
        *  and passes them to MainActivity via created intent. */
        Intent intent = new Intent();

        EditText frameSkipEditText = (EditText) findViewById(R.id.edit_text_frameskip);
        frameSkip = frameSkipEditText.getText().toString();

        EditText dilationsEditText = (EditText) findViewById(R.id.edit_text_dilations);
        numberOfDilations = dilationsEditText.getText().toString();

        EditText minContourAreaEditText = (EditText) findViewById(R.id.edit_text_min_contour_area);
        refObjMinContourArea = minContourAreaEditText.getText().toString();

        EditText sideRatioLimitEditText = (EditText) findViewById(R.id.edit_text_side_ratio_limit);
        refObjSideRatioLimit= sideRatioLimitEditText.getText().toString();

        EditText measObjBoundEditText = (EditText) findViewById(R.id.edit_text_meas_obj_bound);
        measObjBound = measObjBoundEditText.getText().toString();

        EditText measObjMaxBoundEditText = (EditText) findViewById(R.id.edit_text_meas_obj_max_bound);
        measObjMaxBound = measObjMaxBoundEditText.getText().toString();

        EditText measObjMaxAreaEditText = (EditText) findViewById(R.id.edit_text_meas_obj_max_area);
        measObjMaxArea = measObjMaxAreaEditText.getText().toString();

        EditText measObjMinAreaEditText = (EditText) findViewById(R.id.edit_text_meas_obj_min_area);
        measObjMinArea = measObjMinAreaEditText.getText().toString();

        if (!frameSkip.isEmpty()) {
            if (Integer.parseInt(frameSkip) >= 0) {
                intent.putExtra("frameskip", Integer.parseInt(frameSkip));
            }
        }
        if (!numberOfDilations.isEmpty()){
            if (Integer.parseInt(numberOfDilations) >= 0) {
                intent.putExtra("number_of_dilations", Integer.parseInt(numberOfDilations));
            }
        }
        if (!refObjMinContourArea.isEmpty()) {
            if (Double.parseDouble(refObjMinContourArea) >= 0) {
                intent.putExtra("refObjMinContourArea", Double.parseDouble(refObjMinContourArea));
            }
        }
        if (!refObjSideRatioLimit.isEmpty()) {
            if (Double.parseDouble(refObjSideRatioLimit) >= 0) {
                intent.putExtra("refObjSideRatioLimit", Double.parseDouble(refObjSideRatioLimit));
            }
        }
        if (!measObjBound.isEmpty()) {
            if (Integer.parseInt( measObjBound) >= 0) {
                intent.putExtra("measObjBound", Integer.parseInt( measObjBound));
            }
        }
        if (!measObjMaxBound.isEmpty()){
            if (Integer.parseInt(measObjMaxBound) >= 0) {
                intent.putExtra("measObjMaxBound", Integer.parseInt(measObjMaxBound));
            }
        }
        if (measObjMaxArea.isEmpty()) {
            if (Double.parseDouble(measObjMaxArea) >= 0) {
                intent.putExtra("measObjMaxArea", Integer.parseInt(measObjMaxArea));
            }
        }
        if (!measObjMinArea.isEmpty()) {
            if (Double.parseDouble(measObjMinArea) >= 0) {
                intent.putExtra("measObjMinArea", Integer.parseInt(measObjMinArea));
            }
        }
        if (refObjHue >= 0 && refObjHue < 179) {
            intent.putExtra("refObjHue", refObjHue);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    private void initSeekbars() {
        SeekBar hueControl = (SeekBar) findViewById(R.id.hue_bar);
        SeekBar refObjColThresholdControl = (SeekBar) findViewById(R.id.ref_obj_col_threshold_bar);
        SeekBar saturationControl = (SeekBar) findViewById(R.id.saturation_bar);
        SeekBar valueControl = (SeekBar) findViewById(R.id.value_bar);
        try {
            // Note: setting values for hueControl in settings.xml resulted in type error
            hueControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar arg0) {}
                public void onStartTrackingTouch(SeekBar arg0) {}
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    refObjHue = progress;
                    hueTextView.setText(Integer.toString(refObjHue));
                }
            });

            // Listener to receive changes to the refObjSatMinimum-progress level
            refObjColThresholdControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar arg0) {}
                public void onStartTrackingTouch(SeekBar arg0) {}
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    refObjColThreshold = progress;
                    refObjColThresholdTextView.setText(Integer.toString(refObjColThreshold));
                }
            });

            // Listener to receive changes to the refObjSatMinimum-progress level
            saturationControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar arg0) {}
                public void onStartTrackingTouch(SeekBar arg0) {}
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    refObjSatMinimum = progress;
                    saturationTextView.setText(Integer.toString(refObjSatMinimum));
                }
            });

            // Listener to receive changes to the value-progress level
            valueControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar arg0) {}
                public void onStartTrackingTouch(SeekBar arg0) {}
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    value = progress;
                    valueTextView.setText(Integer.toString(value));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTextViews(){

        // initialize TextViews
        TextView frameSkipTextView = (EditText) findViewById(R.id.edit_text_frameskip);
        TextView dilationsTextView = (EditText) findViewById(R.id.edit_text_dilations);
        TextView minContourAreaTextView = (EditText) findViewById(R.id.edit_text_min_contour_area);
        TextView sideRatioLimitTextView = (EditText) findViewById(R.id.edit_text_side_ratio_limit);
        TextView measObjBoundTextView = (EditText) findViewById(R.id.edit_text_meas_obj_bound);
        TextView measObjMaxBoundTextView = (EditText) findViewById(R.id.edit_text_meas_obj_max_bound);
        TextView measObjMaxAreaAreaTextView = (EditText) findViewById(R.id.edit_text_meas_obj_max_area);
        TextView measObjMinAreaTextView = (EditText) findViewById(R.id.edit_text_meas_obj_min_area);

        // Saturation and valueTextView need to be visible to OnSeekBarChangeListener
        hueTextView = (TextView) findViewById(R.id.hue_value);
        refObjColThresholdTextView = (TextView) findViewById(R.id.ref_obj_col_threshold_value);
        saturationTextView = (TextView) findViewById(R.id.saturation_value);
        valueTextView = (TextView) findViewById(R.id.value_value);

        // Get values from MainActivity through Intent
        frameSkip = getIntent().getStringExtra("currentFrameSkip");
        numberOfDilations = getIntent().getStringExtra("currentNumberOfDilations");
        refObjMinContourArea = getIntent().getStringExtra("currentMinContourArea");
        refObjSideRatioLimit = getIntent().getStringExtra("currentSideRatioLimit");
        measObjBound = getIntent().getStringExtra("currentMeasObjBound");
        measObjMaxBound = getIntent().getStringExtra("currentMeasObjMaxBound");
        measObjMaxArea = getIntent().getStringExtra("currentMeasObjMaxArea");
        measObjMinArea = getIntent().getStringExtra("currentMeasObjMinArea");

        refObjHue = getIntent().getIntExtra("refObjHue", 56);
        refObjColThreshold = getIntent().getIntExtra("refObjColThreshold", 12);

        // Set values to TextViews
        frameSkipTextView.setText(frameSkip);
        dilationsTextView.setText(numberOfDilations);
        minContourAreaTextView.setText(refObjMinContourArea);
        sideRatioLimitTextView.setText(refObjSideRatioLimit);
        measObjBoundTextView.setText(measObjBound);
        measObjMaxBoundTextView.setText(measObjMaxBound);
        measObjMaxAreaAreaTextView.setText(measObjMaxArea);
        measObjMinAreaTextView.setText(measObjMinArea);

        hueTextView.setText(refObjHue);
        refObjColThresholdTextView.setText(refObjColThreshold );
        saturationTextView.setText("0");
        valueTextView.setText("0");
    }
}