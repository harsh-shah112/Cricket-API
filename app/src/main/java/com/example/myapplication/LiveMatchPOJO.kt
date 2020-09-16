package com.example.myapplication;

data class LiveMatchPOJO (

    var matchDetail: MatchDetail

){

    data class MatchDetail(

            var unique_key : String,
            var team_1 : String,
            var team_2 : String,
            var matchStarted : Boolean

    )


}