package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class StudentFormActivity : AppCompatActivity() {
    private lateinit var addressHelper: AddressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_form)

        val studentId = findViewById<EditText>(R.id.student_id)
        val fullName = findViewById<EditText>(R.id.full_name)
        val genderGroup = findViewById<RadioGroup>(R.id.gender_group)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.phone)
        val calendarView = findViewById<CalendarView>(R.id.calendar_view)
        val toggleCalendarButton = findViewById<Button>(R.id.toggle_calendar_button)
        val provinceSpinner = findViewById<Spinner>(R.id.spinner_province)
        val districtSpinner = findViewById<Spinner>(R.id.spinner_district)
        val wardSpinner = findViewById<Spinner>(R.id.spinner_ward)
        val hobbySports = findViewById<CheckBox>(R.id.hobby_sports)
        val hobbyMovies = findViewById<CheckBox>(R.id.hobby_movies)
        val hobbyMusic = findViewById<CheckBox>(R.id.hobby_music)
        val termsCheckbox = findViewById<CheckBox>(R.id.terms_checkbox)
        val submitButton = findViewById<Button>(R.id.submit_button)

        addressHelper = AddressHelper(resources)

        val provinceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, addressHelper.getProvinces())
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        provinceSpinner.adapter = provinceAdapter

        provinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProvince = parent.getItemAtPosition(position).toString()
                val districtAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, addressHelper.getDistricts(selectedProvince))
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                districtSpinner.adapter = districtAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        districtSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProvince = provinceSpinner.selectedItem.toString()
                val selectedDistrict = parent.getItemAtPosition(position).toString()
                val wardAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, addressHelper.getWards(selectedProvince, selectedDistrict))
                wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                wardSpinner.adapter = wardAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        toggleCalendarButton.setOnClickListener {
            if (calendarView.visibility == View.VISIBLE) {
                calendarView.visibility = View.GONE
            } else {
                calendarView.visibility = View.VISIBLE
            }
        }

        submitButton.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Form submitted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill in all the required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        val studentId = findViewById<EditText>(R.id.student_id).text.toString().isNotEmpty()
        val fullName = findViewById<EditText>(R.id.full_name).text.toString().isNotEmpty()
        val genderSelected = findViewById<RadioGroup>(R.id.gender_group).checkedRadioButtonId != -1
        val email = findViewById<EditText>(R.id.email).text.toString().isNotEmpty()
        val phone = findViewById<EditText>(R.id.phone).text.toString().isNotEmpty()
        val termsAccepted = findViewById<CheckBox>(R.id.terms_checkbox).isChecked

        return studentId && fullName && genderSelected && email && phone && termsAccepted
    }
}