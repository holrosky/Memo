package com.qcells.memo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qcells.memo.ui.theme.ButtonPressed
import com.qcells.memo.ui.theme.CustomRippleTheme
import com.qcells.memo.ui.theme.Gray300
import com.qcells.memo.ui.theme.Gray950

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit,
) {
    // https://developer.android.com/jetpack/compose/compositionlocal 과
    // https://developer.android.com/reference/kotlin/androidx/compose/material/ripple/package-summary 참고하여 구현하였다.
    CompositionLocalProvider(LocalRippleTheme provides CustomRippleTheme(rippleColor = ButtonPressed)) {
        Box(
            modifier = modifier
                // 클릭시 ripple 효과가 Box 전체 사각형 모양으로 노출되는 것을 막아주기 위해 사용했다.
                .clip(shape = RoundedCornerShape(24.dp))
                .clickable {
                    onClick()
                }
                .background(
                    color = Gray300,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(vertical = 14.dp, horizontal = 20.dp)

        ) {
            Text(
                text = buttonText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Gray950
            )
        }
    }
}

@Preview
@Composable
fun CustomTextButtonPreview() {
    CustomTextButton(buttonText = "asdjklkdaskdd") {}
}