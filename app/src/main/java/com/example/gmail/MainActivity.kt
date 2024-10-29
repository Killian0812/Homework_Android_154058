package com.example.gmail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mActionBarToolbar = findViewById<Toolbar>(R.id.toolbar)
        mActionBarToolbar.title = "Inbox"
        setSupportActionBar(mActionBarToolbar)

        val emailList = listOf(
            Email(
                "M",
                "Matt from Ionic",
                "10:42 AM",
                "The New Ionic Creator Is Here!",
                "Announcing the all-new Creator, build..."
            ),
            Email("S", "support", "10:26 AM", "Société Ovh", "Suivi de vos services..."),
            Email(
                "T",
                "Tuto.com",
                "11:04 AM",
                "8h de formation gratuite",
                "Photoshop, SEO, Blender..."
            ),
            Email(
                "C",
                "Chris Abad",
                "11:22 AM",
                "Help make Campaign Monitor better",
                "Let us know your thoughts! No Images..."
            ),
            Email(
                "E",
                "Edurila.com",
                "12:34 PM",
                "$19 Only (First 10 spots)",
                "Are you looking to learn web design..."
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EmailAdapter(emailList)
    }
}