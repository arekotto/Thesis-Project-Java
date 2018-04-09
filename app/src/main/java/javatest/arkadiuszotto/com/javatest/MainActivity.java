package javatest.arkadiuszotto.com.javatest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import javatest.arkadiuszotto.com.javatest.Fragment.DrawFragment;
import javatest.arkadiuszotto.com.javatest.Fragment.RandomUserFragment;
import javatest.arkadiuszotto.com.javatest.Fragment.TestsFragment;
import javatest.arkadiuszotto.com.javatest.Fragment.WebSocketFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_tests:
                    setTitle(R.string.activity_main_title);
                    replaceFragment(TestsFragment.class);
                    return true;
                case R.id.navigation_web_sockets:
                    setTitle(R.string.title_web_sockets);
                    replaceFragment(WebSocketFragment.class);
                    return true;
                case R.id.navigation_drawing:
                    setTitle(R.string.title_drawing);
                    replaceFragment(DrawFragment.class);
                    return true;
                case R.id.navigation_json_mapping:
                    setTitle(R.string.title_random_users);
                    replaceFragment(RandomUserFragment.class);
                    return true;
            }
            return false;
        }
    };

    private void replaceFragment(Class<? extends Fragment> newFragmentClass) {
        final String tag = newFragmentClass.getSimpleName();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
            currentFragment = fragmentManager.findFragmentByTag(tag);
        }

        if (currentFragment == null) {
            try {
                currentFragment = newFragmentClass.newInstance();
                transaction.add(R.id.content, currentFragment, tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            transaction.show(currentFragment);
        }
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_tests);
    }

}
