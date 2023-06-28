package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
@Composable
private fun MyApp(modifier: Modifier = Modifier){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Surface(
       // modifier = Modifier.fillMaxSize(),
        modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        if (shouldShowOnboarding){
            OnboardScreen(onContinueClicked = {shouldShowOnboarding = false})
        }else{
            Greetings()
        }
    }
}
@Composable
private fun Greetings( modifier: Modifier = Modifier,
                       names: List<String> = List(100){"$it"}){
            LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
                items(items = names) { name ->
                    Greeting(name = name)
                }

        }
}
@Composable
private fun OnboardScreen(onContinueClicked: ()-> Unit, modifier: Modifier = Modifier){

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Welcome to Jetpack Compose by DucAnhDey")
        ElevatedButton(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}
@Composable
private fun Greeting(name: String) {
    var expanded by remember{mutableStateOf(false)}
    val extraPadding by animateDpAsState(if(expanded) 48.dp else 0.dp,
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
    )
    Surface(color = MaterialTheme.colorScheme.primary,
    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "DucAnh,")
                Text(text = name)
            }
            ElevatedButton(onClick = {
                expanded = !expanded
            }) {
                Text(if (expanded) "Show Less" else "Show More")


            }
        }

    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
      MyApp(Modifier.fillMaxSize())
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun GreetingsPreview(){
    MyApplicationTheme {
        Greetings()
    }
}
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardPreview() {
    MyApplicationTheme {
        OnboardScreen(onContinueClicked = {})
    }
}