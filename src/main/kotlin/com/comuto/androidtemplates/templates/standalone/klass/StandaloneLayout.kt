package com.comuto.androidtemplates.templates.standalone.klass

fun createStandaloneLayoutXML()=""" 
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/colorBackground"
        >
       
    <include
        android:id="@+id/toolbar"
        layout="@layout/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        />

    </androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()