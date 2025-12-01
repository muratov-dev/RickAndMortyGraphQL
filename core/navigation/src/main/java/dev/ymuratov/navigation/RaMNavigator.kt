package dev.ymuratov.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import dagger.hilt.android.scopes.ActivityRetainedScoped

typealias EntryProviderInstaller = EntryProviderScope<Destination>.() -> Unit

@ActivityRetainedScoped
class RaMNavigator(startDestination: Destination) {
    val backStack : SnapshotStateList<Destination> = mutableStateListOf(startDestination)

    fun goTo(destination: Destination){
        backStack.add(destination)
    }

    fun goBack(){
        backStack.removeLastOrNull()
    }
}