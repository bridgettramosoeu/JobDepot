package com.example.ankit.job_depot.employer.view;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ankit.job_depot.R;
import com.example.ankit.job_depot.candidate.view.JobLoacationFragment;
import com.example.ankit.job_depot.employer.model.DAO.EmployerHistory;
import com.parse.ParseObject;

import java.util.Calendar;
import java.util.List;

public class SearchCandidates extends Fragment {

    String keyword;
    Button SearchButton;
    ImageButton SearchImageButton;
    EditText SearchField, SearchLoc, SearchSkills, SearchExp;
    String employerName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View jobsView = inflater.inflate(R.layout.fragment_search_candidates, container, false);
        SearchField = (EditText)jobsView.findViewById(R.id.editTextSearch);
        SearchLoc = (EditText)jobsView.findViewById(R.id.editTextSearchByLocation);
        SearchSkills = (EditText)jobsView.findViewById(R.id.editTextSearchSkills);
        SearchExp = (EditText)jobsView.findViewById(R.id.editTextSearchExp);
        SearchButton = (Button)jobsView.findViewById(R.id.buttonSearch);
        SearchImageButton = (ImageButton)jobsView.findViewById(R.id.imageButtonSearch);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", getActivity().MODE_PRIVATE);
        employerName = sharedPreferences.getString("employerName", "ankitb");

        SearchButton.setOnClickListener(onClickListener);
        SearchImageButton.setOnClickListener(onClickListener);



        return jobsView;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.imageButtonSearch:
                case R.id.buttonSearch:
                    Log.d("ABhartha", SearchField.getText().toString());
                    Log.d("ABhartha", SearchLoc.getText().toString());
                    Log.d("ABhartha", SearchSkills.getText().toString());
                    Log.d("ABhartha", SearchExp.getText().toString());
                    Log.d("ABhartha",  "" + SearchExp.getText().toString().equals(""));
                    if(SearchField.getText().toString().equals("") && SearchLoc.getText().toString().equals("")
                            && SearchSkills.getText().toString().equals("")  && SearchExp.getText().toString().equals("") ) {
                        Toast.makeText(getActivity(),
                                "Enter some keywords you want to search",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        if(!(SearchField.getText().toString().equals(""))) {
                            keyword = (SearchField.getText().toString());
                        }
                        if(!(SearchLoc.getText().toString().equals(""))) {
                            keyword = (SearchLoc.getText().toString().toLowerCase());
                        }
                        if(!(SearchSkills.getText().toString().equals("") )){
                            keyword = (SearchSkills.getText().toString().toLowerCase());
                        }
                        if(!(SearchExp.getText().toString().equals(""))){
                            keyword = (SearchExp.getText().toString().toLowerCase());
                        }
                        EmployerHistory candidateList = new EmployerHistory();
                        List<ParseObject> o = candidateList.getCandidates(keyword);

                        CandidateList newFragment =  new CandidateList(o);
                        android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.searchcandidatefragment, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    }
            }
        }
    };
}