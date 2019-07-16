package com.opazoweb.studynomic3.data.taxes

class TaxTableRow(
    val name: String,
    var payFrom: Int,
    var payTo: Int,
    var taxInSwedishKr: Int
)
{
    fun doesItFit (income: Int):Boolean {
        var isIt: Boolean = false
        if (payFrom <= income){
            isIt = payTo >= income
        } else {
            isIt = false
        }
        return isIt
    }
}
