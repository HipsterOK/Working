package ru.porcupine.woodcutterclicker.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import ru.porcupine.woodcutterclicker.R
import ru.porcupine.woodcutterclicker.logic.MainLogic
import ru.porcupine.woodcutterclicker.logic.UpgradeController
import ru.porcupine.woodcutterclicker.tools.SharedPreference
import ru.porcupine.woodcutterclicker.ui.theme.Patterns

class MainActivity : ComponentActivity() {

    private lateinit var mainLogic: MainLogic
    private lateinit var upgradeController: UpgradeController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLogic = MainLogic(this)
        upgradeController = UpgradeController(this)
        mainLogic.startThread(lifecycleScope, this)

        setContent {
            Patterns().Background()
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Balance(mainLogic)
                Upgrade(mainLogic, upgradeController)
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .padding((mainLogic.padding * 3).dp), contentAlignment = Alignment.BottomCenter){
                Axe(mainLogic)
            }
            UpgradeList(mainLogic, upgradeController)
        }
    }

    @Composable
    fun Balance(mainLogic: MainLogic){
        Row(
            Modifier
                .padding((mainLogic.padding).dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.wood),
                contentDescription = "wood",
                modifier = Modifier
                    .size(mainLogic.woodSize.dp)
                    .padding(end = mainLogic.padding.dp)
            )
            Text(
              text = "${mainLogic.balance}",
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily(Font(mainLogic.font)), shadow = Shadow(Color.Black, Offset(mainLogic.padding, 0f))),
                fontSize = (mainLogic.screenWidth / 10).sp,
                color = colorResource(id = R.color.wood),
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = mainLogic.padding.dp)
                )
        }
    }

    @Composable
    fun Upgrade(mainLogic: MainLogic, upgradeController: UpgradeController){
        Patterns().Button(
            mainLogic = mainLogic,
            text = "UPGRADE",
            h = 12f,
            w = 3f,
            font = 20f) {
            upgradeController.alpha=1f
            upgradeController.posX= 0f
        }
    }

    @Composable
    fun Axe(mainLogic: MainLogic){
        val height by animateDpAsState(targetValue = mainLogic.axeHeight.dp)
        val width by animateDpAsState(targetValue = mainLogic.axeWidth.dp)
        Image(
            painter = painterResource(id = R.drawable.axe),
            contentDescription = "axe",
            modifier = Modifier
                .size(width = width, height = height)
                .noRippleClickable {
                    mainLogic.balance += mainLogic.oneTap
                    mainLogic.tapAnimation()
                }
        )
    }

    private inline fun Modifier.noRippleClickable(crossinline onClick: ()->Unit): Modifier = composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }

    @Composable
    fun UpgradeList(mainLogic: MainLogic, upgradeController: UpgradeController) {
        Box(
            Modifier
                .fillMaxSize()
                .alpha(upgradeController.alpha)
                .background(Color.White)
                .offset(upgradeController.posX.dp, 0.dp)
        ){
           Patterns().Background()
            Column(
                modifier = Modifier
                .fillMaxSize()
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Balance(mainLogic)
                    Back(mainLogic, upgradeController)
                }
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    UpgradeOne(mainLogic = mainLogic, upgradeController, 0)
                    UpgradeOne(mainLogic = mainLogic, upgradeController, 1)
                    UpgradeOne(mainLogic = mainLogic, upgradeController, 2)
                    UpgradeOne(mainLogic = mainLogic, upgradeController, 3)
                    UpgradeOne(mainLogic = mainLogic, upgradeController, 4)
                }

            }
        }
    }

    @Composable
    fun UpgradeOne(mainLogic: MainLogic, upgradeController: UpgradeController, i: Int){
        Box(
            Modifier
                .padding(mainLogic.padding.dp)
                .noRippleClickable {
                    upgradeController.tapUpgrade(i, mainLogic)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.button_back),
                contentDescription = "player border",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        height = (mainLogic.screenHeight / 7).dp,
                        width = mainLogic.screenWidth.dp
                    ),
                contentScale = ContentScale.FillBounds
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = upgradeController.nameUpgradeList[i],
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontFamily = FontFamily(Font(mainLogic.font))),
                    fontSize = (mainLogic.screenWidth / 15).sp,
                    color = colorResource(id = R.color.wood),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding((mainLogic.padding).dp)
                )
                Row(
                    Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = mainLogic.padding.dp),
                       verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                    Image(
                        painter = painterResource(id = R.drawable.wood),
                        contentDescription = "wood",
                        modifier = Modifier
                            .size((mainLogic.woodSize / 1.5).dp)
                            .padding(end = (mainLogic.padding / 2).dp)
                    )
                    Text(
                        text = "${upgradeController.nameUpgradeCost[i]}",
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontFamily = FontFamily(Font(mainLogic.font)), shadow = Shadow(Color.Black, Offset(mainLogic.padding, 0f))),
                        fontSize = (mainLogic.screenWidth / 15).sp,
                        color = colorResource(id = R.color.wood),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = (mainLogic.padding/2).dp)
                    )
                }
            }
        }
    }

    @Composable
    fun Back(mainLogic: MainLogic, upgradeController: UpgradeController){
        Patterns().Button(
            mainLogic = mainLogic,
            text = "BACK",
            h = 12f,
            w = 3f,
            font = 20f
        ) {
            upgradeController.alpha = 0f
            upgradeController.posX = mainLogic.screenHeight
        }
    }

    override fun onPause() {
        super.onPause()

        SharedPreference(this).save("balance", mainLogic.balance)
        SharedPreference(this).save("oneTap", mainLogic.oneTap)
        SharedPreference(this).save("oneSecond", mainLogic.oneSecond)
        for(i in 0 until 5){
            SharedPreference(this).save("price$i", upgradeController.nameUpgradeCost[i])
        }

    }
}