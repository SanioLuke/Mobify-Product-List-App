![ads logo][adslogo] Android Ads Module [Version]() 1.0.0
======

This latest Android Ads Module has been simplified with implementation and reduced code & more adaptive processing of ads at runtime.

* Now, the app will run even when the ads have been blocked by external ad blockers.
* New ads type like **Collapsible ads** & **Banner ads** have been implemented with in this module.
* Reduced the number of paramters for the developers whiling implementing the ads module.
* Implemented One Signal for Remote Cloud Notification
* **Facebook Ads** also implemented with no extra implementation for the developers

Screenshots
-----------

![Demo screenshot_01][1]!![Demo screenshot_02][2] ![Demo screenshot_03][3]


Usage
-----

### Native Ad
To start using the Native Ad you just need to add ``NativeView`` inside your .xml file:
* ``app:adType`` can either be `SMALL`, `MEDIUM` or `FULL`.
* To add a placeholder to the NativeView use the `app:placeholder` and pass a drawable.
* By `Default` its ScaleType is `FitXY`
```xml
    <com.adsmodule.api.adsModule.views.NativeView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:adType="{SMALL|MEDIUM|FULL}" />
```

### Banner Ad
To start using the Native Ad you just need to add ``BannerView`` inside your .xml file:
* ``Collapsible Banner Ad`` to enable Collapsible Banner Ad use the `app:isCollapsible` and set its value as `true`.
* By Default ``Collapsible Banner Ad`` is disabled.

```xml
    <com.adsmodule.api.adsModule.views.BannerView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:isCollapsible="false"/>
```


### AppOpen Ad
To start using the AppOpen Ad you just need to add ``AdUtils.showAppOpenAd()`` inside your .java file:

```java
    AdUtils.showAppOpenAd(activity, isLoaded -> {
      // The code inside will the execute after dismiss
      // or in case the ad fails
    });
```

### Interstitial Ad
To start using the Interstitial Ad you just need to add ``AdUtils.showInterstitialAd()`` inside your .java file:

```java
    AdUtils.showInterstitialAd(activity, isLoaded -> {
      // The code inside will the execute after dismiss
      // or in case the ad fails
    });
```

### Rewarded Ad
To start using the Rewarded Ad you just need to add ``AdUtils.showRewardedAd()`` inside your .java file:
* ``isLoaded`` will provide a boolean value by with you can determine the ad is loaded or not
* Also can be use for determining whether the user has received the reward or not

```java
    AdUtils.showRewardedAd(activity, isLoaded -> {
        // The Code inside will the execute after dismiss
        // or in case the ad fails
    });
```
### BackPress Ad
To start using the BackPress Ad you just need to add ``AdUtils.showBackPressAd()`` inside your .java file:

```java
    AdUtils.showBackPressAd(activity, isLoaded -> {
        // The Code inside will the execute after dismiss
        // or in case the ad fails
    });
```

Cloud Notification
-----

### One Signal
To start using the One Signal you have to uncomment this line of code which is inside the ``onCreate()`` which in turn is inside the file which extends ``Application`` class

```java
    // UnComment this line if you require onesignal
    // Globals.initOneSignal(app.getApplicationContext(), "**YOUR_ONE_SIGNAL_APP_ID**");
```

# Contributors

[John Pandian](https://github.com/I-KNOWN)

[Sanio Luke Sebastian](https://github.com/SanioLukeIDE)

Thank you all for your work!

Do you want to contribute?
--------------------------

Feel free to give any suggestion & point out bugs, we are all  be glad to improve it with your help.

[1]: ./art/Screenshot_01.png
[2]: ./art/Screenshot_02.png
[3]: ./art/Screenshot_03.png
[adslogo]: ./art/app_icon.png
