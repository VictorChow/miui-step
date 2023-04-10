package com.victor.miuistep

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.jaredrummler.android.shell.Shell
import com.victor.miuistep.databinding.ActivityMainBinding

class MainActivity : Activity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val shell =
        "content insert --uri content://com.miui.providers.steps/item --bind _begin_time:s:`date +%s`000 --bind _id:i:`echo ${'$'}RANDOM` --bind _end_time:s:`date +%s`999 --bind _mode:i:2 --bind _steps:i:"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStepAdd.setOnClickListener(this)
        binding.btnStepSub.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        binding.btnStepAdd.isEnabled = false
        binding.btnStepSub.isEnabled = false

        var num = binding.etStep.text.toString().toIntOrNull() ?: return
        if (v == binding.btnStepSub) num = -num
        val result = Shell.SU.run(shell + num)
        Toast.makeText(
            this,
            if (result.isSuccessful) "操作成功" else result.getStderr(),
            Toast.LENGTH_SHORT
        ).show()

        binding.btnStepAdd.isEnabled = true
        binding.btnStepSub.isEnabled = true
    }
}