package com.opazoweb.studynomic3.ui.summery

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.calculators.CSNCalculator
import com.opazoweb.studynomic3.data.calculators.WorkCalculator
import com.opazoweb.studynomic3.data.csn.CsnMaxIncome
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.taxes.TaxTable
import com.opazoweb.studynomic3.ui.MainActivity
import kotlinx.android.synthetic.main.summery_fragment.*

class SummeryFragment : Fragment() {

    companion object {
        fun newInstance() = SummeryFragment()
    }

    private lateinit var mainActivity: MainActivity
    private lateinit var municipalityMap:MutableMap<String, Municipality>
    private lateinit var taxTableMap:MutableMap<String, TaxTable>
    private lateinit var csnMaxIncomeMap:MutableMap<Int, CsnMaxIncome>
    private lateinit var sharedpref: SharedPreferences
    private var isChurch:Boolean = false
    private lateinit var workCalculator:WorkCalculator
    private lateinit var csnCalculator: CSNCalculator

    private lateinit var viewModel: SummeryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SummeryViewModel::class.java)

        lateinitAndVars()

        summery()
    }

    private fun lateinitAndVars() {

        mainActivity = (activity as MainActivity)
        municipalityMap = mainActivity.getMunicipalityMap()
        taxTableMap = mainActivity.getTaxTableMap()
        csnMaxIncomeMap = mainActivity.getCsnMaxIncomeMap()
        sharedpref = PreferenceManager.getDefaultSharedPreferences(context)
        isChurch = sharedpref.getBoolean("CHURCH_FEE", false)

        workCalculator = WorkCalculator(municipalityMap, taxTableMap, sharedpref)
        csnCalculator = CSNCalculator(csnMaxIncomeMap)
    }

    private fun summery() {

        val dateFrom = sharedpref.getString("START_DATE", "20190902")!!
        val dateTo = sharedpref.getString("END_DATE", "20191231")!!

//        val dateFromTo:String = "$dateFrom - $dateTo"
        val municipality:String = sharedpref.getString("YOUR_RESIDENT", "STOCKHOLM")!!.toString()
        val income = sharedpref.getString("FULLTIME_PAY", "0")!!.toInt()
        val incomeAfterTaxText = workCalculator.payAfterTaxes(income, municipality, isChurch) + " Kr"
        val leavePref = sharedpref.getString("WORK_LEAVE", "100")!!
        val leave = leavePref.replace(",", ".").toDouble()
        val leaveText = "$leave%"
        val incomeAfterTaxLeaveText = workCalculator.payAfterTaxes(income, municipality, isChurch, leave) + " Kr"
        val weeks = csnCalculator.studieInWeeks(dateFrom, dateTo)
        val studyDateText = "$weeks Weeks"
        val pace = sharedpref.getString("YOUR_STUDY_PACE", "100")!!.toInt()
        val maxIncome = csnCalculator.maxIncome(weeks, pace).toString() + " Kr"

        textviewSummeryMunicipality.text = municipality
//        Work Text

        val taxTable = workCalculator.taxTable(municipality, isChurch)
        textviewSummeryTaxTable.text = taxTable
        textviewSummeryYourPay.text = incomeAfterTaxText
        textviewSummeryYourLeave.text = leaveText
        textviewSummeryYourPayStudent.text = incomeAfterTaxLeaveText

//        Study Text
        textviewSummeryStudyDate.text = studyDateText
        textviewSummeryStudyMaxIncome.text = maxIncome

        val paceText = "$pace %"
        textviewSummeryStudyPace.text = paceText

//        Conclusion
        val totalPay = workCalculator.totalPay(weeks, income, leave).toString()
        textviewSummeryIncome.text = totalPay

        val left = ((csnCalculator.maxIncome(weeks, pace) - totalPay.toInt()) / (weeks / 4.5)).toInt()
        textviewSummeryLeft.text = left.toString()
    }

}
