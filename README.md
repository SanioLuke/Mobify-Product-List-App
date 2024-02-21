![ads logo][adslogo] Mobify - Product List App [Version]() 1.0.0
======

Please download the APK File of this application from here : Download here![4]

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

![screenshot_01][1] ![screenshot_02][2] ![screenshot_03][3]


Usage
-----

### Retrofit Connection
To connect to the dummy API, I have used ``Retrofit API Integration`` inside a seperate java folder called ``Retrofit``:
``` Retrofit Client
    private static final String BASE_URL = "https://dummyjson.com/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
```

### Retrofit Interface
Now, to add query to the established retrofit connection, I have made a custom retrofit based ``interfaces``, so that using these interfaces the API calling can be handling with repective body and queries 
``` Retrofit Interfaces
    @GET("products")
    Call<ResponseModel> getAllProducts();

    @GET("products/{id}")
    Call<ProductModel> getProductDetail(@Path("id") int productId);
```

### Retrofit API Call Handler
Now, after success Retorfit connection and query setup, I have made a custom handler functions to fetch corresponding response so that handling the data in activity would be much easier.

* For that, I have made custom functions with each having a interface parameter, so that after successful retrival of the data, the data can be directly passed to the activity for display
* Created 2 functions: 1. ``callProductsApi()`` for API call for all the products, 2. ``callProductDetailApi()`` for retrieving details of a particular product 

``` API Products Interface
    interface ProductsInterface {
        default void getProducts(List<ProductModel> products) {
            Log.e(TAG, "Products: " + products);
        }

        default void getProductItem(ProductModel product) {
            Log.e(TAG, "Product Detail: " + product);
        }
    }
```

```java
    public static void callProductsApi(ProductsInterface productsInterface) {
        ProductsApiInterface productsApiInterface = RetrofitClient.getRetrofitInstance().create(ProductsApiInterface.class);
        Call<ResponseModel> callAllProducts = productsApiInterface.getAllProducts();
        callAllProducts.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductModel> products = response.body().getProducts();
                    productsInterface.getProducts(products);
                } else {
                    productsInterface.getProducts(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: Products API Call failure : " + t.getLocalizedMessage());
                productsInterface.getProducts(null);
            }
        });
```

``` java
    public static void callProductDetailApi(int productId, ProductsInterface productsInterface) {
        ProductsApiInterface productsApiInterface = RetrofitClient.getRetrofitInstance().create(ProductsApiInterface.class);
        Call<ProductModel> callProductDetail = productsApiInterface.getProductDetail(productId);
        callProductDetail.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(@NonNull Call<ProductModel> call, @NonNull Response<ProductModel> response) {
                if (response.isSuccessful()) {
                    ProductModel productDetail = response.body();
                    productsInterface.getProductItem(productDetail);
                } else {
                    productsInterface.getProducts(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductModel> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: Products API Call failure : " + t.getLocalizedMessage());
                productsInterface.getProducts(null);
            }
        });
    }
```

### API Calling
Now the successful intergration of API is called on ``Home Page`` and ``Product Display Page`` to display the details to the user

```java
    // Products retrieval API Call
        ProductsApiHandler.callProductsApi(new ProductsInterface() {
            @Override
            public void getProducts(List<ProductModel> products) {
                if (products != null) {
                     // Handling if data is received successfully.
                     // Some code below. The code is actually available in the project
                } else {
                    // Handling if data is not received.
                    // Some code below. The code is actually available in the project
                }
            }
        });
```

```java
    // Product Item Details retrieval API Call
    ProductsApiHandler.callProductDetailApi(productId, new ProductsInterface() {
            @Override
            public void getProductItem(ProductModel product) {
                if (product != null) {
                     // Handling if data is received successfully.
                     // Some code below. The code is actually available in the project
                } else {
                    // Handling if data is not received.
                    // Some code below. The code is actually available in the project
                }
            }
        });
```

### Design (XML)
* For displaying the list of products, I have used ``RecyclerView``
* For product item design, I have used ``MaterialCardView`` and nested ``RelativeLayout`` and ``LinearLayout``

``` xml
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/homeRecyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/homeToolbarDivider"
        android:layout_marginHorizontal="10dp"/>
```


[1]: ./images/screenshot_01.jpg
[2]: ./images/screenshot_02.jpg
[3]: ./images/screenshot_03.jpg
[4]: ./images/mobify.apk
[adslogo]: ./images/app_icon.webp
