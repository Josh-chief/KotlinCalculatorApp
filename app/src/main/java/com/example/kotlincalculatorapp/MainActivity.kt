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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //associate view objects with Ids of the TextView defined in Xml
        workingsTV = findViewById(R.id.workingTV)
        resultsTV = findViewById(R.id.resultsTV)
3
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

      fun equalsAction(view: View) {
          resultsTV.text= calculateResults()
      }

      private fun calculateResults(): String
      {
        val digitsOperators = digitsOperators()
          if(digitsOperators.isEmpty())return  ""

          val timesDivision = timesDivisionCalculate(digitsOperators)
          if(timesDivision.isEmpty())return  ""

          val results = addSubtractCalculate(timesDivision)
          return  results.toString()
      }

      private fun addSubtractCalculate(passedList: MutableList<Any>): Float
      {
            var result = passedList[0] as Float

            for (i in passedList.indices)
            {
                if (passedList[i] is Char && i != passedList.lastIndex)
                {
                   var operator = passedList[i]
                   var nextDigit = passedList[i + 1] as Float
                    if(operator == '+')
                        result += nextDigit
                    if(operator == '-')
                        result -= nextDigit

                }

            }
            return result

      }

      private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
          var List = passedList
            while (List.contains('x')|| List.contains('/'))
            {
               List = calcTimesDiv(List)
            }
          return List
      }

      private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any>
      {
        val newList = mutableListOf<Any>()

          var restartIndex = passedList.size
          for (i in passedList.indices)
          {
              if (passedList[i] is Char && i !=passedList.lastIndex && i < restartIndex)
              {
                  val operator = passedList[i]
                  val prevDigit = passedList[i - 1] as Float
                  val nextDigit = passedList[i + 1] as Float

                  when(operator)
                  {
                      'x' ->
                      {
                          newList.add(prevDigit * nextDigit)
                          restartIndex = i + 1
                      }
                      '/' ->
                      {
                          newList.add(prevDigit / nextDigit)
                          restartIndex = i + 1
                      }
                      else ->
                      {
                        newList.add(prevDigit)
                        newList.add(operator)
                      }
                  }
              }
              if(i > restartIndex)
                  newList.add(passedList[i])
          }
          return newList
      }

      private fun digitsOperators():MutableList<Any>
    {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (charater in workingsTV.text)
        {
            if(charater.isDigit() || charater == '.')
                currentDigit += charater
            else
            {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(charater)

            }
        }

        if (currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }
  }






