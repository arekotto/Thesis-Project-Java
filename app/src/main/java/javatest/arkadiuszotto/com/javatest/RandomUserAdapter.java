package javatest.arkadiuszotto.com.javatest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javatest.arkadiuszotto.com.javatest.Model.RandomUser;

/**
 * @author arekotto
 */

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.ViewHolder> {

    private List<RandomUser> randomUsers;
    private Context context;

    public RandomUserAdapter(List<RandomUser> randomUsers, Context context) {
        this.randomUsers = randomUsers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RandomUser user = randomUsers.get(position);
        holder.headerTitleTextView.setText(String.format(Locale.getDefault(), "User %d", position));
        holder.nameTextView.setText(user.getName().getFirst());
        holder.lastNameTextView.setText(user.getName().getLast());
        holder.titleTextView.setText(user.getName().getTitle());
        holder.genderTextView.setText(user.getGender());
        holder.cityTextView.setText(user.getLocation().getCity());
        holder.streetTextView.setText(user.getLocation().getStreet());
    }

    @Override
    public int getItemCount() {
        return randomUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView headerTitleTextView;
        private TextView nameTextView;
        private TextView lastNameTextView;
        private TextView titleTextView;
        private TextView genderTextView;
        private TextView cityTextView;
        private TextView streetTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            headerTitleTextView = itemView.findViewById(R.id.header_title_text_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            lastNameTextView = itemView.findViewById(R.id.last_name_text_view);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            genderTextView = itemView.findViewById(R.id.gender_text_view);
            cityTextView = itemView.findViewById(R.id.city_text_view);
            streetTextView = itemView.findViewById(R.id.street_text_view);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
