  package com.example.kotlincalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

  class MainActivity : AppCompatActivity() {

      private var canAddOperation = false
      private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


      fun allClearAction(view: View)
      {

          resultsTV.text = ""
      }
      fun backSpaceAction(view: View)
      {
          val length = workingsTV.length()
          if(length > 0)
              workingTV.text = workingTv.text.subSequence(0, length - 1)
      }
      fun equalAction(view: View) {}

      fun numberAction(view: View)
      {
          if(view is Button){
              if (canAddDecimal)
                  workinsTV.append(view.text)
              canAddOperation = false
          }
          else
              workingTV.append(view.text)
          canAddOperation = true

          }

      fun operationalAction(view: View)
      {
          if(view is Button){
              workingsTV.append(view.text && canAddOperation)
              canAddOperation = false
          }
      }
      }
