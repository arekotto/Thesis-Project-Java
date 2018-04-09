package javatest.arkadiuszotto.com.javatest.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javatest.arkadiuszotto.com.javatest.Model.RandomUser;
import javatest.arkadiuszotto.com.javatest.R;
import javatest.arkadiuszotto.com.javatest.RandomUserAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RandomUserFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<RandomUser> randomUsers = new ArrayList<>();

    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_random_user, container, false);
        recyclerView = inflate.findViewById(R.id.random_user_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        requestQueue = Volley.newRequestQueue(getActivity());

        inflate.findViewById(R.id.new_user_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchNewUser();
            }
        });

        inflate.findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomUsers.clear();
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new RandomUserAdapter(randomUsers, getActivity());
        recyclerView.setAdapter(adapter);
        return inflate;
    }

    private void fetchNewUser() {
        String url = "https://randomuser.me/api/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        RandomUserResponse parsedResponse = new Gson().fromJson(response, RandomUserResponse.class);
                        if (parsedResponse == null) {
                            return;
                        }
                        List<RandomUser> userResponse = parsedResponse.getResults();
                        if (userResponse != null && !userResponse.isEmpty()) {
                            randomUsers.add(userResponse.get(0));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showNoConnectionToast();
            }
        });
        requestQueue.add(stringRequest);
    }

    private class RandomUserResponse {
        @SerializedName("results")
        private List<RandomUser> results;

        public List<RandomUser> getResults() {
            return results;
        }
    }

    private void showNoConnectionToast() {
        Context context = getActivity().getApplicationContext();
        Toast.makeText(context, R.string.no_connection, Toast.LENGTH_SHORT).show();
    }

}
