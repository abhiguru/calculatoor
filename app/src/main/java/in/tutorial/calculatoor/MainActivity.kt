package `in`.tutorial.calculatoor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView? = null;
    private var lastNumeric : Boolean = false;
    var lastDot:Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tv_input)
    }
    fun onDigit(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }
    fun onClear(view: View){
        tvInput?.text = "";
    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view:View){
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString();
            var prefix = ""
            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitVal = tvValue.split("-")

                    var one = splitVal[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitVal[1]
                    var result = one.toDouble() - two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }else if(tvValue.contains("+")){
                    val splitVal = tvValue.split("+")

                    var one = splitVal[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitVal[1]
                    var result = one.toDouble() + two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }else if(tvValue.contains("*")){
                    val splitVal = tvValue.split("*")

                    var one = splitVal[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitVal[1]
                    var result = one.toDouble() * two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }else if(tvValue.contains("/")){
                    val splitVal = tvValue.split("/")

                    var one = splitVal[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitVal[1]
                    var result = one.toDouble() / two.toDouble()
                    tvInput?.text = removeZeroAfterDot(result.toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDot(res:String): String{
        var value = res
        if(res.contains(".0"))
            return res.substring(0, res.length-2)
        return res;
    }
    private fun isOperatorAdded(value:String):Boolean{
        if(value.startsWith("-")){
            val str = value.drop(1);
            if(str.contains("/") || str.contains("*") ||
                str.contains("+") || str.contains("-")) {
                return true
            }
            return false
        }else if(value.contains("/") || value.contains("*") ||
                 value.contains("+") || value.contains("-")) {
            return true
        }
        return false;
    }
}