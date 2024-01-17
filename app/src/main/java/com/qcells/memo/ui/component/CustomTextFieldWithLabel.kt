package com.qcells.memo.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qcells.memo.ui.theme.ActiveBorder
import com.qcells.memo.ui.theme.Gray100
import com.qcells.memo.ui.theme.Gray750
import com.qcells.memo.ui.theme.Gray950

@Composable
fun CustomTextFieldWithLabel(
    modifier: Modifier = Modifier,
    label: String = "",
    text: String = "",
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var isActive by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
    ) {
        Row {
            Spacer(Modifier.width(16.dp))
            Label(label = label)
        }

        Spacer(Modifier.height(6.dp))

        /**
         * TextField 의 내부 구조를 보니 TextField 도 BasicTextField 을 호출하고 있었고, BasicTextField 은
         * CoreTextField 를 호출하고 있다는 것을 확인했다. TextField 는 Meterial Design Guilde 를 따르는
         * Composable 인 점과 TextField -> BasicTextField -> CoreTextField 순서인 내부 구조를 보았을 때
         * 아래 레벨로 내려갈 수록 개발자가 커스텀할 수 있는 부분이 더 많다고 생각하였다.
         * 현재 요구사항에 필요한 둥근 모서리, 포커싱 등의 기능들은 전부 TextField 로도 할 수 있으니 굳이 로우 레벨 API 를 사용할 필요가
         * 없다고 생각하여 TextField 로 구현을 하려고 했었다. 하지만 요구 사항 중 TextField 내부의 패딩을 적용해야 하는 것이 있는데,
         * TextField 는 기본적으로 내부 패딩이 존재하고 이를 조절할 수 방법을 딱히 찾지 못했다. 결국에는 아무런 decoration 이 없는
         * BasicTextField 으로 구현을 하게 되었다.
         **/

        BasicTextField(
            modifier = modifier
                .onFocusChanged { focusState ->
                    isActive = focusState.isFocused
                },
            value = text,
            textStyle = TextStyle(
                color = if (isActive) Gray950 else Gray750,
                fontSize = 14.sp,
            ),
            singleLine = singleLine,
            onValueChange = {
                onValueChange(it)
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    isActive = false
                }
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = if (isActive) ActiveBorder else Gray100,
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 40.dp
                        )
                ) {
                    innerTextField()
                }
            }
        )
    }

}

@Composable
fun Label(
    modifier: Modifier = Modifier,
    label: String,
) {
    Text(
        modifier = modifier,
        text = label,
        fontSize = 13.sp,
        maxLines = 1,
    )
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    CustomTextFieldWithLabel(
        label = "제목",
        text = "Hello sadasdasdsaWorld!\n\n\n\n\n\n\n\n\n\n\n\n\n",
        onValueChange = {

        }
    )
}