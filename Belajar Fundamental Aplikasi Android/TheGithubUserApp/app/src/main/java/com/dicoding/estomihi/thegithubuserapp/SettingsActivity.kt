package com.dicoding.estomihi.thegithubuserapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.dicoding.estomihi.thegithubuserapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_NAME = "reminder_preference"
        private const val IS_REMINDER_ACTIVATED = "is_reminder"
    }

    private lateinit var binding : ActivitySettingsBinding
    private lateinit var alarmReceiver: AlarmReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        alarmReceiver = AlarmReceiver()

        val reminderSharedPreferences: SharedPreferences =
            this.getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )

        binding.switchRemainder.isChecked =
            reminderSharedPreferences.getBoolean(IS_REMINDER_ACTIVATED, false)


        binding.switchRemainder.setOnClickListener {
            if(binding.switchRemainder.isChecked) {
                val editor = reminderSharedPreferences.edit()
                editor.putBoolean(IS_REMINDER_ACTIVATED, true)
                editor.apply()
                binding.switchRemainder.isChecked = true

                val messageRemind = getString(R.string.message_reminder)
                alarmReceiver.setRepeatingAlarm(this, messageRemind)
            } else {
                val editor = reminderSharedPreferences.edit()
                editor.putBoolean(IS_REMINDER_ACTIVATED, false)
                editor.apply()
                binding.switchRemainder.isChecked = false
                alarmReceiver.cancelReminder(this)
            }
        }

        binding.btnLanguange.setOnClickListener {
            Intent(Settings.ACTION_LOCALE_SETTINGS)
                .also {
                startActivity(it)
            }
        }

    }
}