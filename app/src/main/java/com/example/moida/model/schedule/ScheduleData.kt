package com.example.moida.model.schedule

data class ScheduleData(
    val scheduleId: Int,
    val scheduleStartDate: String = "",
    val scheduleTime: String = "",
    val scheduleName: String = "",
    val category: String = "",
    var memberTimes: List<List<Any>>?
) {
    constructor() : this(-1, "", "", "", "", null) // default 생성자
}