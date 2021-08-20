package com.victor.miuistep

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.jaredrummler.android.shell.Shell
import com.victor.miuistep.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding
    private val shell =
        "content insert --uri content://com.miui.providers.steps/item --bind _begin_time:s:`date +%s`000 --bind _id:i:`echo ${'$'}RANDOM` --bind _end_time:s:`date +%s`999 --bind _mode:i:2 --bind _steps:i:"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStep.setOnClickListener {
            val num = binding.etStep.text.toString().toIntOrNull() ?: return@setOnClickListener
            Shell.SU.run(shell + num)
            Toast.makeText(this, "操作成功", Toast.LENGTH_SHORT).show()
        }
    }
}