  package com.example.kotlincalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

  class MainActivity : AppCompatActivity() {

      private var canAddOperation = false
      private var canAddDecimal = true
      private lateinit var workingsTV : TextView
      private lateinit var resultsTV : TextView


        //associate view objects with Ids of the TextView defined in Xml
        workingsTV = findViewById(R.id.workinsTV)
        resultsTV = findViewById(R.id.resultsTV)

    }

      fun numberAction(view: View)
      {
          if(view is Button)
          {
              if (view.text == ".") {
                  if (canAddDecimal)
                      workingsTV.append(view.text)
                      canAddDecimal = false
              }
              else
                  workingsTV.append(view.text)
                  canAddOperation = true
          }
      }

          fun operationAction(view: View)
          {
              if(view is Button && canAddOperation)
              {
                  workingsTV.append(view.text)
                  canAddOperation = false
                  canAddDecimal = true
              }
          }

      fun allClearAction(view: View) {
        workingsTV.text = ""
        resultsTV.text = ""
      }

      fun backSpaceAction(view: View) {
          val length = workingsTV.length()
          if (length > 0)
              workingsTV.text = workingsTV.text.subSequence(0, length -1)

      }

      fun equalsAction(view: View) {}
  }


