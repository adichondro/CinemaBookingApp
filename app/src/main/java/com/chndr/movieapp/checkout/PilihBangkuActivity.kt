package com.chndr.movieapp.checkout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chndr.movieapp.R
import com.chndr.movieapp.model.Checkout
import com.chndr.movieapp.model.Film
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuActivity : AppCompatActivity() {

    private var statusA1: Boolean = false
    private var statusA2: Boolean = false
    private var statusA3: Boolean = false
    private var statusA4: Boolean = false

    private var statusB1: Boolean = false
    private var statusB2: Boolean = false
    private var statusB3: Boolean = false
    private var statusB4: Boolean = false

    private var statusC1: Boolean = false
    private var statusC2: Boolean = false
    private var statusC3: Boolean = false
    private var statusC4: Boolean = false

    private var statusD1: Boolean = false
    private var statusD2: Boolean = false
    private var statusD3: Boolean = false
    private var statusD4: Boolean = false
    private var total: Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        val data = intent.getParcelableExtra<Film>("data")
        tv_kursi.text = data?.judul

        //LINE A
        a1.setOnClickListener {
            if (statusA1) {
                a1.setImageResource(R.drawable.ic_rectangle_empty)
                statusA1 = false
                total -= 1
                beliTiket(total)
            } else {
                a1.setImageResource(R.drawable.ic_rectangle_selected)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A1", "70000")
                dataList.add(data)
            }
        }

        a2.setOnClickListener {
            if (statusA2) {
                a2.setImageResource(R.drawable.ic_rectangle_empty)
                statusA2 = false
                total -= 1
                beliTiket(total)
            } else {
                a2.setImageResource(R.drawable.ic_rectangle_selected)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A2", "70000")
                dataList.add(data)
            }
        }

        a3.setOnClickListener {
            if (statusA3) {
                a3.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3 = false
                total -= 1
                beliTiket(total)
            } else {
                a3.setImageResource(R.drawable.ic_rectangle_selected)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3", "70000")
                dataList.add(data)
            }
        }

        a4.setOnClickListener {
            if (statusA4) {
                a4.setImageResource(R.drawable.ic_rectangle_empty)
                statusA4 = false
                total -= 1
                beliTiket(total)
            } else {
                a4.setImageResource(R.drawable.ic_rectangle_selected)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A4", "70000")
                dataList.add(data)
            }
        }

        //LINE B
        b1.setOnClickListener {
            if (statusB1) {
                b1.setImageResource(R.drawable.ic_rectangle_empty)
                statusB1 = false
                total -= 1
                beliTiket(total)
            } else {
                b1.setImageResource(R.drawable.ic_rectangle_selected)
                statusB1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B1", "70000")
                dataList.add(data)
            }
        }

        b2.setOnClickListener {
            if (statusB2) {
                b2.setImageResource(R.drawable.ic_rectangle_empty)
                statusB2 = false
                total -= 1
                beliTiket(total)
            } else {
                b2.setImageResource(R.drawable.ic_rectangle_selected)
                statusB2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B2", "70000")
                dataList.add(data)
            }
        }

        b3.setOnClickListener {
            if (statusB3) {
                b3.setImageResource(R.drawable.ic_rectangle_empty)
                statusB3 = false
                total -= 1
                beliTiket(total)
            } else {
                b3.setImageResource(R.drawable.ic_rectangle_selected)
                statusB3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B3", "70000")
                dataList.add(data)
            }
        }

        b4.setOnClickListener {
            if (statusB4) {
                b4.setImageResource(R.drawable.ic_rectangle_empty)
                statusB4 = false
                total -= 1
                beliTiket(total)
            } else {
                b4.setImageResource(R.drawable.ic_rectangle_selected)
                statusB4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B4", "70000")
                dataList.add(data)
            }
        }

        //LINE C
        c1.setOnClickListener {
            if (statusC1) {
                c1.setImageResource(R.drawable.ic_rectangle_empty)
                statusC1 = false
                total -= 1
                beliTiket(total)
            } else {
                c1.setImageResource(R.drawable.ic_rectangle_selected)
                statusC1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C1", "70000")
                dataList.add(data)
            }
        }

        c2.setOnClickListener {
            if (statusC2) {
                c2.setImageResource(R.drawable.ic_rectangle_empty)
                statusC2 = false
                total -= 1
                beliTiket(total)
            } else {
                c2.setImageResource(R.drawable.ic_rectangle_selected)
                statusC2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C2", "70000")
                dataList.add(data)
            }
        }

        c3.setOnClickListener {
            if (statusC3) {
                c3.setImageResource(R.drawable.ic_rectangle_empty)
                statusC3 = false
                total -= 1
                beliTiket(total)
            } else {
                c3.setImageResource(R.drawable.ic_rectangle_selected)
                statusC3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C3", "70000")
                dataList.add(data)
            }
        }

        c4.setOnClickListener {
            if (statusC4) {
                c4.setImageResource(R.drawable.ic_rectangle_empty)
                statusC4 = false
                total -= 1
                beliTiket(total)
            } else {
                c4.setImageResource(R.drawable.ic_rectangle_selected)
                statusC4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C4", "70000")
                dataList.add(data)
            }
        }

        //LINE D
        d1.setOnClickListener {
            if (statusD1) {
                d1.setImageResource(R.drawable.ic_rectangle_empty)
                statusD1 = false
                total -= 1
                beliTiket(total)
            } else {
                d1.setImageResource(R.drawable.ic_rectangle_selected)
                statusD1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D1", "70000")
                dataList.add(data)
            }
        }

        d2.setOnClickListener {
            if (statusD2) {
                d2.setImageResource(R.drawable.ic_rectangle_empty)
                statusD2 = false
                total -= 1
                beliTiket(total)
            } else {
                d2.setImageResource(R.drawable.ic_rectangle_selected)
                statusD2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D2", "70000")
                dataList.add(data)
            }
        }

        d3.setOnClickListener {
            if (statusD3) {
                d3.setImageResource(R.drawable.ic_rectangle_empty)
                statusD3 = false
                total -= 1
                beliTiket(total)
            } else {
                d3.setImageResource(R.drawable.ic_rectangle_selected)
                statusD3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D3", "70000")
                dataList.add(data)
            }
        }

        d4.setOnClickListener {
            if (statusD4) {
                d4.setImageResource(R.drawable.ic_rectangle_empty)
                statusD4 = false
                total -= 1
                beliTiket(total)
            } else {
                d4.setImageResource(R.drawable.ic_rectangle_selected)
                statusD4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D4", "70000")
                dataList.add(data)
            }
        }

        btn_home.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
                .putExtra("data", dataList)
            startActivity(intent)
        }

        iv_back.setOnClickListener {
            finish()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun beliTiket(total: Int) {
        if (total == 0){
            btn_home.text = "Beli Tiket"
            btn_home.visibility = View.INVISIBLE
        }else{
            btn_home.text = "Beli Tiket ($total)"
            btn_home.visibility = View.VISIBLE
        }
    }
}