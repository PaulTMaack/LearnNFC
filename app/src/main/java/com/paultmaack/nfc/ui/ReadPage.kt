package com.paultmaack.nfc.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.paultmaack.nfc.MainActivity
import com.paultmaack.nfc.R

enum class ReadScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
//    Flavor(title = R.string.choose_flavor),
//    Pickup(title = R.string.choose_pickup_date),
//    Summary(title = R.string.order_summary)
}
@Composable
fun ReadPage(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Row(
        modifier = modifier,
    ) {
        Button(
            modifier = Modifier
                .weight(1f)
                .padding(30.dp)
                .align(CenterVertically),
            onClick = {
                handleIntentAndNavigate(navController,ReadScreen.Home.name)
                navController.navigate(ReadScreen.Home.name)
            /*
            TODO
            */ }
        ) {
            Text(stringResource(R.string.click_me))
        }
    }
}


@Preview
@Composable
fun ReadPagePreview() {
    ReadPage(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    )
}
fun handleIntentAndNavigate(navController: NavController, argument: String) {
    // Use the NavController to navigate to the destination with arguments
    navController.navigate(argument) {
        // Pass arguments to the destination
        launchSingleTop = true
//        popUpTo = navController.graph.startDestination
    }
}