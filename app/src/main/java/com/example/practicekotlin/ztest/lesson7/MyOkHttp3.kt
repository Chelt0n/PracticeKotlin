package com.example.practicekotlin.ztest.lesson7

class MyOkHttp3 {
    /*
    private val client = OkHttpClient()
    private val builder: Request.Builder = Request.Builder()
    builder.header(API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
    builder.url(API_URL + "lat=${localWeather.city.lat}&lon=${localWeather.city.lon}")
    val request: Request = builder.build()
    val call: Call = client.newCall(request)
    //=======================Асинхронный запрос==================================
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
        }

        override fun onResponse(call: Call, response: Response) {
            val serverResponse: String? = response.body?.string()
            if (response.isSuccessful && serverResponse != null) {
                val weatherDTO =
                    Gson().fromJson<WeatherDTO>(serverResponse, WeatherDTO::class.java)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    fillInAllFields(weatherDTO)
                }
            }
        }

    })
    //==================Синхронный запрос===================
    Thread {
         val response: Response = call.execute()
         val serverResponse: String? = response.body?.string()
         if (response.isSuccessful && serverResponse != null) {
             val weatherDTO =
                 Gson().fromJson<WeatherDTO>(serverResponse, WeatherDTO::class.java)
             val handler = Handler(Looper.getMainLooper())
             handler.post {
                 fillInAllFields(weatherDTO)
             }
         }
     }.start()

     */
}