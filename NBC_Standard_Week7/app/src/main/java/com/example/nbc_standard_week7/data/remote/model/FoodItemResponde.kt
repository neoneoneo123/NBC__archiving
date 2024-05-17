package com.example.nbc_standard_week7.data.remote.model

data class B553748(
    val body: B553748Body,
    val header: B553748Header,
)

data class B553748Body(
    val items: List<B553748Items>,
    val totalCount: String,
    val pageNo: String,
    val numOfRows: String,
)

data class B553748Header(
    val resultCode: String,
    val resultMsg: String
)

data class B553748Items(
    val item: B553748Item
)

data class B553748Item(
    val prdkindstate: String?,
    val nutrient: String?,
    val manufacture: String?,
    val rnum: String?,
    val prdkind: String?,
    val rawmtrl: String?,
    val prdlstNm: String?,
    val imgurl2: String?,
    val imgurl1: String?,
    val productGb: String?,
    val prdlstReportNo: String?,
    val allergy: String?,
    val seller: String?,
    val barcode: String?,
    val capacity: String?,
)