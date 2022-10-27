package edu.temple.basicbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    lateinit var urlEditText: EditText
    lateinit var goButton: ImageButton
    lateinit var webView: WebView
    lateinit var webPageTitle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)

        //enable javascript
        webView.settings.javaScriptEnabled = true

        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = WebViewClient()
        goButton.setOnClickListener(){
            var url = urlEditText.text.toString()
            if(!url.startsWith("https://")){
                if(!url.startsWith("www.")){
                    //does not contain www.
                    webView.loadUrl("https://www.$url")
                } else{
                    //does contain www. so we just add https://
                    webView.loadUrl("https://$url")
                }
            } else{
                webView.loadUrl(url)
            }
            webView.webViewClient = object: WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?){
                    super.onPageFinished(webView, url)
                    webPageTitle = webView.title.toString()
                    title = webPageTitle
                }
        }
        }
    }
}