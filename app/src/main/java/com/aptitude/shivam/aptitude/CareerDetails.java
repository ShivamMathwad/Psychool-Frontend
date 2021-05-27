package com.aptitude.shivam.aptitude;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.aptitude.shivam.aptitude.Utils.Constants;

public class CareerDetails extends AppCompatActivity {
    TextView career_name, description, jobs, universities, skills;
    String str_career_name, str_description, str_jobs, str_universities, str_skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);

        init();

        str_career_name = getIntent().getStringExtra("career_name");

        switch (str_career_name) {
            case "Mathematics & Analysis":
                str_description = Constants.MathDesc;
                str_jobs = Constants.MathJobs;
                str_universities = Constants.MathUnis;
                str_skills = Constants.MathSkills;
                break;

            case "Management":
                str_description = Constants.ManagementDesc;
                str_jobs = Constants.ManagementJobs;
                str_universities = Constants.ManagementUnis;
                str_skills = Constants.ManagementSkills;
                break;

            case "Public & Political Affairs":
                str_description = Constants.PPADesc;
                str_jobs = Constants.PPAJobs;
                str_universities = Constants.PPAUnis;
                str_skills = Constants.PPASkills;
                break;

            case "Design":
                str_description = Constants.DesignDesc;
                str_jobs = Constants.DesignJobs;
                str_universities = Constants.DesignUnis;
                str_skills = Constants.DesignSkills;
                break;

            case "Medical":
                str_description = Constants.MedicalDesc;
                str_jobs = Constants.MedicalJobs;
                str_universities = Constants.MedicalUnis;
                str_skills = Constants.MedicalSkills;
                break;

            case "Weather & Environmental Sci.":
                str_description = Constants.WESDesc;
                str_jobs = Constants.WESJobs;
                str_universities = Constants.WESUnis;
                str_skills = Constants.WESSkills;
                break;

            case "Psychology":
                str_description = Constants.PsychologyDesc;
                str_jobs = Constants.PsychologyJobs;
                str_universities = Constants.PsychologyUnis;
                str_skills = Constants.PsychologySkills;
                break;

            case "Defense":
                str_description = Constants.DefenceDesc;
                str_jobs = Constants.DefenceJobs;
                str_universities = Constants.DefenceUnis;
                str_skills = Constants.DefenceSkills;
                break;

            case "Banking & Finance":
                str_description = Constants.BankingDesc;
                str_jobs = Constants.BankingJobs;
                str_universities = Constants.BankingUnis;
                str_skills = Constants.BankingSkills;
                break;

            case "Education":
                str_description = Constants.EducationDesc;
                str_jobs = Constants.EducationJobs;
                str_universities = Constants.EducationUnis;
                str_skills = Constants.EducationSkills;
                break;

            case "Mass & Media":
                str_description = Constants.MMDesc;
                str_jobs = Constants.MMJobs;
                str_universities = Constants.MMUnis;
                str_skills = Constants.MMSkills;
                break;

            case "Literature":
                str_description = Constants.LiteratureDesc;
                str_jobs = Constants.LiteratureJobs;
                str_universities = Constants.LiteratureUnis;
                str_skills = Constants.LiteratureSkills;
                break;

            case "Engineering":
                str_description = Constants.EngineeringDesc;
                str_jobs = Constants.EngineeringJobs;
                str_universities = Constants.EngineeringUnis;
                str_skills = Constants.EngineeringSkills;
                break;

            case "Travel & Tourism":
                str_description = Constants.TourismDesc;
                str_jobs = Constants.TourismJobs;
                str_universities = Constants.TourismUnis;
                str_skills = Constants.TourismSkills;
                break;

            case "Agriculture":
                str_description = Constants.AgricultureDesc;
                str_jobs = Constants.AgricultureJobs;
                str_universities = Constants.AgricultureUnis;
                str_skills = Constants.AgricultureSkills;
                break;
        }

        career_name.setText(str_career_name);
        description.setText(str_description);
        jobs.setText(str_jobs);
        universities.setText(str_universities);
        skills.setText(str_skills);
    }

    public void init() {
        career_name = findViewById(R.id.career_name);
        description = findViewById(R.id.description);
        jobs = findViewById(R.id.jobs);
        universities = findViewById(R.id.universities);
        skills = findViewById(R.id.skills);
    }

}