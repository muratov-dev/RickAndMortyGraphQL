package dev.ymuratov.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.ymuratov.core.ui.R

val robotoFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
)

@OptIn(ExperimentalTextApi::class)
val nunitoSans = FontFamily(
    Font(R.font.nunitosans_variable, FontWeight.Normal, variationSettings = FontVariation.Settings(FontVariation.weight(400))),
    Font(R.font.nunitosans_variable, FontWeight.Medium, variationSettings = FontVariation.Settings(FontVariation.weight(500))),
    Font(R.font.nunitosans_variable, FontWeight.SemiBold, variationSettings = FontVariation.Settings(FontVariation.weight(600))),
)

@Immutable
data class RaMTypography(
//    val h1: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//    ),
    val titleLarge: TextStyle = TextStyle(
        fontFamily = nunitoSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
//    val h3: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 16.sp,
//        lineHeight = 22.sp,
//    ),
//    val titleLarge: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 16.sp,
//        lineHeight = 22.sp,
//    ),
//    val titleMedium: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 16.sp,
//        lineHeight = 20.sp,
//    ),
    val textMedium: TextStyle = TextStyle(
        fontFamily = nunitoSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    val textRegular: TextStyle = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
//    val bodyMedium: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 14.sp,
//        lineHeight = 20.sp,
//    ),
    val bodyRegular: TextStyle = TextStyle(
        fontFamily = nunitoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
//    val bodySmall: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp,
//        lineHeight = 16.sp,
//    ),
//    val labelSmall: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 12.sp,
//        lineHeight = 16.sp,
//    ),
//    val buttonMedium: TextStyle = TextStyle(
//        fontFamily = jetbrainsMonoFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 18.sp,
//        lineHeight = 40.sp,
//    ),
)

internal val LocalRaMTypography = staticCompositionLocalOf { RaMTypography() }