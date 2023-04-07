package com.example.recyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewapp.databinding.ActivityCountryCodeBinding

class CountryCodeActivity : AppCompatActivity() {

    var name =ArrayList<String>()
    var code=ArrayList<Int>()
    var country= ArrayList<CountryModelClass>()
    lateinit var binding:ActivityCountryCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCountryCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        view()
    }

    private fun view() {
        settingdata()

        for(i in 0..code.size-1)
        {
            var modelclass= CountryModelClass(code[i],name[i])
            country.add(modelclass)
        }

        var countryAdapter=CountryCodeAdapter(country,{code,name ->
            var intent=Intent(this@CountryCodeActivity,CountryShowActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        })

        var manager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rcvCountry.layoutManager=manager
        binding.rcvCountry.adapter=countryAdapter

    }

    private fun settingdata() {

        code.add(+91)
        code.add(+61)
        code.add(+44)
        code.add(+27)
        code.add(+880)
        code.add(+1)
        code.add(+93)
        code.add(+975)
        code.add(+57)

        name.add("INDIA")
        name.add("AUSTRALIA")
        name.add("UNITED KINGDOM")
        name.add("SOUTH AFRICA")
        name.add("BANGLADESH")
        name.add("CANADA")
        name.add("AFGHANISTAN")
        name.add("BHUTAN")
        name.add("COLOMBIA")

    }
}