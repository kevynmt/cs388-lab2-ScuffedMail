package com.example.scuffedmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var emails : List<Email>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create views
        var emailsRv = findViewById<RecyclerView>(R.id.emailsRv)        //Create view for the emails recycler view
        var loadMoreButton = findViewById<Button>(R.id.loadMoreButton)  //Create view for the load more button

        var emails = EmailFetcher.getEmails()                           //Get the emails and save them to emails
        val adapter = EmailAdapter(emails)                              //Create an adapter for the list of emails
        emailsRv.adapter = adapter                                      //Attach the email adapter to the recycler view
        emailsRv.layoutManager = LinearLayoutManager(this)       //Set layout manager to format the emails

        //onClickListener for the load more button: loads the next five emails in the list
        loadMoreButton.setOnClickListener {
            //Fetch the next five emails
            val newEmails = EmailFetcher.getNext5Emails()

            //Add newEmails to the end of the list of existing emails
            emails.addAll(newEmails)

            //Notify the adapter that there are new emails so the layout gets updated
            adapter.notifyDataSetChanged()
        }
    }
}