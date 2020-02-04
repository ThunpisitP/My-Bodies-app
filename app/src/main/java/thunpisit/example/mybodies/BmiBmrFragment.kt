package thunpisit.example.mybodies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment


class BmiBmrFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var genderSelected : String? = "male"
        var activitySelected : Int? = 0
        val view = inflater.inflate(R.layout.fragment_bmi_bmr,container,false)

        //input text
        val ageInput = view.findViewById<EditText>(R.id.ageInput)
        val heightInput = view.findViewById<EditText>(R.id.heightInput)
        val weightInput = view.findViewById<EditText>(R.id.weightInput)
        //c-input text

        //store calculated value
        var bmiValue : String? = null
        var bmrValue : Double? = null
        //c-store calculated value

        //radio button
        val radio = view.findViewById<RadioGroup>(R.id.radioGroup)
        radio.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.male -> genderSelected = "male"
                R.id.female -> genderSelected = "female"
            }
        }
        //c-radio button

        //spinner
        val option = view.findViewById<Spinner>(R.id.activityList)
        val activityOptions = arrayOf("little or no exercise",
            "light exercise or sports 1-3 days/week",
            "moderate exercise 3-5 days/week",
            "hard exercise 6-7 days/week",
            "very hard exercise and a physical job")
        option.adapter = ArrayAdapter<String>(view.context,android.R.layout.simple_spinner_dropdown_item,activityOptions)
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(view.context,"please select from option",Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    activitySelected = option.selectedItemPosition
                }
            }
        }
        //c-spinner

        //clickable text
        val resultPress = view.findViewById<TextView>(R.id.resultPress)
        resultPress.setOnClickListener{
            try{
                if(stringNotEmpty(ageInput) && stringNotEmpty(heightInput) && stringNotEmpty(weightInput)){
                    bmiValue = onCalculateBMI(heightInput.text.toString(),weightInput.text.toString())
                    bmrValue = onCalculateBMR(ageInput.text.toString(),heightInput.text.toString(),weightInput.text.toString(),genderSelected,activitySelected)

                    Toast.makeText(view.context,"BMI : $bmiValue\nBMR : $bmrValue Calories/day",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(view.context,"input invalid",Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                Toast.makeText(view.context,"Exception : ${e.message}",Toast.LENGTH_SHORT).show()
            }
        }
        //c-clickable text

        return view
    }
    private fun stringNotEmpty(editText: EditText): Boolean {
        var sText = editText.text.toString()
        if (sText.isNullOrEmpty()||sText.isNullOrBlank()){
            return false
        }
        return true
    }

    private fun onCalculateBMI(height:String,weight:String) : String?{
        var result : Double? = null
        result = weight.toDouble()/((height.toDouble()/100)*(height.toDouble()/100))
        if(result < 15){
            return "Very severely underweight"
        }else if(result>=15 && result<16){
            return "Severely underweight"
        }else if(result>=16 && result<18.5){
            return "Underweight"
        }else if(result>=18.5 && result<25){
            return "Normal (healthy weight)"
        }else if(result>=25 && result<30){
            return "Overweight"
        }else if(result>=30 && result<35){
            return "Moderately obese"
        }else if(result>=35 && result<40){
            return "Severely obese"
        }else if(result>=40){
            return "Very severely obese"
        }
        return "$result"
    }

    private fun onCalculateBMR(age:String,height:String,weight:String,gender:String?,activityFactor:Int?): Double?{
        var result : Double? =null
        when(gender){
            "male" -> {
                result = 66.47 + (13.75 * weight.toDouble() + (5.003 * height.toDouble()) - (6.755 * age.toDouble()))
            }
            "female" -> {
                result = 665.1 + (9.563 * weight.toDouble() + (1.85 * height.toDouble()) - (4.676 * age.toDouble()))
            }
            else -> Toast.makeText(view?.context,"invalid input",Toast.LENGTH_SHORT).show()
        }

        when(activityFactor){
            0 -> result = result!! * 1.2
            1 -> result = result!! * 1.375
            2 -> result = result!! * 1.55
            3 -> result = result!! * 1.725
            4 -> result = result!! * 1.9
            else -> Toast.makeText(view?.context,"invalid input",Toast.LENGTH_SHORT).show()
        }
        return String.format("%.2f",result).toDouble()
    }
}
























