package project3.csc214.myapp.login_register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import Database.MyDatabase;
import model.User;
import project3.csc214.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPreference extends Fragment {

    SeekBar seekBarLunch;
    SeekBar seekBarDinner;
    TextView moneyLunch;
    TextView moneyDinner;
    Button mlogin;
    User user;
    MyDatabase mcollection;
    String workday="";
    String restday="";
    String allergy ="";
    public FoodPreference() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mcollection = MyDatabase.get(getActivity().getApplicationContext());

        user = mcollection.getCurrentUser();
        View view =inflater.inflate(R.layout.fragment_food_preference, container, false);

        user.setWorkday(workday);
        user.setRestday(restday);
        user.setAllergy(allergy);
        moneyLunch = (TextView)view.findViewById(R.id.seekBarValueforL);
        seekBarLunch =(SeekBar)view.findViewById(R.id.seekBarLunch);
        seekBarLunch.setMax(200);
        seekBarLunch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progress = progress;
                moneyLunch.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(),String.valueOf(progress),Toast.LENGTH_SHORT).show();
                moneyLunch.setText(String.format("%s$", String.valueOf(progress)));
                user.setExpanseLunch(progress);
            }
        });
        seekBarDinner =(SeekBar)view.findViewById(R.id.seekBarDinner);
        moneyDinner = (TextView)view.findViewById(R.id.seekBarValueforD);
        seekBarDinner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progress = progress;
                moneyDinner.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(),String.valueOf(progress),Toast.LENGTH_SHORT).show();
                moneyDinner.setText(String.format("%s$", String.valueOf(progress)));
                user.setExpanseDinner(progress);
            }
        });
        mlogin = (Button)view.findViewById(R.id.login_food);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcollection.insertUser(user);
                FragmentManager manager =getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Login next = new Login();
                transaction.replace(R.id.login_register_page,next)
                        .commit();
            }
        });




        return view;
    }

    public void onCheckBoxClikced(View view){
        boolean checked = ((CheckBox)view).isChecked();
        switch (view.getId()){
            case R.id.checkboxMon:
                if (checked)
                    workday += "M";
                else
                    restday += "M";
                break;
            case R.id.checkboxTue:
                if (checked)
                    workday += "T";
                else
                    restday += "T";
                break;
            case R.id.checkboxWen:
                if (checked)
                    workday += "W";
                else
                    restday += "W";
                break;
            case R.id.checkboxThu:
                if (checked)
                    workday += "R";
                else
                    restday += "R";
                break;
            case R.id.checkboxFri:
                if (checked)
                    workday += "F";
                else
                    restday += "F";
                break;
            case R.id.checkboxSat:
                if (checked)
                    workday += "S";
                else
                    restday += "S";
                break;
            case R.id.checkboxSun:
                if (checked)
                    workday += "P";
                else
                    restday += "P";
                break;
            case R.id.checkboxPeanuts:
                if (checked)
                    allergy += "P";
                break;
            case R.id.checkboxDiary:
                if (checked)
                    allergy += "D";
                break;
            case R.id.checkboxSeaFood:
                if (checked)
                    allergy += "S";
                break;
            case R.id.checkboxEgg:
                if (checked)
                    allergy += "E";
                break;

        }
    }



}
