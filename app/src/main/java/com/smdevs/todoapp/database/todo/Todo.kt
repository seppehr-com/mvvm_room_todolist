package com.smdevs.todoapp.database.todo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todos_tb")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "priority")
    var priority:Int,
    @ColumnInfo(name = "checked")
    var checked:Boolean
): Serializable
