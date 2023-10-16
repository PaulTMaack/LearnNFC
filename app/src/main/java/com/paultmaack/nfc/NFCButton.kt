package com.paultmaack.nfc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paultmaack.nfc.ui.theme.LearnNFCTheme


@Composable
fun NFCButton(name: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier.padding(start = 6.dp, end = 6.dp)
    ) {
        Text(name)
    }
//    Text(
//        text = "Register $name!",
//        modifier = modifier
//    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnNFCTheme {
        NFCButton("NFC")
    }
}
//fun handleIntentAndNavigate(navController: NavController, argument: String) {
//    // Use the NavController to navigate to the destination with arguments
//    navController.navigate("destination_route/{argument}") {
//        // Pass arguments to the destination
//        launchSingleTop = true
////        popUpTo = navController.graph.startDestination
//    }
//}

/*
https://stackoverflow.com/questions/52763896/android-kotlin-how-to-read-nfc-tag-inside-activity
override fun onResume() {
    super.onResume()

    NfcAdapter.getDefaultAdapter(this)?.let { nfcAdapter ->
        // An Intent to start your current Activity. Flag to singleTop
        // to imply that it should only be delivered to the current
        // instance rather than starting a new instance of the Activity.
        val launchIntent = Intent(this, this.javaClass)
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        // Supply this launch intent as the PendingIntent, set to cancel
        // one if it's already in progress. It never should be.
        val pendingIntent = PendingIntent.getActivity(
            this, 0, launchIntent, PendingIntent.FLAG_CANCEL_CURRENT
        )

        // Define your filters and desired technology types
        val filters = arrayOf(IntentFilter(ACTION_TECH_DISCOVERED))
        val techTypes = arrayOf(arrayOf(IsoDep::class.java.name))

        // And enable your Activity to receive NFC events. Note that there
        // is no need to manually disable dispatch in onPause() as the system
        // very strictly performs this for you. You only need to disable
        // dispatch if you don't want to receive tags while resumed.
        nfcAdapter.enableForegroundDispatch(
            this, pendingIntent, filters, techTypes
        )
    }
}

override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)

    if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        IsoDep.get(tag)?.let { isoDepTag ->
            // Handle the tag here
        }
    }
}


https://stackoverflow.com/questions/66546962/jetpack-compose-how-do-i-refresh-a-screen-when-app-returns-to-foreground




 */