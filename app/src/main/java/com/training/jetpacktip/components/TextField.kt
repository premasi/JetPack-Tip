package com.training.jetpacktip.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.jetpacktip.R

@Composable
fun InputTextField(
    modifier: Modifier = Modifier, // Not Required
    valueState: MutableState<String>,
    labelText: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
){
    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(labelText) },
        leadingIcon = { Icon(painter = painterResource(R.drawable.baseline_catching_pokemon_24), contentDescription = "InputTextFieldIcon") },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
            imeAction = imeAction),
        keyboardActions = onAction,
        modifier = modifier.padding(vertical = 4.dp)
            .padding(start = 4.dp)
    )
}