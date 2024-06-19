package com.example.moida.model.schedule

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class Repository(private val table : DatabaseReference){
    fun addPendingSchedule(scheduleData: ScheduleData) {
        table.child(scheduleData.scheduleId.toString()).setValue(scheduleData).addOnCompleteListener {  }
        Log.i("chrin", "addPendingSchedule: 스케줄 추가됨\n$scheduleData")
    }

    fun updateScheduleName(scheduleData: ScheduleData) {
        table.child(scheduleData.scheduleId.toString())
            .child(scheduleData.scheduleName).setValue(scheduleData.scheduleName)
    }

    fun updateScheduleDate(scheduleData: ScheduleData) {
        table.child(scheduleData.scheduleId.toString())
            .child(scheduleData.scheduleStartDate).setValue(scheduleData.scheduleStartDate)
    }

    fun updateScheduleTime(scheduleData: ScheduleData) {
        table.child(scheduleData.scheduleId.toString())
            .child(scheduleData.scheduleTime).setValue(scheduleData.scheduleTime)
    }

    fun deleteSchedule(scheduleData: ScheduleData) {
        table.child(scheduleData.scheduleId.toString()).removeValue()
    }

    fun getAllSchedules(): Flow<List<ScheduleData>> = callbackFlow {
        val listener = object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<ScheduleData>()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(ScheduleData::class.java)
                    item?.let {
                        itemList.add(it)
                    }
                }
                trySend(itemList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("chrin", "[Repository] onCancelled(1): DatabaseError")
            }
        }

        table.addValueEventListener(listener) //변경사항을 background에서 감지 중 변경 발생하면 onDataChange 불러짐
        awaitClose {
            table.removeEventListener(listener)
        }
    }

    fun getSchedule(scheduleId: String, callback: (ScheduleData) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(ScheduleData::class.java)
                    item?.let {
                        if (it.scheduleId.toString() == scheduleId) {
                            callback(it)
                            return
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("chrin", "[Repository] getSchedule onCancelled(2): DatabaseError")
            }
        }

        table.addValueEventListener(listener)
    }

    fun getUserSchedule(scheduleId: String, userName: String, callback: (List<Any>) -> Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(ScheduleData::class.java)
                    item?.let {
                        if (it.scheduleId.toString() == scheduleId) {
                            for (time in it.memberTimes!!)
                                if (userName == time[0]) {
                                    callback(time)
                                    return
                                }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("chrin", "[Repository] userSchedule onCancelled(2): DatabaseError")
            }
        }

        table.addValueEventListener(listener)
    }
}