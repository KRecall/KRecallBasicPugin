package io.github.octestx.krecall.plugins.basic.model

import org.ktorm.entity.Entity

interface WaitingProcess : Entity<WaitingProcess> {
    companion object : Entity.Factory<WaitingProcess>()
    //Auto increment id
    val id: Int
    val timestamp: Long
    val duration: Long
    //Specify the processor of the data (find the only processor that can handle the type)
    val type: String
    // extra data
    val data1: String
    val data2: String
    val data3: String
    val data4: String
    val data5: String
    val data6: String
    val data7: String
    val data8: String
    val data9: String
    val data10: String
    val data11: String
    val data12: String
    val data13: String
    val data14: String
    val data15: String
    val data16: String
    val data17: String
    val data18: String
    val data19: String
    val data20: String
}