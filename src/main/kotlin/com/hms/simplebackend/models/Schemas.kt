package com.hms.simplebackend.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class Auther(@Id val id:String? = null, val name:String, val birthDate:LocalDate)

@Document
data class Book(@Id val id:String? = null, val name:String)