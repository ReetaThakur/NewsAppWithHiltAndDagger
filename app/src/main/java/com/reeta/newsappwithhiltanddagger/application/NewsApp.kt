package com.reeta.newsappwithhiltanddagger.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp :Application() {
}