package ru.porcupine.animalquiz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

var succesCount = 0
var wrongCount = 0
var index = 0
var listQuestion:MutableList<QuestionModel> = arrayListOf()
var listAnswer:MutableList<String> = arrayListOf()
var active = true

class QuizActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        succesCount = 0
        wrongCount = 0
        index = 0
        listQuestion = arrayListOf()
        listAnswer = arrayListOf()
        active=true

        val questionAdapter = QuestionAdapter()
        listQuestion = questionAdapter.readFromAsset(application)
        listQuestion.shuffle()


        setContent {
            listAnswer.add(listQuestion[0].trueAnswer)
            listAnswer.add(listQuestion[0].wrongAnswer1)
            listAnswer.add(listQuestion[0].wrongAnswer2)
            listAnswer.add(listQuestion[0].wrongAnswer3)

            listAnswer.shuffle()

            val question = remember { mutableStateOf(value = listQuestion[0].question) }
            val trueAnswer = remember { mutableStateOf(value = listQuestion[0].trueAnswer)  }
            val answer1 = remember() { mutableStateOf(value = listAnswer[0]) }
            val answer2 = remember() { mutableStateOf(value = listAnswer[1]) }
            val answer3 = remember() { mutableStateOf(value = listAnswer[2]) }
            val answer4 = remember() { mutableStateOf(value = listAnswer[3]) }

            val enbl1 = remember() { mutableStateOf(value = true) }
            val enbl2 = remember() { mutableStateOf(value = true) }
            val enbl3 = remember() { mutableStateOf(value = true) }
            val enbl4 = remember() { mutableStateOf(value = true) }

            BackImage()
            Question(
                question = question,
                answer1 = answer1,
                answer2 = answer2,
                answer3 = answer3,
                answer4 = answer4,
                trueAnswer = trueAnswer,
                enbl1 = enbl1,
                enbl2 = enbl2,
                enbl3 = enbl3,
                enbl4 = enbl4
            )
        }
    }

    @Composable
    fun Question(question:MutableState<String>, answer1:MutableState<String>,
                 answer2:MutableState<String>, answer3:MutableState<String>, answer4:MutableState<String>, trueAnswer:MutableState<String>,
                 enbl1:MutableState<Boolean>,enbl2:MutableState<Boolean>,enbl3:MutableState<Boolean>,enbl4:MutableState<Boolean>,){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val backgroundColor1 = remember { mutableStateOf(value = Color.White) }
            val backgroundColor2 = remember { mutableStateOf(value = Color.White) }
            val backgroundColor3 = remember { mutableStateOf(value = Color.White) }
            val backgroundColor4 = remember { mutableStateOf(value = Color.White) }

            val enblNext = remember { mutableStateOf(value=false) }
            val visBtn = remember { mutableStateOf(value = 0f) }

            Row(modifier = Modifier
                .padding(25.dp)
                .fillMaxHeight(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = question.value,
                    fontWeight = FontWeight(600),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                modifier = Modifier
                    .alpha(visBtn.value)
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .padding(30.dp),
                enabled = enblNext.value,
                colors = ButtonDefaults.buttonColors(Color.White),
                onClick = {
                    if(index < 14) {
                        index++

                        listAnswer.clear()
                        listAnswer.add(listQuestion[index].trueAnswer)
                        listAnswer.add(listQuestion[index].wrongAnswer1)
                        listAnswer.add(listQuestion[index].wrongAnswer2)
                        listAnswer.add(listQuestion[index].wrongAnswer3)

                        listAnswer.shuffle()

                        question.value = listQuestion[index].question
                        trueAnswer.value = listQuestion[index].trueAnswer
                        answer1.value = listAnswer[0]
                        answer2.value = listAnswer[1]
                        answer3.value = listAnswer[2]
                        answer4.value = listAnswer[3]

                        enbl1.value = true
                        enbl2.value = true
                        enbl3.value = true
                        enbl4.value = true

                        backgroundColor1.value = Color.White
                        backgroundColor2.value = Color.White
                        backgroundColor3.value = Color.White
                        backgroundColor4.value = Color.White

                        enblNext.value = false
                        active=true
                        visBtn.value = 0f
                    } else {
                        val resultPage = Intent(this@QuizActivity, ResultActivity::class.java)
                        resultPage.putExtra("succes", succesCount)
                        resultPage.putExtra("wrong", wrongCount)
                        startActivity(resultPage)
                        finish()
                    }
                }
            ) {
                Text(text = "Next")
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if(active) {
                            if (answer1.value == trueAnswer.value) {
                                backgroundColor1.value = Color.Green
                                succesCount++
                            } else {
                                backgroundColor1.value = Color.Red
                                wrongCount++
                            }
                            active = false
                            enbl2.value = false
                            enbl3.value = false
                            enbl4.value = false
                            enblNext.value = true
                            visBtn.value = 1f
                        }
                    },
                    enabled = enbl1.value,
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor1.value),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                        .padding(end = 10.dp)

                    ) {
                    Text(text = answer1.value, textAlign = TextAlign.Center)
                }

                Button(
                    onClick = {
                        if(active) {
                            if (answer2.value == trueAnswer.value) {
                                backgroundColor2.value = Color.Green
                                succesCount++
                            } else {
                                backgroundColor2.value = Color.Red
                                wrongCount++
                            }
                            active = false
                            enbl1.value = false
                            enbl3.value = false
                            enbl4.value = false
                            enblNext.value = true
                            visBtn.value = 1f
                        }
                    },
                    enabled = enbl2.value,
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor2.value),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 10.dp)

                    ){
                    Text(text = answer2.value, textAlign = TextAlign.Center)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if(active) {
                            if (answer3.value == trueAnswer.value) {
                                backgroundColor3.value = Color.Green
                                succesCount++
                            } else {
                                backgroundColor3.value = Color.Red
                                wrongCount++
                            }
                            active = false
                            enbl1.value = false
                            enbl2.value = false
                            enbl4.value = false
                            enblNext.value = true
                            visBtn.value = 1f
                        }
                    },
                    enabled = enbl3.value,
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor3.value),
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                        .padding(end = 10.dp)

                    ){
                    Text(text = answer3.value, textAlign = TextAlign.Center)
                }
                Button(
                    onClick = {
                        if(active) {
                            if (answer4.value == trueAnswer.value) {
                                backgroundColor4.value = Color.Green
                                succesCount++
                            } else {
                                backgroundColor4.value = Color.Red
                                wrongCount++
                            }
                            active = false
                            enbl1.value = false
                            enbl2.value = false
                            enbl3.value = false
                            enblNext.value = true
                            visBtn.value = 1f
                        }
                    },
                    enabled = enbl4.value,
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor4.value),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 10.dp)

                    ){
                    Text(text = answer4.value, textAlign = TextAlign.Center)
                }
            }
        }
    }

    @Composable
    fun BackImage() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "back",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

