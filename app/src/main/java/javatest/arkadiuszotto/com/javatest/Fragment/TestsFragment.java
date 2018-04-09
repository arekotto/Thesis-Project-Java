package javatest.arkadiuszotto.com.javatest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import javatest.arkadiuszotto.com.javatest.Model.Test.PerformanceTestType;
import javatest.arkadiuszotto.com.javatest.R;
import javatest.arkadiuszotto.com.javatest.TestActivity;

/**
 * @author arekotto
 */

public class TestsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_tests, container, false);
        final Intent intent = new Intent(getActivity(), TestActivity.class);

        inflate.findViewById(R.id.button_test_array_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.LIST_ADD);
                intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 9);
                getActivity().startActivity(intent);
            }
        });

        inflate.findViewById(R.id.button_test_array_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.LIST_SORT);
                intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 7);
                getActivity().startActivity(intent);
            }
        });

        inflate.findViewById(R.id.button_test_string).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.STRINGS);
                intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 9);
                getActivity().startActivity(intent);
            }
        });

        inflate.findViewById(R.id.button_test_classes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.CLASSES);
                intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 9);
                getActivity().startActivity(intent);
            }
        });

        return inflate;
    }
}
