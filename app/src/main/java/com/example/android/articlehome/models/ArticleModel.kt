package com.example.android.articlehome.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "article_table")
@Parcelize
data class ArticleModel(@PrimaryKey val id:String, val sectionId:String, val sectionName:String,
                        val webPublicationDate: String,
                        val webTitle:String, val webUrl:String,
                        val apiUrl:String): Parcelable
