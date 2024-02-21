![ads logo][adslogo] Mobify - Product List App [Version]() 1.0.0
======

This android application is a small integration of API call for retriving sample products data from dummy API server and displaying the list of products along with its other details. The app includes pages like

* Splash Screen
* Home Page
* Product Display Page

The instructions for using this application are :
* Whiling opening the app, first screen that pops up is **SplashScreen**. Please wait for the splashscreen to load and go to home page.
* After that, the **HomePage** opens up and a API call is triggered in which with successful call trigger, the sample product list is display to the user.
* The user can scroll through the home page and find their desired product to view.
* While clicking/tapping on the desired product, another page called **Product Display** is opened which contains the details of that clicked products like information like - Product Name, Cateogry, Images, Rating, etc.

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

[1]: ./images/screenshot_01.jpg
[2]: ./images/screenshot_02.jpg
[3]: ./images/screenshot_03.jpg
[adslogo]: ./images/app_icon.webp
