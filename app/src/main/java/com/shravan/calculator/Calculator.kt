package com.shravan.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Icon


@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
){
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 24.dp, start = 12.dp, end = 12.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .weight(1f)
                .padding(bottom = 16.dp, end = 40.dp, start = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End

        ) {
            when(val currentState = state.value){
                is CalculatorState.Error -> {
                    Text(
                        text = currentState.expression,
                        lineHeight = 24.sp,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                CalculatorState.Initial -> {}
                is CalculatorState.Input -> {
                    Text(
                        text = currentState.expression,
                        lineHeight = 48.sp,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = currentState.result,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                is CalculatorState.Success -> {
                    Text(
                        text = currentState.result,
                        lineHeight = 48.sp,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "",
                        lineHeight = 24.sp,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.EULER))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    modifier = Modifier,
                    text = "e",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            Box(
                modifier = Modifier.weight(1f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PI))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "π",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.SINE))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "sin",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Delete)
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Backspace,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(4.dp)
                )
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Clear)
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "Ac",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PARENTHESIS))
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "( )",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIVIDE))
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "/",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_7))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "7",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_8))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "8",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_9))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "9",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.MULTIPLY))
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "X",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_4))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "4",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_5))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "5",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_6))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "6",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.SUB))
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "-",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_1))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "1",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_2))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "2",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_3))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "3",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable(){
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.ADD))
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "+",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.weight(1f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_0))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "0",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DECIMAL))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = ".",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Box(
                modifier = Modifier.weight(1/2f)
                    .clip(CircleShape)
                    .clickable{
                        viewModel.processCommand(CalculatorCommand.Evaluate)
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier,
                    text = "=",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}