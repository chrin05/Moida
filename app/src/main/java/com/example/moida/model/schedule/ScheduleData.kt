package com.example.moida.model.schedule

data class ScheduleData(
    val scheduleId: Int,
    val scheduleStartDate: String = "",
    val scheduleTime: String = "",
    val scheduleName: String = "",
    val category: String = "",
    var memberTimes: Map<String, Map<String, List<Int>>>? //user1: {time1: [0,0...], time2: [0,0..] ..}
) {
    constructor() : this(-1, "", "", "", "", null) // default 생성자
}