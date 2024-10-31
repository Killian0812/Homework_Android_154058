package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentListActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerViewStudents: RecyclerView
    private lateinit var searchView: SearchView

    private val studentList = listOf(
        Student("Nguyen Manh Cuong", "20210144"),
        Student("Nguyen Manh Hung", "20210102"),
        Student("Nguyen Thanh Lam", "20210103"),
        Student("Trinh Hong Phuong", "20210104"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        recyclerViewStudents = findViewById(R.id.recyclerViewStudents)
        searchView = findViewById(R.id.searchView)

        recyclerViewStudents.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        recyclerViewStudents.adapter = studentAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length > 2) {
                    filter(newText)
                } else {
                    studentAdapter.updateList(studentList)
                }
                return true
            }
        })
    }

    private fun filter(query: String) {
        val filteredList = studentList.filter {
            it.name.contains(query, ignoreCase = true) || it.studentId.contains(query)
        }
        studentAdapter.updateList(filteredList)
    }
}

data class Student(val name: String, val studentId: String)
