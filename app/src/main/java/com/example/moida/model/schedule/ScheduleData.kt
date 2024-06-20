package com.example.moida.model.schedule

data class UserTime(
    val scheduleId: String,
    val userName: String,
    val time1: String,
    val time2: String,
    val time3: String,
    val time4: String,
    val time5: String,
    val time6: String,
    val time7: String,
) {
    constructor() : this("-1", "", "", "", "", "", "", "", "")
}

data class UserTime2(
    val userName: String,
    val time1: String,
    val time2: String,
    val time3: String,
    val time4: String,
    val time5: String,
    val time6: String,
    val time7: String,
){
    constructor() : this("" ,"", "", "", "", "", "", "")
}

data class ScheduleData(
    val scheduleId: Int,
    val scheduleStartDate: String = "",
    val scheduleTime: String = "",
    val scheduleName: String = "",
    val category: String = "",
//    var memberTimes: Map<String, String>?
) {
    constructor() : this(-1, "", "", "", "") // default 생성자
}