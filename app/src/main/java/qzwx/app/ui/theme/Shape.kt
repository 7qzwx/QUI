package qzwx.app.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// 像素风格通常使用直角或裁剪角，而不是圆角
val AppShapes = Shapes(
    small = CutCornerShape(2.dp),
    medium = CutCornerShape(4.dp),
    large = CutCornerShape(6.dp)
) 