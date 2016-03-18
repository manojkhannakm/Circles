# Circles

### Introduction

An Android library module that provides three custom views `CircleLayout`, `CircleView`, `CircleTextView`. The circles are rendered using `VectorDrawable`.

### Preview

<img src="/preview/preview.png" width=360 />

### Usage

```xml
<?xml version="1.0" encoding="utf-8"?>

<com.manojkhannakm.circles.view.CircleLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/circle_layout_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.manojkhannakm.circles.view.CircleView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:circle_color="@color/red"
        app:circle_size="100dp"
        app:circle_x_factor="0.3"
        app:circle_y_factor="0.5" />

    <com.manojkhannakm.circles.view.CircleTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:circle_color="@color/green"
        app:circle_size="100dp"
        app:circle_text_text="Text"
        app:circle_x_factor="0.6"
        app:circle_y_factor="0.5" />

</com.manojkhannakm.circles.view.CircleLayout>
```
