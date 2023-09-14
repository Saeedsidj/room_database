package com.example.cardatabase.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cardatabase.dataBase.entity.CompanyEntity
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val companies = viewModel.companies.collectAsState()
    val modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val company = viewModel.company.collectAsState()
    var isEdit by remember { mutableStateOf(false) }
    var indexClicked: Int = 0
    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.DarkGray,
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            bottomEnd = 0.dp,
            bottomStart = 0.dp,
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        scrimColor = Color.Transparent,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "A D D  C O M P A N Y",
                    style = MaterialTheme.typography.h5,
                    color = Color.LightGray
                )
                OutlinedTextField(
                    value = company.value.name ,
                    onValueChange = {
                            viewModel.updateCoName(it)
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                    ),
                    label = { Text(text = "Enter Name", color = Color.LightGray) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray,
                        cursorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )
                OutlinedTextField(
                    value = company.value.country,
                    onValueChange = {
                            viewModel.updateCoCountry(it)
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                    ),
                    label = { Text(text = "Enter Country", color = Color.LightGray) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.LightGray,
                        focusedIndicatorColor = Color.LightGray,
                        cursorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )
                Button(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    onClick = {
                        coroutineScope.launch {
                            modalSheetState.hide()
                        }
                        viewModel.insertCompany()

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.DarkGray,
                        contentColor = Color.LightGray,
                        disabledBackgroundColor = MaterialTheme.colors.surface,
                        disabledContentColor = MaterialTheme.colors.onSurface
                    ),
                    border = BorderStroke(1.dp, Color.LightGray)
                ) {
                    Text(
                        text = "S U B M I T",
                    )
                }
            }
        }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "C A R S") },
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            modalSheetState.show()
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(text = "A D D")
                        Icon(Icons.Default.Add, contentDescription = "")
                    }
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(companies.value) { index, it ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                3.dp,
                                Color.LightGray,
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color.DarkGray)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Column {
                            if (isEdit && index == indexClicked) {
                                Column(
                                    modifier = Modifier.padding(10.dp),
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    OutlinedTextField(
                                        value = company.value.name,
                                        onValueChange = {
                                            viewModel.updateCoName(it)
                                        },
                                        textStyle = TextStyle(
                                            textAlign = TextAlign.Start,
                                        ),
                                        label = { Text(text = "Edit Name", color = Color.LightGray) },
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Done
                                        ),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.Transparent,
                                            textColor = Color.LightGray,
                                            focusedIndicatorColor = Color.LightGray,
                                            cursorColor = Color.LightGray,
                                            unfocusedIndicatorColor = Color.LightGray
                                        )
                                    )
                                    OutlinedTextField(
                                        value =  company.value.country,
                                        onValueChange = {
                                                viewModel.updateCoCountry(it)
                                        },
                                        textStyle = TextStyle(
                                            textAlign = TextAlign.Start,
                                        ),
                                        label = { Text(text = "Edit Country", color = Color.LightGray) },
                                        keyboardOptions = KeyboardOptions(
                                            imeAction = ImeAction.Done
                                        ),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.Transparent,
                                            textColor = Color.LightGray,
                                            focusedIndicatorColor = Color.LightGray,
                                            cursorColor = Color.LightGray,
                                            unfocusedIndicatorColor = Color.LightGray
                                        )
                                    )
                                }
                            } else {
                                Text(
                                    text = "${it.id} - ${it.name}",
                                    fontSize = 20.sp,
                                    color = Color.LightGray
                                )
                                Text(
                                    text = it.country,
                                    fontSize = 15.sp,
                                    color = Color.LightGray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        if (!isEdit) {
                            IconButton(onClick = {
                                isEdit = true
                                indexClicked = index
                            }) {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = "",
                                    tint = Color.LightGray
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.deleteCompany(it)
                                }
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "",
                                    tint = Color.LightGray
                                )
                            }
                        }
                        if (isEdit && index == indexClicked) {
                            IconButton(onClick = {
                                viewModel.updateCompany(it.id)
                                isEdit = !isEdit
                            }) {
                                Icon(
                                    Icons.Default.Done,
                                    contentDescription = "",
                                    tint = Color.LightGray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
