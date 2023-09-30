package com.example.firebase_config

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class City(
    var id: String = "",
    var country: String = "",
    var city: String = "",
    var population: Int = 0,
    var area:Double=0.0,
    var sectors: ArrayList<String> = arrayListOf()
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Firebase.firestore
            .collection("cities")
            .document("db8608bc")
            .set(
                City("db8608bc", "USA", "San Francisco", 860000, 121.4, arrayListOf("Z", "A"))
            )

        Firebase.firestore
            .collection("cities")
            .document("5c5b8399")
            .set(
                City("5c5b8399","USA", "Los Angeles", 3900000,  1302.0,arrayListOf("B", "Q"))
            )

        Firebase.firestore
            .collection("cities")
            .document("26c1a9c7")
            .set(
                City("26c1a9c7","USA", "Washington, D.C", 680000, 177.0,arrayListOf("C", "W"))
            )

        Firebase.firestore
            .collection("cities")
            .document("26c1a9c7")
            .set(
                City("26c1a9c7","Japan", "Tokio", 9000000, 2187.0,arrayListOf("W", "F"))
            )

        Firebase.firestore
            .collection("cities")
            .document("e9df2cb8")
            .set(
                City("e9df2cb8","Mexico", "Ciudad de México", 8800000, 1485.0,arrayListOf("Z", "R"))
            )

        Firebase.firestore
            .collection("cities")
            .document("306f85d2")
            .set(
                City("306f85d2","China", "Beijing", 21500000, 16411.0,arrayListOf("G", "A"))
            )

        Firebase.firestore
            .collection("cities")
            .document("43dbe338")
            .set(
                City("43dbe338","Colombia", "Bogotá", 7100000, 1775.0,arrayListOf("H", "E"))
            )


        Firebase.firestore
            .collection("cities")
            .document("e9211d10")
            .set(
                City("e9211d10","Colombia", "Cali", 2200000, 564.0,arrayListOf("I", "Q"))
            )

        Firebase.firestore
            .collection("cities")
            .document("9c8d8090")
            .set(
                City("9c8d8090","Colombia", "Ibagué", 529000, 1378.0,arrayListOf("L", "M"))
            )

        Firebase.firestore
            .collection("cities")
            .document("9fef8768")
            .set(
                City("9fef8768","USA", "New York", 8300000, 468.9, arrayListOf("N", "Z"))
            )

        Firebase.firestore
            .collection("cities")
            .document("69da425e")
            .set(
                City("69da425e","Argentina", "Buenos Aires", 15100000, 203.0, arrayListOf("D", "L"))
            )

    }
}