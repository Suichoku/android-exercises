package com.example.golfcourseswishlist

class Places {

    companion object {

        var placeNameArray = arrayOf(
            "Black Mountain",
            "Chambers Bay",
            "Clear Water",
            "Harbour Town",
            "Muirfield",
            "Old Course",
            "Pebble Beach",
            "Spy Class",
            "Turtle Bay"
        )

        fun placeList(): ArrayList<Place> {
            val list = ArrayList<Place>()
            for (i in placeNameArray.indices) {
                list.add(Place(
                    name = placeNameArray[i],
                    image = placeNameArray[i].replace("\\s+".toRegex(), "").toLowerCase()
                ))
            }
            return list
        }
    }
}