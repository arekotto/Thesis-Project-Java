package javatest.arkadiuszotto.com.javatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import javatest.arkadiuszotto.com.javatest.Model.Test.ClassesTest;
import javatest.arkadiuszotto.com.javatest.Model.Test.ListSortTest;
import javatest.arkadiuszotto.com.javatest.Model.Test.PerformanceTest;
import javatest.arkadiuszotto.com.javatest.Model.Test.ListAddTest;
import javatest.arkadiuszotto.com.javatest.Model.Test.PerformanceTestType;
import javatest.arkadiuszotto.com.javatest.Model.Test.StringsTest;

public class TestActivity extends AppCompatActivity {

    public static final String PERFORMANCE_TEST_TYPE_KEY = "PERFORMANCE_TEST_TYPE";
    public static final String PERFORMANCE_TEST_ITERATIONS_KEY = "PERFORMANCE_TEST_ITERATIONS";


    public static final Integer DEFAULT_NUMBER_OF_ITERATIONS = 1000000;


    private TextView resultDescriptionTextView;
    private TextView resultTextView;

    private EditText iterationsTextField;

    private PerformanceTest performanceTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switch ((PerformanceTestType) getIntent().getSerializableExtra(PERFORMANCE_TEST_TYPE_KEY)) {
            case LIST_ADD:
                performanceTest = new ListAddTest();
                setTitle(R.string.activity_test_title_list_add);
                break;
            case LIST_SORT:
                performanceTest = new ListSortTest();
                setTitle(R.string.activity_test_title_list_sort);
                break;
            case STRINGS:
                performanceTest = new StringsTest();
                setTitle(R.string.activity_test_title_strings);
                break;
            case CLASSES:
                performanceTest = new ClassesTest();
                setTitle(R.string.activity_test_title_class);
                break;
        }


        resultDescriptionTextView = findViewById(R.id.text_view_result_description);
        resultTextView = findViewById(R.id.text_view_result);

        iterationsTextField = findViewById(R.id.text_field_iterations);

        int maxNumberOfIterations = getIntent().getIntExtra(PERFORMANCE_TEST_ITERATIONS_KEY, 9);

        iterationsTextField.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxNumberOfIterations)});

        resultDescriptionTextView.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);


        findViewById(R.id.button_run_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runTest();
            }
        });
        findViewById(R.id.button_default_iterations).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iterationsTextField.setText(String.valueOf(DEFAULT_NUMBER_OF_ITERATIONS));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void runTest() {
        String text = iterationsTextField.getText().toString();
        if (!isNumeric(text)) {
            return;
        }
        Integer iterations = Integer.parseInt(text);
        double testResult = performanceTest.run(iterations);
        resultTextView.setText(String.format(Locale.getDefault(), "%.6f", testResult));
        resultDescriptionTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
    }

    private boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
