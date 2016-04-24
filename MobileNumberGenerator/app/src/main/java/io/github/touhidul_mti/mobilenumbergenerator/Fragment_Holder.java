package io.github.touhidul_mti.mobilenumbergenerator;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Fragment_Holder extends AppCompatActivity implements Communicator{

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment__holder);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Game_Fragment games = new Game_Fragment();

        fragmentTransaction.add(R.id.holder_rl1, games, "gamesTag");
        fragmentTransaction.commit();
    }
    public void communicateResponse(){

        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        Fragment_Result results = new Fragment_Result();
        fragmentTransaction2.replace(R.id.holder_rl1, results,"resultsTag");

        fragmentTransaction2.commit();
    }
}
