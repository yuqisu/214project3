package project3.csc214.myapp.FoodReferenceUpdate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Database.MyDatabase;
import model.User;
import project3.csc214.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodRating extends Fragment {
    private MyDatabase mcollection;
    User user;

    public FoodRating() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mcollection = MyDatabase.get(getActivity().getApplicationContext());
        user = mcollection.getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_food_rating, container, false);

        return view;
    }

}
