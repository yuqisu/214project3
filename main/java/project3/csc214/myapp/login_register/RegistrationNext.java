package project3.csc214.myapp.login_register;


import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import Database.MyDatabase;
import model.User;
import project3.csc214.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationNext extends Fragment {

    private MyDatabase mcollection;
    private Button mprofile;
    private EditText mName;
    private TextView mbirthday;
    private Button mbirthdayPicker;
    private EditText mTown;
    private EditText mBio;
    private Button mpreference;
    User user;
    public RegistrationNext() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mcollection = MyDatabase.get(getActivity().getApplicationContext());
        user = mcollection.getCurrentUser();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_next, container, false);
        mprofile = (Button)view.findViewById(R.id.register_picture);
        mprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mName = (EditText) view.findViewById(R.id.register_name);
        mbirthday = (TextView) view.findViewById(R.id.regirster_bithday);
        mbirthdayPicker = (Button)view.findViewById(R.id.regirster_bithday_pick);
        mbirthdayPicker.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int date = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mbirthday.setText(dayOfMonth+" - "+(month+1)+" "+year);
                                user.setBirthday(dayOfMonth+" - "+(month+1)+" "+year);

                            }
                        },year,month,date);
                datePickerDialog.show();



            }

        });
        mTown = (EditText) view.findViewById(R.id.register_town);
        mBio = (EditText) view.findViewById(R.id.register_bio);
        mpreference = (Button)view.findViewById(R.id.login_in_next_register);
        mpreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mName.getText().toString();
                user.setFullname(name);
                String town =mTown.getText().toString();
                user.setHometown(town);
                String bio = mBio.getText().toString();
                user.setBio(bio);
                FragmentManager manager =getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                FoodPreference preference = new FoodPreference();
                transaction.replace(R.id.login_register_page,preference)
                        .commit();


            }
        });
        return view;
    }

//    public void updateInformation(String name, Date birth,String filePath,String town, String bio){
//        mcollection.updateUser(mcollection.getLatestUser(),name,birth,filePath,town,bio);
//    }


}