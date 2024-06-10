package com.example.opsc7311poepart2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AccountsActivity : AppCompatActivity(), OnGoalReachedListener {

    private lateinit var categoriesNavigationButton: Button
    private lateinit var tasksNavigationButton: Button
    private lateinit var timeSheetsNavigationButton: Button
    private lateinit var logHoursNavigationButton: Button
    private lateinit var accountsNavigationButton: Button
    private lateinit var homeNavigationButton: ImageButton
    private lateinit var levelTextView: TextView
    private lateinit var experienceProgressBar: ProgressBar

    private var currentExperience = 80
    private var currentLevel = 1
    private val experienceToLevelUp = 100 // Assuming each level requires 100 experience points

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accounts)

        categoriesNavigationButton = findViewById(R.id.categoriesNavigationButton)
        tasksNavigationButton = findViewById(R.id.tasksNavigationButton)
        timeSheetsNavigationButton = findViewById(R.id.timeSheetsNavigationButton)
        logHoursNavigationButton = findViewById(R.id.logHoursNavigationButton)
        accountsNavigationButton = findViewById(R.id.accountsNavigationButton)
        homeNavigationButton = findViewById(R.id.homeNavigationButton)

        levelTextView = findViewById(R.id.lvlTxt)
        experienceProgressBar = findViewById(R.id.progressBarExperience)

        // Initialize level and experience
        currentExperience = 80
        currentLevel = 1
        updateLevelAndExperience(0) // This will set the initial values correctly

        categoriesNavigationButton.setOnClickListener { navigateToCategories() }
        tasksNavigationButton.setOnClickListener { navigateToTasks() }
        timeSheetsNavigationButton.setOnClickListener { navigateToTimeSheets() }
        logHoursNavigationButton.setOnClickListener { navigateToLogHours() }
        accountsNavigationButton.setOnClickListener { navigateToAccounts() }
        homeNavigationButton.setOnClickListener{ navigateToHome() }

        (applicationContext as? DashboardActivity)?.setOnGoalReachedListener(this)
    }

    private fun navigateToCategories() {
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToTasks() {
        val intent = Intent(this, TasksActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToTimeSheets() {
        val intent = Intent(this, TimeSheetsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLogHours() {
        val intent = Intent(this, LogHoursActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAccounts() {
        val intent = Intent(this, AccountsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    override fun onGoalReached(experienceEarned: Int) {
        updateLevelAndExperience(experienceEarned)
    }

    fun updateLevelAndExperience(experienceEarned: Int) {
        currentExperience += experienceEarned
        while (currentExperience >= experienceToLevelUp) {
            currentExperience -= experienceToLevelUp
            currentLevel++
        }

        levelTextView.text = "Level $currentLevel"
        experienceProgressBar.progress = (currentExperience * 100) / experienceToLevelUp
    }
}
