package com.example.gmail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Email(
    val senderIcon: String,
    val senderName: String,
    val time: String,
    val subject: String,
    val preview: String
)

class EmailAdapter(private val emailList: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderIcon: TextView = itemView.findViewById(R.id.sender_icon)
        val senderName: TextView = itemView.findViewById(R.id.sender_name)
        val time: TextView = itemView.findViewById(R.id.email_time)
        val subject: TextView = itemView.findViewById(R.id.email_subject)
        val preview: TextView = itemView.findViewById(R.id.email_preview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.senderIcon.text = email.senderIcon
        holder.senderName.text = email.senderName
        holder.time.text = email.time
        holder.subject.text = email.subject
        holder.preview.text = email.preview
    }

    override fun getItemCount(): Int = emailList.size
}
