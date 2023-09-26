package res

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

object Font {
    val montserrat = FontFamily(
        Font(
            resource = "fonts/montserrat/semibold.ttf",
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/black.ttf",
            FontWeight.Black,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/blackitalic.ttf",
            FontWeight.Black,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/bold.ttf",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/bolditalic.ttf",
            FontWeight.Bold,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/extrabold.ttf",
            FontWeight.ExtraBold,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/extrabolditalic.ttf",
            FontWeight.ExtraBold,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/extralight.ttf",
            FontWeight.ExtraLight,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/extralightitalic.ttf",
            FontWeight.ExtraLight,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/italic.ttf",
            FontWeight.Normal,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/regular.ttf",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/light.ttf",
            FontWeight.Light,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/lightitalic.ttf",
            FontWeight.Light,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/medium.ttf",
            FontWeight.Medium,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/mediumitalic.ttf",
            FontWeight.Medium,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/semibolditalic.ttf",
            FontWeight.SemiBold,
            FontStyle.Italic
        ),
        Font(
            "fonts/montserrat/thin.ttf",
            FontWeight.Thin,
            FontStyle.Normal
        ),
        Font(
            "fonts/montserrat/thinitalic.ttf",
            FontWeight.Thin,
            FontStyle.Italic
        )
    )

    val snakeIOTypography = Typography(
        defaultFontFamily = montserrat,
        h1 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W900,
            fontSize = 42.sp
        ),
        h2 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W800,
            fontSize = 36.sp
        ),
        h3 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W700,
            fontSize = 30.sp
        ),
        h4 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp
        ),
        h5 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp
        ),
        h6 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        body1 = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = montserrat,
            fontSize = 14.sp
        ),
        button = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        overline = TextStyle(
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    )
}