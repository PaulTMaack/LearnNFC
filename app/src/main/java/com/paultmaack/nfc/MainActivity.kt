package com.paultmaack.nfc

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.NfcA
import android.nfc.tech.NfcF
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import com.paultmaack.nfc.ui.theme.LearnNFCTheme

//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val lifecycleOwner = LocalLifecycleOwner.current
//        val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()
//
//        LaunchedEffect(lifecycleState) {
//            // Do something with your state
//            // You may want to use DisposableEffect or other alternatives
//            // instead of LaunchedEffect
//            when (lifecycleState) {
//                Lifecycle.State.DESTROYED -> {}
//                Lifecycle.State.INITIALIZED -> {}
//                Lifecycle.State.CREATED -> {}
//                Lifecycle.State.STARTED -> {}
//                Lifecycle.State.RESUMED -> {}
//            }
//        }
//        lateinit var intentFiltersArray: Array<IntentFilter>
//        val techListsArray = arrayOf(arrayOf(NfcF::class.java.name))
//        val nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(this)
//        lateinit var pendingIntent: PendingIntent
//        val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
//        try {
//            ndef.addDataType("text/plain")
//        } catch (e: IntentFilter.MalformedMimeTypeException) {
//            throw RuntimeException("fail", e)
//        }
//        intentFiltersArray = arrayOf(ndef)
//        if (nfcAdapter == null) {
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage("This device doesn't support NFC.")
//            builder.setPositiveButton("Cancel", null)
//            val myDialog = builder.create()
//            myDialog.setCanceledOnTouchOutside(false)
//            myDialog.show()
//            // txttext.setText("THIS DEVICE DOESN'T SUPPORT NFC. PLEASE TRY WITH ANOTHER DEVICE!")
//        } else if (!nfcAdapter!!.isEnabled) {
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("NFC Disabled")
//            builder.setMessage("Plesae Enable NFC")
//            // txttext.setText("NFC IS NOT ENABLED. PLEASE ENABLE NFC IN SETTINGS->NFC")
//            builder.setPositiveButton("Settings") { _, _ -> startActivity(Intent(Settings.ACTION_NFC_SETTINGS)) }
//            builder.setNegativeButton("Cancel", null)
//            val myDialog = builder.create()
//            myDialog.setCanceledOnTouchOutside(false)
//            myDialog.show()
//        }
//        setContent {
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nfcAdapter: NfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        var pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_MUTABLE
        )
        val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED).apply {
            try {
                addDataType("*/*")    /* Handles all MIME based dispatches.
                                 You should specify only the ones that you need. */
            } catch (e: IntentFilter.MalformedMimeTypeException) {
                throw RuntimeException("fail", e)
            }
        }

        val intentFiltersArray = arrayOf(ndef)
        val techListsArray = arrayOf(arrayOf<String>(NfcF::class.java.name))


        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LearnNFCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NFCButton("NFC")
                }
            }
        }
    }

    //    public override fun onPause() {
//        super.onPause()
//        adapter.disableForegroundDispatch(this)
//    }
//
//    public override fun onResume() {
//        super.onResume()
//        adapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray)
//    }
//
//    public override fun onNewIntent(intent: Intent) {
//        val tagFromIntent: Tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
//        //do something with tagFromIntent
//    }
    public override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(applicationContext, "I'm on the outside.", Toast.LENGTH_SHORT).show()
//        val tagFromIntent = intent.getParcelableExtra( NfcAdapter.EXTRA_TAG)
//        val tagFromIntent = intent?.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG) //?: return
//
//        val nfc = NfcA.get(tagFromIntent)
//
//        val atqa: ByteArray = nfc.getAtqa()
//        val sak: Short = nfc.getSak()
//        nfc.connect()
//        val isConnected = nfc.isConnected()
//
//
//        if (isConnected) {
//            val receivedData: ByteArray = nfc.transceive(NFC_READ_COMMAND)
////        ..
////        //code to handle the received data
////        // Received data would be in the form of a byte array that can be converted to string
////        //NFC_READ_COMMAND would be the custom command you would have to send to your NFC Tag in order to read it
////        ..
//        } else {
//            Log.e("ans", "Not connected")
//        }
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent?.action) {
            intent?.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                Toast.makeText(applicationContext, "Value From Message Inside action", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


/*
https://developer.android.com/jetpack/compose/side-effects
Might be helpful.
* */

